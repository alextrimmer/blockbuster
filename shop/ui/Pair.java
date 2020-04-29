package shop.ui;

final class Pair <P> {
    String prompt;
    UIMenuAction action;
    UIFormTest test;

    Pair(String prompt, P t){
        this.prompt = prompt;
        if(t instanceof UIMenuAction)
            action = (UIMenuAction) t;
        else if(t instanceof UIFormTest)
            test = (UIFormTest) t;
        else
            throw new IllegalArgumentException();
    }
}
