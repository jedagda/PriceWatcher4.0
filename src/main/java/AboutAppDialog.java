import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutAppDialog extends JDialog {
    private JPanel bottom;
    private JButton okButton;
    private Main main;

    // private ItemManager itemManager;

    public void setMain(Main main) {
        this.main = main;
    }

    public AboutAppDialog(JFrame owner) {
        super(owner, true);
        //   main =(Main)owner;
        //   itemManager = main.getItemManager();
        setTitle("About");
        JLabel cool = new JLabel("I am a label");
        bottom = new JPanel();
        bottom.add(okButton = new JButton("Okay"));
        ButtonHandler bHandler = new ButtonHandler();
        okButton.addActionListener(bHandler);
        bottom.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(bottom, BorderLayout.SOUTH);
        getContentPane().add(cool, BorderLayout.CENTER);
    }


    class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent evt){
            setVisible(false);
        }

    }
}


