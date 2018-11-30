package item;

import java.util.LinkedList;
import java.util.List;

public class ItemManager {

    private List<Item> items;

    public ItemManager() {
        items = new LinkedList<>();
    }

    public int count() {
        // TODO Auto-generated method stub
        return items.size();
    }

    public Item addItem(String name, String url) {
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean contains(Item item) {
        return items.contains(item);
    }

    public Item getItemAtI(int i){
        return items.get(i);
    }

    public List<Item> getItems(){
        return this.items;
    }
}