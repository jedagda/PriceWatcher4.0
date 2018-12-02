import item.ItemManager;

import javax.swing.*;

public class Main {
    private JFrame main;
    private UIBuilder UIBuilder;
    private ItemManager itemManager = new ItemManager();


    private Main() {

        UIBuilder = new UIBuilder(itemManager);




    }

    public static void main(String[] args) {
        new Main();

    }
}
