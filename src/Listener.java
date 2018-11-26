import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *Listener for program
 * */
public class Listener implements ActionListener {


    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if (command.equals("Add")) {
            System.out.println("Add");
        } else if (command.equals("Check")) {
            System.out.println("Check");
        } else if (command.equals("About")) {
            System.out.println("About");
        } else if (command.equals("Exit")) {
            System.exit(0);
        }

    }
}