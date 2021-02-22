package project.logic.basic;

public class Command {

    private String name;
    private int numberOfSugar = 0;
    private String message;
    private boolean isHot = false;


    public void setName(String name) {
        this.name = name;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumberOfSugar(int numberOfSugar) {
        this.numberOfSugar = numberOfSugar;
    }

    public boolean isHot() {
        return isHot;
    }

    public int getNumberOfSugar() {
        return numberOfSugar;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}

