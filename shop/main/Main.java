package shop.main;

import shop.ui.UIFactory;
import shop.ui.UI;
import shop.data.Data;

public class Main {
  private Main() {}
  public static void main(String[] args) {
    UIFactory fact = new UIFactory();
    Control control = new Control(Data.newInventory(), (UI) fact.start("popup",null,null));
    control.run();
  }
}
