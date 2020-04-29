package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI, UIFactoryInterface {
  PopupUI() {}

  public void displayMessage(String message) {
    JOptionPane.showMessageDialog(null,message);
  }

  public void displayError(String message) {
    JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void processMenu(UIFormMenuInterface menu) {
    StringBuffer b = new StringBuffer();
    b.append(menu.getHeading());
    b.append("\n");
    b.append("Enter choice by number:");
    b.append("\n");

    for (int i = 1; i < menu.size(); i++) {
      b.append("  " + i + ". " + menu.getPrompt(i));
      b.append("\n");
    }

    String response = JOptionPane.showInputDialog(b.toString());
    int selection;
    try {
      selection = Integer.parseInt(response, 10);
      if ((selection < 0) || (selection >= menu.size()))
        selection = 0;
    } catch (NumberFormatException e) {
      selection = 0;
    }

    menu.runAction(selection);
  }

  public String[] processForm(UIFormMenuInterface form) {
    String[] f2 = new String[form.size()];
    int count = 0;
    while (count < form.size()) {
      String prompt = JOptionPane.showInputDialog(form.getPrompt(count));
      if (prompt == null) {
        prompt = "";
      }
      if (!form.checkInput(count, prompt)) {
        displayError("Invalid input.");
      }
      else {
        f2[count] = prompt;
        ++count;
      }
    }
    return f2;
  }
}
