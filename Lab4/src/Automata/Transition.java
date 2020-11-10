package Automata;

public class Transition {
    private String value;
    private String startState;
    private String endState;

    public Transition(String startState, String endState, String value) {
        this.startState = startState;
        this.endState = endState;
        this.value = value;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
