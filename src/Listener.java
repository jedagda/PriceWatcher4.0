import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Listener for program
 * */
public class Listener implements ActionListener {

    private ConfigureUI main;

    protected void setMain (ConfigureUI main){
        this.main = main;
    }


    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals("Add")) {
            addItemDialog(actionEvent);
        } else if (command.equals("Check")) {
            System.out.println("Check");
        } else if (command.equals("About")) {
            aboutAppDialog(actionEvent);
        } else if (command.equals("Exit")) {
            System.exit(0);
        }

    }

    JDialog dialog;
    public void addItemDialog(ActionEvent evt){
        if(dialog == null){
            dialog = new AddItemDialog(main);
        }
        dialog.setBounds(0,0,350,300);
        dialog.show();
    }

    public void aboutAppDialog(ActionEvent evt){
        if(dialog == null){
            dialog = new AboutAppDialog(main);
        }
        dialog.setBounds(0,0,350,300);
        dialog.show();
    }
}