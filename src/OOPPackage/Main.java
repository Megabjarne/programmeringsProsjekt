package OOPPackage;

/**
 *
 * @author Thomas Sund Mjåland
 */

/**
 * Runs the menu, and when the menu exits, display a goodbye-message
 */
public class Main {
    public static void main(String[] args) {
        MainMenu.run();
        System.out.println("Thank you for participating in this Aperture Science computer-aided enrichment activity.\nGoodbye.\n\nYou monster.");
        //for reference: "https://theportalwiki.com/wiki/GLaDOS_voice_lines"
    }
}
