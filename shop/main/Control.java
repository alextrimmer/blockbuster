package shop.main;

import shop.ui.UI;
import shop.ui.UIFactory;
import shop.ui.UIMenuBuilderInterface;
import shop.ui.UIFormBuilderInterface;
import shop.ui.UIFormMenuInterface;

import shop.data.Inventory;

import shop.main.UIMenuEnums.EnumConstructor;

class Control {

  private static UIFactory fact = new UIFactory();
  private UIFormMenuInterface[] _menus;
  private States _state;

  private UIFormMenuInterface _getVideoForm;
  private UIFormEnums _stringTest;
    
  private Inventory _inventory;
  private UI _ui;
  
  Control(Inventory inventory, UI ui) {
    _inventory = inventory;
    _ui = ui;

    _menus = new UIFormMenuInterface[States.NUMSTATES.getValue()];
    _state = States.START;
    addSTART(States.START);
    addEXIT(States.EXIT);
    
    UIFormEnums yearTest = UIFormEnums.YEARTEST;

    _stringTest = UIFormEnums.STRINGTEST;

    UIFormBuilderInterface f = (UIFormBuilderInterface) fact.start("Form",null,null);
    f.add("Title", _stringTest.getUIFT());
    f.add("Year", yearTest.getUIFT());
    f.add("Director", _stringTest.getUIFT());
    _getVideoForm = f.toUIForm("Enter Video");

    new EnumConstructor(_ui, _inventory, _getVideoForm, _state);
  }
  
  void run() {
    try {
      while (_state != States.EXITED) {
        _ui.processMenu(_menus[_state.getValue()]);
        _state = UIMenuEnums.getState();
      }
    } catch (Error e) {
      _ui.displayError("UI closed");
    }
  }
  
  private void addSTART(States start) {

    UIMenuBuilderInterface m = (UIMenuBuilderInterface) fact.start("Menu",null,null);

    m.add("Default", UIMenuEnums.DEFAULT.getUIMA());
    m.add("Add/Remove copies of a video", UIMenuEnums.ADDREMOVE.getUIMA());
    m.add("Check in a video", UIMenuEnums.CHECKIN.getUIMA());
    m.add("Check out a video", UIMenuEnums.CHECKOUT.getUIMA());
    m.add("Print the inventory", UIMenuEnums.PRINT.getUIMA());
    m.add("Clear the inventory", UIMenuEnums.CLEAR.getUIMA());
    m.add("Undo", UIMenuEnums.UNDO.getUIMA());
    m.add("Redo", UIMenuEnums.REDO.getUIMA());
    m.add("Print top ten all time rentals in order", UIMenuEnums.TOPTEN.getUIMA());
    m.add("Exit", UIMenuEnums.EXIT.getUIMA());
    m.add("Initialize with bogus contents", UIMenuEnums.BOGUS.getUIMA());

    _menus[start.getValue()] = m.toUIMenu("Bob's Video");

  }

  private void addEXIT(States start) {
    UIMenuBuilderInterface m = (UIMenuBuilderInterface) fact.start("Menu", null, null);

    m.add("Default", UIMenuEnums.DEFAULT.getUIMA());
    m.add("Yes", UIMenuEnums.YES.getUIMA());
    m.add("No", UIMenuEnums.NO.getUIMA());

    _menus[start.getValue()] = m.toUIMenu("Are you sure you want to exit?");
  }
}