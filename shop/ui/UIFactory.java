package shop.ui;

public class UIFactory {
  public UIFactoryInterface start(String i, String head, Pair[] t){
    switch(i){
      case UIFactoryInterface.popUI:
        return new PopupUI();

      case UIFactoryInterface.textUI:
        return new TextUI();

      case UIFactoryInterface.UIFormBuilder:
        return new UIFormBuilder();

      case UIFactoryInterface.UIFormMenu:
        return new UIFormMenu(head, t);

      case UIFactoryInterface.UIMenuBuilder:
        return new UIMenuBuilder();

      default:
        return null;
    }
  }
}
