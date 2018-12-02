

import item.Item;
import item.ItemManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemDialog extends JDialog {
    private JPanel bottom;
    private JButton addButton, cancelButton;
    private AddItemDialogPanel dialogPanel;
    private UIBuilder ui;
    private ItemManager itemManager;
    private JList itemList;
    private DefaultListModel<Item> listModel;

   // private ItemManager itemManager;


    public AddItemDialog(JFrame owner, ItemManager itemManager, JList itemList, DefaultListModel<Item> listMode){
        super(owner, true);
        this.itemManager= itemManager;
        this.itemList = itemList;
        this.listModel = listMode;
        setTitle("Add New Item");
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        ButtonHandler bHandler = new ButtonHandler();
        addButton.addActionListener(bHandler);
        cancelButton.addActionListener(bHandler);
        bottom = new JPanel();
        bottom.add(addButton);
        bottom.add(cancelButton);
        bottom.setBorder(BorderFactory.createEtchedBorder());
        dialogPanel = new AddItemDialogPanel();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(dialogPanel, BorderLayout.CENTER);
    }

    public ItemManager setItemManager(){
        return this.itemManager;
    }

/*    public void printList(ItemManager itemManager){
        for (int i = 0; i < itemManager.count(); i++ ){
            System.out.println(itemManager.getItemAtI(i).getName());
        }
    } */
    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            JButton button = (JButton) evt.getSource();
            String label = button.getText();
            if("Add".equals(label)){
                itemManager.addItem(new Item(dialogPanel.nameField.getText(), dialogPanel.urlField.getText()));
                listModel.addElement(new Item(dialogPanel.nameField.getText(), dialogPanel.urlField.getText()));
                itemList.repaint();
                System.out.println("Item Added");

            }
            dialogPanel.reset();
            setVisible(false);
        }

    }
}
