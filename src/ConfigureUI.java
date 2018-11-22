import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigureUI extends JFrame {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar menuBar;


    public ConfigureUI(){
        prepareGUI();
    }

    public static void main(String[] args){
        ConfigureUI congfiguteUI = new ConfigureUI();

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
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);
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

    private void addMenuBarElements(){
        JMenuItem file = new JMenuItem("File");
        JMenuItem help = new JMenuItem("Help");
        menuBar.add(file);
        menuBar.add(help);
    }

    private void addMainFrameElements(){
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
    }

    private void addControlPanelElements(){
        JButton checkPrice = new JButton("Check");
        JButton addItem = new JButton("Add");
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
        addMainFrameElements();
        addControlPanelElements();
        addMenuBarElements();
        mainFrame.setVisible(true);

    }

    private class CheckButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            if (command.equals("Check")) {
                statusLabel.setText("Price Checked");
            }
        }
    }

    private class AddItemButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent){
            String command = actionEvent.getActionCommand();
            if(command.equals("Add")) {
                statusLabel.setText("Item Added");
            }
        }
    }



}
