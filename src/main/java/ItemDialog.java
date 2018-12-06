

import controller.WebPriceCrawler;
import item.Item;
import item.ItemListModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ItemDialog extends JDialog {
    private JPanel bottom;
    private JButton addButton, cancelButton, okButton, checkButton;
    private ItemDialogPanel dialogPanel;
    private JList itemJList;
    private ItemListModel itemListModel;
    private int index;
    private WebPriceCrawler crawler;

   // private ItemManager itemManager;

    public ItemDialog(JFrame owner, JList itemJList, ItemListModel itemListModel){
        super(owner, true);

        this.itemJList = itemJList;
        this.itemListModel = itemListModel;
        setTitle("Add New Item");
        checkButton = new JButton("Check");
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");
        ButtonHandler bHandler = new ButtonHandler();
        checkButton.addActionListener(bHandler);
        addButton.addActionListener(bHandler);
        cancelButton.addActionListener(bHandler);
        bottom = new JPanel();
        bottom.add(checkButton);
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
        checkButton = new JButton("Check");
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        ButtonHandler bHandler = new ButtonHandler();
        checkButton.addActionListener(bHandler);
        okButton.addActionListener(bHandler);
        cancelButton.addActionListener(bHandler);
        bottom = new JPanel();
        bottom.add(checkButton);
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

            if("Check".equals(label)){
                try {
                    crawler = new WebPriceCrawler(dialogPanel.urlField.getText());
                    System.out.println(crawler.getItemName());
                    dialogPanel.nameField.setText(crawler.getItemName());
                } catch (IOException e) {
                    System.out.println("Enter a correct URL");
                    e.printStackTrace();
                }
            }
            else if("Add".equals(label)){
                itemListModel.addElement(new Item(dialogPanel.nameField.getText(), dialogPanel.urlField.getText(), crawler.getInitialPrice()));
               // itemListModel.addElement(new Item(dialogPanel.nameField.getText(), dialogPanel.urlField.getText()));
                itemJList.repaint();
                System.out.println("Item Added");
                dialogPanel.reset();
                setVisible(false);

            } else if("Ok".equals(label)){
                itemListModel.getElementAt(index).setName(dialogPanel.nameField.getText());
                itemListModel.getElementAt(index).setURL(dialogPanel.urlField.getText());
                itemJList.repaint();
                System.out.println("Item Edited");
                dialogPanel.reset();
                setVisible(false);
            }

        }

    }
}
