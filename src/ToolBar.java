
import javax.swing.*;
import java.awt.*;

import resources.Resources;

public class ToolBar extends JPanel {

    static final private String ADDITEM = "Add";
    static final private String CHECKPRICE = "Check";
    static final private String ABOUT = "About";
    //private ItemView itemView;;
    private ConfigureUI main;
    private Resources resources;
    private Icon icon;
    private  Listener listener;


    protected void setMain (ConfigureUI main){
        this.main = main;
    }

    
   // private AddItemDialog addItemDialog = new AddItemDialog();


    public ToolBar(){
        super(new BorderLayout());

        JToolBar toolBar = new JToolBar("Price Watcher ToolBar");
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
        button = makeNavigationButton("about", ABOUT, "View Information on applicaiton,", "View Info");
        toolBar.add(button);
    }

    protected JButton makeNavigationButton (String iconName, String actionCommand, String toolTipText, String altText){
        resources = new Resources();
        listener = new Listener();
        icon = resources.createImageIcon(iconName, toolTipText);
        JButton button = new JButton();
        button.setIcon(icon);
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(listener);
        return button;
    }


}
