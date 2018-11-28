import javax.swing.*;

public class Main {
    private JFrame main;
    private UIBuilder UIBuilder;


    private Main() {
        //trigger = new UIBuilder();
        //trigger.setListener(new Listener());
        //main = trigger.getUI();
        UIBuilder = new UIBuilder();



    }

    public static void main(String[] args) {
        new Main();

    }
}
