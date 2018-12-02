package item;

import java.util.LinkedList;
import java.util.List;

public class ItemManager  extends LinkedList<Item>{

    private LinkedList<Item> items;

    public ItemManager() {
        items = listSample();

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

    public LinkedList<Item> listSample(){
        LinkedList<Item> items = new LinkedList<>();
        items.push(new Item("Ghost In the Wires","https://www.amazon.com/Ghost-Wires-Adventures-Worlds-Wanted/dp/0316037729/" ,"4/24/12","gitw"));
        items.push(new Item("Snow Crash","https://www.amazon.com/Snow-Crash-Neal-Stephenson/dp/0553380958" ,"4/02/00","snow-crash"));
        return items;
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