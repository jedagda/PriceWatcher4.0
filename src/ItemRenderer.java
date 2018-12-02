import item.Item;

import javax.swing.*;
import java.awt.*;


public class ItemRenderer extends ItemView implements ListCellRenderer<Item> {

    public ItemRenderer(){
        setOpaque(true);

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        setItem(item);
        repaint();
        return this;

    }

}