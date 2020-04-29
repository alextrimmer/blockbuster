package shop.ui;

public interface UI {
  void processMenu(UIFormMenuInterface menu);
  String[] processForm(UIFormMenuInterface form);
  void displayMessage(String message);
  void displayError(String message);
}
