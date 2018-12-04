import javax.swing.*;
import java.util.HashMap;

public class ActionMap extends HashMap<String, String[]>{
    private HashMap<String, String[]> mapOfActions;
    ActionMap(){
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
    }

    public HashMap<String, String[]> getMapOfActions(){
        return this.mapOfActions;
    }

}
