package item;

import javax.swing.*;

public class ItemListModel extends DefaultListModel<Item>{

    private DefaultListModel<Item> itemListModel;

    public ItemListModel(){

    }

    public Item getItem(int index){
        return itemListModel.getElementAt(index);
    }

    public void addItem(String name, String url){
        itemListModel.addElement(new Item(name,url));
    }

    public void addItem(Item item){
        itemListModel.addElement(item);
    }

    public boolean containts(Item item){
        return itemListModel.contains(item.getName());
    }

    public void deleteItem(int index){
        itemListModel.removeElementAt(index);
    }

    public void deleteItem(Item item){
        itemListModel.removeElement(item);
    }

    public int size(){
        return itemListModel.size();
    }

    public void editName(int index, String name){
        getItem(index).setName(name);
    }

    public void editURL(int index, String url){
        getItem(index).setURL(url);
    }

    public void checkNewPrice(int index, double price){
        getItem(index).setPrice(price);
    }




}
