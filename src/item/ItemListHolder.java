package item;

public class ItemListHolder {

    private ItemManager itemManager = new ItemManager ();

    public ItemManager getItemManager(){
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager){
        this.itemManager = itemManager;
    }


}
