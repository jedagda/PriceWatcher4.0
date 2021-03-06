import controller.PriceCrawler;
import controller.WebPriceCrawler;
import item.Item;
import item.ItemListModel;
import resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;


public class UIBuilder extends JFrame{

    static final private String ADDITEM = "Add";
    static final private String CHECKPRICE = "Check";
    static final private String ABOUT = "About";
    static final private String EXIT = "Exit";
    static final private String REMOVE = "Remove";
    static final private String UPDATE = "Update";
    static final private String EDIT = "Edit";
    static final private String SORT = "SORT";
    static final private String [] BUTTONS =  {ADDITEM, REMOVE, UPDATE, EDIT, CHECKPRICE, SORT, ABOUT, EXIT};


    private  WebPriceCrawler crawler;
    /** The main frame of Price Watcher 4.0 */
    private JFrame mainFrame;
    /** The menu bar */
    private JMenuBar menuBar;
    /** A menu */
    private JMenu menu;

    /**
     * The Tool Bar
     */
     ToolBar toolBar;


    /**
     * JPanel where items will be displayed on
     * */
    private JPanel itemBoard;

    /**
     *  Control panel of Price Watcher
     * */
    private JPanel controlPanel;


    /**
     * Messages will be displayed
     * */
    private JLabel msgBar;

    /**
     *  Calls the Resources class
     *  */
    private Resources resources;

    /**
     *  An Icon image
     * */
    private Icon icon;

    private Listener listener = new Listener();

    private HashMap<String, String[]> mapOfActions;

    private PopupMenu pop = new PopupMenu();


    private ItemListModel itemListModel;


    private void setItemListModel(ItemListModel itemListModel){
        this.itemListModel = itemListModel;
    }
    /**
     * Main Constructor of UIBuilder class
     * */
    protected UIBuilder(ItemListModel itemListModel) {
        setItemListModel(itemListModel);
        mapOfActions = actionMapLoader();
        prepareGUI();

        showMessage("Welcome to Price Watcher");
        mainFrame.setVisible(true);

    }

    /**
     *  Prepares the GUI's layout
     * */
    private void prepareGUI() {
        setMainFrame();
        setCloser();
        setItemBoard();
        setStatusLabel();
        addControlPanel();
        addMenuBarElements();
        addMainFrameElements();
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     *  Sets the item board from where the items will be displayed
     */
    private JList<Item> itemJList = new JList<>();
    private void setItemBoard() {
        itemBoard = new JPanel();
        itemBoard.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,16,0,16), BorderFactory.createEmptyBorder()));
        itemJList = new JList<>(itemListModel);
        itemJList.setFixedCellHeight(120);
        itemJList.setCellRenderer(new ItemRenderer());
        itemBoard.add(itemJList);
        itemBoard.add(new JScrollPane(itemJList));
        buildPopupMenu(itemJList);
        itemBoard.setLayout(new GridLayout(1,1));
    }

    /**
     * Set's the main frame's title, dimensions, and layout
     * */
    private void setMainFrame(){
        mainFrame = new JFrame("Price Watcher 4.0");
        mainFrame.setSize(400, 600);
        mainFrame.setLayout(new BorderLayout());
    }

    /**
     * Sets the closer on the window
     * */
    private void setCloser(){
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void addControlPanel(){
        controlPanel = makeControlPanel();
        controlPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 16));
    }

    private JPanel makeControlPanel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        toolBar = new ToolBar();
        toolBar.setMain(this);

        panel.add(toolBar);
        return panel;
    }



    private void buildPopupMenu(JList jList){
        JPopupMenu editMenu = new JPopupMenu();

        editMenu.add(manufactureMenuItem("Update", mapOfActions));
        editMenu.add(manufactureMenuItem("Edit",mapOfActions));
        editMenu.add(manufactureMenuItem("Remove", mapOfActions));

        jList.setComponentPopupMenu(editMenu);

        jList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JList theList = (JList) e.getSource();
                if(e.getButton() == MouseEvent.BUTTON3){
                    editMenu.show(jList, e.getX(), e.getY());
                }

            }
        });

    }

    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked() {
        try {
            Desktop desktop = Desktop.getDesktop();
         //   URI oURL = new URI(item.getURL());
         //   desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        showMessage("View clicked!");
    }


    /**    private Trigger trigger;
     * Sets the Status label which indicates what actions the program has taken
     * */
    private void setStatusLabel(){
        msgBar = new JLabel("",JLabel.CENTER);
        msgBar.setSize(350,50);
    }

    /**
     * Adds the elements to the main frame
     */
    private void addMainFrameElements(){
        mainFrame.add(controlPanel, BorderLayout.NORTH);
        mainFrame.add(itemBoard,BorderLayout.CENTER);
        mainFrame.add(msgBar,BorderLayout.SOUTH);
    }

    private HashMap<String, String[]> actionMapLoader(){
        mapOfActions = new HashMap<>();
        mapOfActions.put("Add", new String[]{"add" , "Add Item" , "Add", "Adds an Item"});
        mapOfActions.put("Remove", new String[]{"remove", "Remove Item" , "Remove","Removes an Item"});
        mapOfActions.put("Edit", new String[]{"edit", "Edit Item", "Edit", "Edits an Item"});
        mapOfActions.put("Update",new String[]{"update", "Update Item" , "Update", "Updates a single item"});
        mapOfActions.put("Check", new String[]{"check", "Check Price", "Check", "Checks price of all items"});
        mapOfActions.put("Sort", new String[]{"sort", "Sort by Price" , "Sort", "Sort the Items by Price"});
        mapOfActions.put("About", new String[]{"about", "About App" , "About", "About the application"});
        mapOfActions.put("Exit", new String[]{"exit", "Exit Application", "Exit", "Exit Application"});
        mapOfActions.put("Okay", new String[]{"okay", "Okay", "Okay", "Okay"});
        mapOfActions.put("Cancel", new String[]{"cancel", "Cancel", "Cancel", "Cancel"});
        return mapOfActions;
    }

    private JButton manufactureButton(String buttonName, HashMap<String, String[]> mapOfButtons) {
        String[] buttonInfo = mapOfButtons.get(buttonName);
        JButton button = new JButton();
        button.setIcon(new Resources().createImageIcon(buttonInfo[0], buttonInfo[1])); //Icon Name , Alt Text
        button.setActionCommand(buttonInfo[2]); //action command
        button.setToolTipText(buttonInfo[3]); //Tool Tip Text
        button.addActionListener(new Listener());
        return button;
    }

    private JMenuItem manufactureMenuItem(String menuItemName, HashMap<String, String[]> mapOfMenuItems ){
        String [] menuItemInfo = mapOfMenuItems.get(menuItemName);
        JMenuItem menuItem = new JMenuItem(menuItemInfo[2]); //name of Menu Item
        menuItem.setIcon(new Resources().createImageIcon(menuItemInfo[0], menuItemInfo[1])); //Icon Name , Alt Text
        menuItem.setActionCommand(menuItemInfo[2]); //Set Action Command
        menuItem.addActionListener(new Listener());
        return menuItem;
    }

    private JButton makeNavigationButton (String iconName, String actionCommand, String toolTipText, String altText){
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

    /**
     * Adds the the menu items to the menu  and add the menus to the menu bar
     * */
    private void addMenuBarElements(){
        menuBar = new JMenuBar();
        resources = new Resources();

        menu = new JMenu();
        icon = resources.createImageIcon("menu", "Menu");
        menu.setIcon(icon);
        menuBar.add(menu);

        menu.add(manufactureMenuItem("Add", mapOfActions));
        menu.add(manufactureMenuItem("Check", mapOfActions));
        menu.add(manufactureMenuItem("Sort",mapOfActions));
        menu.add(manufactureMenuItem("About", mapOfActions));
        menu.add(manufactureMenuItem("Exit", mapOfActions));

    }

    /** Show briefly the given string in the message bar.
     *  Code by: Yoonsik Cheon
     *  */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000); // 3 seconds
            } catch (InterruptedException e) {
            }
            if (msg.equals(msgBar.getText())) {
                SwingUtilities.invokeLater(() -> msgBar.setText(" "));
            }
        }).start();
    }


    private class PopupMenu extends JPopupMenu{

        JPopupMenu popupMenu;

        PopupMenu(){
            Container c = getContentPane();
            popupMenu = new JPopupMenu("Edit");

            popupMenu.add(new JMenuItem("Add"));
           // popupMenu.add(manufactureMenuItem("Remove", mapOfActions));
          //  popupMenu.add(manufactureMenuItem("Edit", mapOfActions));

            c.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    showPopup(e);
                }
            });
        }

        void showPopup(MouseEvent e) {
            if (e.isPopupTrigger())
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }





        private class ToolBar extends JPanel {

        //private ItemView itemView;;
        private UIBuilder main;
        private Resources resources;
        private Icon icon;
        private  Listener listener;


        protected void setMain (UIBuilder main){
            this.main = main;
        }


        // private ItemDialog addItemDialog = new ItemDialog();


        public ToolBar(){
            super(new BorderLayout());

            JToolBar toolBar = new JToolBar("Price Watcher ToolBar");
            addButtons(toolBar);
            //setPreferredSize(new Dimension(450, 130));
            add(toolBar, BorderLayout.PAGE_START);
           // addItemDialog(scrollPane, BorderLayout.CENTER);
        }

        protected void addButtons (JToolBar toolBar){
            toolBar.add(manufactureButton("Check", mapOfActions));
            toolBar.add(manufactureButton("Add", mapOfActions));
            toolBar.add(manufactureButton("Sort", mapOfActions));
            toolBar.add(manufactureButton("About", mapOfActions));
        }


    /*    class AddItemHandler implements ActionListener {
            JDialog dialog;
            public void actionPerformed(ActionEvent evt){
                if(dialog == null){
                    dialog = new ItemDialog(main, itemManager);
                }
                dialog.setBounds(0,0,350,300);
                dialog.show();
            }

        }*/


    }

    private class Listener implements ActionListener {

        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Add")) {

                addItemDialog(actionEvent);
                showMessage("Item Added");

            } else if(command.equals("Remove")) {

                int index = itemJList.getSelectedIndex();
                itemListModel.removeElementAt(index);

                showMessage("Item Removed");

            } else if (command.equals("Edit")){
                int index = itemJList.getSelectedIndex();
                editItemDialog(actionEvent);
                showMessage("Item Edited");



            } else if (command.equals("Check")) {

                PriceCrawler priceCrawler = new PriceCrawler();
                for (int i = 0; i < itemListModel.getSize(); i++) {
                    itemListModel.getElementAt(i).setPrice(priceCrawler.randomPrice());
                    System.out.println(itemListModel.getElementAt(i).getPrice());

                }
                itemJList.repaint();
                showMessage("New Price Obtained");

            } else if(command.equals("Update")) {
                PriceCrawler priceCrawler = new PriceCrawler();
                int index = itemJList.getSelectedIndex();
                try {
                    crawler = new WebPriceCrawler(itemListModel.getElementAt(index).getURL());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                itemListModel.getElementAt(index).setPrice(crawler.getInitialPrice());
                showMessage("New Price Obtained for: " + itemListModel.getElementAt(index).getName());

            } else if (command.equals("Sort")){
                ItemListModel sortedItemListModel = new ItemListModel();
                System.out.println("Sort");
                for(int i = 0; i < itemListModel.getSize(); i++){
                    System.out.println(i);
                    for(int j = 1; j < itemListModel.getSize(); j++){
                        if(itemListModel.getElementAt(i).getPrice() > itemListModel.getElementAt(j).getPrice()){
                            System.out.println("yes");
                            sortedItemListModel.addElement(itemListModel.getElementAt(i));
                        }
                    }
                }
                itemListModel = new ItemListModel(sortedItemListModel);
                itemJList.repaint();



            } else if (command.equals("About")) {
                aboutAppDialog(actionEvent);
                showMessage("Display About");
            } else if (command.equals("Exit")) {
                System.exit(0);
            }

        }


        JDialog dialog;
        private void editItemDialog(ActionEvent evt){
            int index = itemJList.getSelectedIndex();
            ItemDialog editItemDialog = new ItemDialog(UIBuilder.this, itemJList, itemListModel, index);
            editItemDialog.setBounds(0,0,350,300);
            editItemDialog.show();
        }
        private void addItemDialog(ActionEvent evt){
            ItemDialog itemDialog = new ItemDialog(UIBuilder.this, itemJList, itemListModel);
            if(dialog == null){
                dialog = itemDialog;
            }
            dialog.setBounds(0,0,350,300);
            dialog.show();
        }

        private void aboutAppDialog(ActionEvent evt){
            if(dialog == null){
                dialog = new AboutAppDialog(mainFrame);
            }
            dialog.setBounds(0,0,350,300);
            dialog.show();
        }
    }



}
