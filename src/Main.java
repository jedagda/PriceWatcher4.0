import javax.swing.*;

public class Main {
    private JFrame main;
    private Trigger trigger;
    private ConfigureUI configureUI;


    private Main() {
        //trigger = new ConfigureUI();
        //trigger.setListener(new Listener());
        //main = trigger.getUI();
        configureUI = new ConfigureUI();
        configureUI.setListener(new Listener());
        main = configureUI.getUI();



    }

    public static void main(String[] args) {
        new Main();

    }
}
