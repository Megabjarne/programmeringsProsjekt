package OOPPackage;

import java.util.Scanner;

/**
 * @author Thomas Sund Mjåland
 */
public abstract class Menu {
    
    /**
     * Abstract method that runs the menu-instance it is called on
     */
    public abstract void run();
    
    /**
     * Name of the Menu-instance, in order to textually represent the menu/action in a sub-menu
    */
    private final String itemName;
    
    /**
     * default constructor, name of the item is obligatory
     * @param name, the name to be used to textually represent the object
     */
    public Menu(String name){
        itemName=name;
    }
    
    /**
     * Returns the textual representation of the object
     * @return the given name of the menu
     */
    public String getName(){
        return itemName;
    }
    
    /**
     * Menu-item representing a menu of options
     */
    public static class SubMenu extends Menu{
        String subMenuTitle;    //The title to be shown at the top of the menu
        Menu menuItems[];       //the menus to be shown as options in this menu
        boolean hasExit=false;  //whether a "n. Exit" should be shown as an option
        boolean fallsThrough=true;  //wether this menu should return once an option has been selected and finished running
        
        /**
         * Constructor for creating a new sub-menu
         * @param name, the name shown when this menu is an option in another sub-menu
         * @param subMenuTitle, the title to be shown at the top of the menu when shown
         * @param menuItems, list of all menus to be shown as options in this menu
         */
        public SubMenu(String name, String subMenuTitle, Menu... menuItems){
            super(name);
            this.subMenuTitle=subMenuTitle;
            this.menuItems=menuItems;
        }
        
        /**
         * Used to optionally turn on/off the "n. Exit" option on the bottom of the menu when shown
         * @param hasExit, true if the menu should show an exit-option, false if not
         * @return Itself
         */
        public SubMenu setExit(boolean hasExit){
            this.hasExit=hasExit;
            return this;
        }
        
        /**
         * Used to optionally turn on/off the "fallsThrough", fallsThrough being that the menu returns after an option has been chosen and finishes
         * @param fallsThrough
         * @return Itself
         */
        public SubMenu setFallsThrough(boolean fallsThrough){
            this.fallsThrough=fallsThrough;
            return this;
        }
        
        /**
         * Overrides the "run()" of Menu to display a list of options and taking in input from user
         */
        @Override
        public void run(){
            Scanner input = new Scanner(System.in);
            boolean done=false;
            
            while (!done){
                System.out.print("\n\n");
                System.out.println("----- " + subMenuTitle + " -----");
                for (int i=0;i<menuItems.length;i++){
                    System.out.println(Integer.toString(i+1) + ": " + menuItems[i].getName());  //lists all the items like this: "n. [itemName]\n"
                }
                if (hasExit){
                    System.out.println(Integer.toString(menuItems.length+1) + ": " + "Exit");   //if hasexit is set, also append an "n. Exit\n" to the end
                }
                System.out.print(" > ");    //prompts the user for input
                String selection="";
                while (selection.equals("")){
                    selection = input.nextLine().replaceAll("[^\\d]", "");  //removes all characters that are not numeric
                }
                int selectedIndex = Integer.parseInt(selection)-1;  //from a 1-based index to a 0-based
                if (selectedIndex>=0 && selectedIndex<menuItems.length){
                    menuItems[selectedIndex].run(); //runs the selected menu-object
                    done=fallsThrough;      //if fallsThrough is set true, then it will now exit/return
                }else if( hasExit && selectedIndex==menuItems.length ){
                    done=true;  //if selection 
                }else{
                    System.out.println("The selected item is outside this menu's bounds");
                }
            }
        }
    }
    
    /**
     * abstract action-class, could optionally be switched to storing and executing a Runnable, but really, why would you ever do that?
     */
    public static abstract class ActionItem extends Menu {
        /**
         * constructor
         * @param name, name do be displayed when object is to be listed in a sub-menu
         */
        public ActionItem(String name){
            super(name);
        }
    };
}
