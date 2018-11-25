import resources.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;




public class ConfigureUI extends JFrame {

    private static ConfigureUI configureUI;

    private JFrame mainFrame;

    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem exit;
    private JMenuItem add;
    private JMenuItem about;
    private JPanel itemBoard = new JPanel();
    private final static String IMAGE_DIR = "/icons/";

    /** Message bar to display various messages. */
    //private JLabel msgBar = new JLabel(" ");

    private JPanel controlPanel;
    private JButton checkPrice;
    private JButton addItem;

    private JLabel headerLabel;
    private JLabel msgBar;

    private Resources image;


    private ConfigureUI(){
        prepareGUI();
        showMessage("Welcome");
        mainFrame.setVisible(true);
    }

    public static void main(String[] args){
        configureUI = new ConfigureUI();

    }

    private void setMainFrame(){
        mainFrame = new JFrame("Price Watecher 4.0");
        mainFrame.setSize(400, 600);
        mainFrame.setLayout(new GridLayout(4,1));
    }

    private void setHeaderLabel(){
        headerLabel = new JLabel("Sample Header", JLabel.CENTER );
    }

    private void setStatusLabel(){
        msgBar = new JLabel("",JLabel.CENTER);
        msgBar.setSize(350,50);
    }

    private void setCloser(){
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    private void setControlPanel(){
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
    }

    private void setMenuBar(){
        menuBar = new JMenuBar();
    }

    private void setItemBoard(){
        itemBoard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,16,0,16),
                BorderFactory.createLineBorder(Color.GRAY)));
        itemBoard.setSize(mainFrame.getMinimumSize());
        itemBoard.setLayout(new GridLayout(1,1));
    }

    private void addMenuBarElements(){
        file = new JMenu();
        Icon icon = createImageIcon("icons/menu.png", "Menu");
        file.setIcon(icon);
        help = new JMenu("Help");
        exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitListener());
        about = new JMenuItem("About");
        add = new JMenuItem("Add");

        menuBar.add(file);
        menuBar.add(help);

        file.add(add);
        file.add(exit);

        help.add(about);

    }

    protected ImageIcon createImageIcon (String path, String description){
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon (imgURL, description);
        } else {
            System.err.println("Could't find file " + path);
            return null;
        }
    }

    private void addMainFrameElements(){
 //       mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(itemBoard);
        mainFrame.add(msgBar);
    }

    private void addControlPanelElements(){
        checkPrice = new JButton("Check");
        addItem = new JButton("Add");
        checkPrice.setActionCommand("Check");
        addItem.setActionCommand("Add");
        checkPrice.addActionListener(new CheckButtonListener());
        addItem.addActionListener(new AddItemButtonListener());
        controlPanel.add(checkPrice);
        controlPanel.add(addItem);
    }



    private void prepareGUI(){
        setMainFrame();
        setMenuBar();
        setHeaderLabel();
        setStatusLabel();
        setCloser();
        setControlPanel();
        setItemBoard();
        addMainFrameElements();
        addControlPanelElements();
        addMenuBarElements();
        mainFrame.setJMenuBar(menuBar);


    }

    /** Show briefly the given string in the message bar. */
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

    private class CheckButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Check")) {
               showMessage("Price Checked");
            }
        }
    }

    private class AddItemButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            String command = actionEvent.getActionCommand();
            if(command.equals("Add")) {
                showMessage("Item Added");
            }
        }
    }

    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Exit")) {
                System.exit(0);
            }
        }
    }

    public Image getImage(String file) {
        try {
            URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
