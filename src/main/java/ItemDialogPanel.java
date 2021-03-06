import javax.swing.*;
import java.awt.*;

public class ItemDialogPanel extends JPanel {
    JLabel nameLabel;
    JTextField nameField;

    JLabel urlLabel;
    JTextField urlField;

   // JLabel initialPriceLabel;
   // JTextField initialPriceField;


    JLabel imageNameLabel;
    JTextField imageNameField;

    public void setNameLabel(String name){
        this.nameField = new JTextField(name);
    }

    ItemDialogPanel() {

        nameLabel = new JLabel("Item Name");
        nameField = new JTextField();

        urlLabel = new JLabel("Link");
        urlField = new JTextField();

        add(nameLabel);
        add(nameField);

        add(urlLabel);
        add(urlField);
    }

    ItemDialogPanel(String editName, String editURL) {
        nameLabel = new JLabel("Item Name");
        nameField = new JTextField(editName);

        urlLabel = new JLabel("Link");
        urlField = new JTextField(editURL);

        add(nameLabel);
        add(nameField);

        add(urlLabel);
        add(urlField);

    }
    public Dimension getPreferredSize(){
        return new Dimension(350, 200);
    }

    public Dimension getMinimumSize(){
        return new Dimension(350, 200);
    }

    public void doLayout(){
        nameLabel.setBounds(20,10, 80,30);
        nameField.setBounds(120,15, 200,30);

        urlLabel.setBounds(20,40, 80,30);
        urlField.setBounds(120,45, 200,30);

    }

    public void reset() {
        nameField.setText("");
        urlField.setText("");
    }


}

