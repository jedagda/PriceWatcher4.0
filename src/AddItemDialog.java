

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

   // private ItemManager itemManager;

    public void setUI (UIBuilder ui){
        this.ui = ui;
    }

    public AddItemDialog(JFrame owner){
        super(owner, true);
        ui =(UIBuilder)owner;
        itemManager = ui.getItemManager();
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
                itemManager.addItem(new Item(dialogPanel.nameField.getText(),
                        dialogPanel.urlField.getText()));
                System.out.println("Item Added");
                ui.setItemList(itemManager);

            }
            dialogPanel.reset();
            setVisible(false);
        }

    }
}
