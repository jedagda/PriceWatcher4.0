import item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ItemRenderer extends ItemView implements ListCellRenderer<Item> {

    public ItemRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        if(isSelected){
            setBackground(new Color(177,183,254));


        } else{
            setBackground(Color.WHITE);
        }

        setItem(item);
        repaint();
        return this;

    }

    private void buildPopupMenu(JList list){
        JPopupMenu editMenu = new JPopupMenu();
        editMenu.add(("Update"));
        editMenu.add(("Edit"));
        editMenu.add(("Remove"));

        list.setComponentPopupMenu(editMenu);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3)
                    editMenu.show(list, e.getX(), e.getY());
                list.setBackground(new Color(166, 111, 166));
            }
        });

    }

}