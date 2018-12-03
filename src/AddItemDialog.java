

import item.Item;
import item.ItemListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddItemDialog extends JDialog {
    private JPanel bottom;
    private JButton addButton, cancelButton;
    private AddItemDialogPanel dialogPanel;
    private JList itemJList;
    private ItemListModel itemListModel;

   // private ItemManager itemManager;


    public AddItemDialog(JFrame owner, JList itemJList, ItemListModel itemListModel){
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
        dialogPanel = new AddItemDialogPanel();
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

            }
            dialogPanel.reset();
            setVisible(false);
        }

    }
}
