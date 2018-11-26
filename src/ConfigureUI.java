import resources.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigureUI extends JFrame implements Trigger {

    private static ConfigureUI configureUI;
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
     * List of menu actions of the menu bar
     * */
    private JMenuItem add;
    private JMenuItem check;
    private JMenuItem about;
    private JMenuItem exit;

    /**
     * JPanel where items will be displayed on
     * */
    private JPanel itemBoard;

    /**
     *  Control panel of Price Watcher
     * */
    private JPanel controlPanel;

    /**
     * Control Panel action buttons
     * */
    private JButton checkPrice;
    private JButton addItem;

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

    /**
     * Main Constructor of ConfigureUI class
     * */
    protected ConfigureUI(){
        prepareGUI();
        showMessage("Welcome to Price Watcher");
        mainFrame.setVisible(true);
    }

    public ConfigureUI getUI(){
        return configureUI;
    }

    /**
     *  Prepares the GUI's layout
     * */
    private void prepareGUI(){
        setMainFrame();
        setCloser();
        setItemBoard();
        setStatusLabel();
        addControlPanel();
        //addControlPanelElements();
        addMenuBarElements();
        addMainFrameElements();

        mainFrame.setJMenuBar(menuBar);
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



    /**
     *  Sets the item board from where the items will be displayed
     */
    private void setItemBoard(){
        itemBoard = new JPanel();
        itemBoard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,16,0,16),
                BorderFactory.createLineBorder(Color.GRAY)));

        itemBoard.setLayout(new GridLayout(1,1));
        itemBoard.setSize(mainFrame.getMinimumSize());
    }

    /**
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

    /**
     * Adds the the menu items to the menu  and add the menus to the menu bar
     * */
    private void addMenuBarElements(){
        menuBar = new JMenuBar();
        resources = new Resources();

        menu = new JMenu();
        icon = resources.createImageIcon("menu", "Menu");
        menu.setIcon(icon);

        add = new JMenuItem("Add");
        icon = resources.createImageIcon("add", "Add Item");
        add.setIcon(icon);
        add.setActionCommand("Add");
        add.addActionListener(listener);

        check = new JMenuItem("Check");
        icon = resources.createImageIcon("check" , "Check Price");
        check.setIcon(icon);
        check.setActionCommand("Check");
        check.addActionListener(listener);

        about = new JMenuItem("About");
        icon = resources.createImageIcon("about" , "About");
        about.setIcon(icon);
        about.setActionCommand("About");
        about.addActionListener(listener);


        exit = new JMenuItem("Exit");
        icon = resources.createImageIcon("exit", "Exit");
        exit.setIcon(icon);
        exit.addActionListener(listener);

        menuBar.add(menu);
        menu.add(add);
        menu.add(check);
        menu.add(about);
        menu.add(exit);

    }

    /**
     * Adds the elements to the control panel
     * */
    private void addControlPanelElements(){

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        checkPrice = new JButton("Check");
        addItem = new JButton("Add");
        checkPrice.setActionCommand("Check");
        addItem.setActionCommand("Add");
        checkPrice.addActionListener(new Listener());
        addItem.addActionListener(new Listener());
        controlPanel.add(checkPrice);
        controlPanel.add(addItem);
    }

    /** Show briefly the given string in the message bar.
     *  Code by: Yoonsik Cheon
     *  */
    protected void showMessage(String msg) {
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

    public void triggerShowMessage(String msg){
        showMessage(msg);
    }

    public void setListener(Listener listener){
         this.listener = listener;
    }
    /**
     * Adds and Item
     */
    public void addItem(){
        showMessage("Item Added");
    }

    /**
     * Checks the price of the items added
     */
    public void checkPrice(){
        showMessage("Price Checked");
    }

    /**
     * Displays About the program
     */
    public void about(){
        showMessage("Display About");
    }

    /**
     * Exits the program with exit code 0
     */
    public void exit(){
        System.exit(0);
    }


}
