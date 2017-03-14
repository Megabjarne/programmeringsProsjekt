/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOPPackage;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class TestingMain {
    public static void main(String[] args) {
        Menu thomasAction = new Menu.ActionItem("Thomas"){
            @Override
            public void run(){
                System.out.println("You selected THOMAS!!!");
                try{System.in.read();System.in.skip(1000);}catch(Exception ex){};
            }
        };
        Menu øyvindAction = new Menu.ActionItem("Øyvind"){
            @Override
            public void run(){
                System.out.println("You selected Øyvind!!!");
                try{System.in.read();System.in.skip(1000);}catch(Exception ex){};
            }
        };
        Menu bendikAction = new Menu.ActionItem("Bendik"){
            @Override
            public void run(){
                System.out.println("You selected Bendik!!!");
                try{System.in.read();System.in.skip(1000);}catch(Exception ex){};
            }
        };
        Menu nameMenu = new Menu.SubMenu("contributors","Contributors!",thomasAction,øyvindAction,bendikAction)
                .setExit(true)
                .setFallsThrough(true);
        Menu crashMenu = new Menu.ActionItem("Crash program"){
            @Override
            public void run(){
                int i=0;
                i = i/i;
            }
        };
        Menu mainMenu = new Menu.SubMenu("","Main menu",nameMenu, crashMenu)
                .setExit(true)
                .setFallsThrough(false);
        
        
        mainMenu.run();
    }
}
