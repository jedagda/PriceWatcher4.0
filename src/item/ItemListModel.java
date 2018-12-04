package item;

import javax.swing.*;

public class ItemListModel extends DefaultListModel<Item>{

    private DefaultListModel<Item> itemListModel;

    public ItemListModel(){

    }

    public ItemListModel(DefaultListModel<Item> newListModel){
        this.itemListModel = newListModel;
    }



}
