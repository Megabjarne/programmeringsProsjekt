package OOPPackage;

import java.util.Scanner;

/**
 * @author Thomas Sund Mjåland
 */
public abstract class Menu {
    
    public abstract void run();
    private final String itemName;
    
    public Menu(String name){
        itemName=name;
    }
    
    public String getName(){
        return itemName;
    }
    
    public static class SubMenu extends Menu{
        String subMenuTitle;
        Menu menuItems[];
        boolean hasExit=false;
        boolean fallsThrough=true;
        public SubMenu(String name, String _subMenuTitle, Menu... _menuItems){
            super(name);
            subMenuTitle=_subMenuTitle;
            menuItems=_menuItems;
        }
        public SubMenu setExit(boolean _hasExit){
            hasExit=_hasExit;
            return this;
        }
        public SubMenu setFallsThrough(boolean _fallsThrough){
            fallsThrough=_fallsThrough;
            return this;
        }
        public void run(){
            Scanner input = new Scanner(System.in);
            boolean done=false;
            
            while (!done){
                System.out.print("\n\n");
                System.out.println("----- " + subMenuTitle + " -----");
                for (int i=0;i<menuItems.length;i++){
                    System.out.println(Integer.toString(i+1) + ": " + menuItems[i].getName());
                }
                if (hasExit){
                    System.out.println(Integer.toString(menuItems.length+1) + ": " + "Exit");
                }
                System.out.print(" > ");
                String selection="";
                while (selection.equals("")){
                    selection = input.nextLine().replaceAll("[^\\d]", "");
                }
                int selectedIndex = Integer.parseInt(selection)-1;
                if (selectedIndex>=0 && selectedIndex<menuItems.length){
                    menuItems[selectedIndex].run();
                    done=fallsThrough;
                }else if( hasExit && selectedIndex==menuItems.length ){
                    done=true;
                }
            }
        }
    }
    public static abstract class ActionItem extends Menu {
        public ActionItem(String name){
            super(name);
        }
    };
}
