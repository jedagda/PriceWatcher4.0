import item.Item;
import item.ItemListModel;

import javax.swing.*;

public class Main {

    private UIBuilder UIBuilder;
    private ItemListModel itemListModel = new ItemListModel();
    private Item item;


    private Main() {

        //UIBuilder = new UIBuilder(itemManager);
        UIBuilder = new UIBuilder(itemListModel);




    }

    public static void main(String[] args) {

        new Main();

    }
}
