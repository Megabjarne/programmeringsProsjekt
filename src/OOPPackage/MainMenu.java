package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class MainMenu {
    static void run(){
        Menu addFlight = new Menu.ActionItem("add flight"){
            @Override
            public void run(){
                
            }
        };
        Menu addTicket = new Menu.ActionItem("add ticket"){
            @Override
            public void run(){
                System.out.println("Oh bugger off you sod!");
            }
        };
        Menu selfDestruct = new Menu.ActionItem("initiate self-destruct"){
            @Override
            public void run(){
                int i=0;
                i = 0/i;
            }
        };
        Menu rootMenu = new Menu.SubMenu("","Main menu", addFlight, addTicket, selfDestruct)
                .setExit(true)
                .setFallsThrough(false);
        rootMenu.run();
    }
}
