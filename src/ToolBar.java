
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;



public class ToolBar extends JPanel implements ActionListener {

    static final private String ADDITEM = "add item";
    static final private String CHECKPRICE = "check price";
    static final private String INFO = "info";
    //private ItemView itemView;;
    private ConfigureUI main;

    public void setMain (ConfigureUI main){
        this.main = main;
    }

   // private AddItemDialog addItemDialog = new AddItemDialog();


    public ToolBar(){
        super(new BorderLayout());

        JToolBar toolBar = new JToolBar("Still draggable");
        addButtons(toolBar);
        //setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
       // add(scrollPane, BorderLayout.CENTER);
    }

    protected void addButtons (JToolBar toolBar){
        JButton button = null;
        button = makeNavigationButton("check", CHECKPRICE, "Checks price of Item", "Check Price");
        toolBar.add(button);
        button = makeNavigationButton("add", ADDITEM, "Adds an Item", "Add Item");
        toolBar.add(button);
        button.addActionListener(makeAddItemHandler());
        button = makeNavigationButton("info", INFO, "View Information on applicaiton,", "View Info");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton (String imageName, String actionCommand, String toolTipText, String altText){
        String imgLocation = "/resources/images/" + imageName + ".png";
        URL imageURL = ToolBar.class.getResource(imgLocation);

        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if(imageURL != null) {
            button.setIcon(new ImageIcon(imageURL, altText));
        } else {
            button.setText(altText);
            System.err.println("Resource not found: " +imgLocation);
        }
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(CHECKPRICE.equals(cmd)){
            checkButtonClicked(e);
        }
/*        else if(ADDITEM.equals(cmd)){
            actionPerformed(e);
        } */
    }

    ActionListener makeAddItemHandler(){
        return new AddItemHandler();
    }



    private void checkButtonClicked(ActionEvent event) {

        //item.setPrice(priceCrawler.randomPrice());
        // itemView.repaint();
    }

    class AddItemHandler implements ActionListener {
        JDialog dialog;
        public void actionPerformed(ActionEvent evt){
            if(dialog == null){
               //dialog = new AddItemDialog(main);
            }
            dialog.setBounds(0,0,350,300);
            dialog.show();
        }

    }




}
