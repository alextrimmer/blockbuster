package shop.main;

public enum States {

    EXITED(0), EXIT(1), START(2), NUMSTATES(10);

    private final int value;


    States(int value) {

        this.value = value;
    }


    public int getValue() {

        return value;
    }
}
