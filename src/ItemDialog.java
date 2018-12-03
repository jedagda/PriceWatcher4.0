

import item.Item;
import item.ItemListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemDialog extends JDialog {
    private JPanel bottom;
    private JButton addButton, cancelButton, okButton;
    private ItemDialogPanel dialogPanel;
    private JList itemJList;
    private ItemListModel itemListModel;
    int index;

   // private ItemManager itemManager;


    public ItemDialog(JFrame owner, JList itemJList, ItemListModel itemListModel){
        super(owner, true);

        this.itemJList = itemJList;
        this.itemListModel = itemListModel;
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
        dialogPanel = new ItemDialogPanel();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(dialogPanel, BorderLayout.CENTER);
    }

    public ItemDialog(JFrame owner, JList itemJList, ItemListModel itemListModel, int index){
        super(owner, true);
        this.itemJList = itemJList;
        this.itemListModel = itemListModel;
        this.index = index;
        setTitle("Edit: " + itemListModel.getElementAt(index).getName());
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        ButtonHandler bHandler = new ButtonHandler();
        okButton.addActionListener(bHandler);
        cancelButton.addActionListener(bHandler);
        bottom = new JPanel();
        bottom.add(okButton);
        bottom.add(cancelButton);
        bottom.setBorder(BorderFactory.createEtchedBorder());
        dialogPanel = new ItemDialogPanel(itemListModel.getElementAt(index).getName(), itemListModel.getElementAt(index).getURL() );
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(dialogPanel, BorderLayout.CENTER);
    }



    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            JButton button = (JButton) evt.getSource();
            String label = button.getText();
            if("Add".equals(label)){
                itemListModel.addElement(new Item(dialogPanel.nameField.getText(), dialogPanel.urlField.getText()));
                itemJList.repaint();
                System.out.println("Item Added");

            } else if("Ok".equals(label)){
                itemListModel.getElementAt(index).setName(dialogPanel.nameField.getText());
                itemListModel.getElementAt(index).setURL(dialogPanel.urlField.getText());
                itemJList.repaint();
                System.out.println("Item Edited");
            }
            dialogPanel.reset();
            setVisible(false);
        }

    }
}
