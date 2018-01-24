
/**
 * TypeFasterer
 *
 * @author Shawn Patel and Bryan Kim
 * @version 1.0
 */
import java.util.Scanner;

public class TypeFasterer {
    public static void main(String [] args) {
        menu(); 
    }

    private static void menu() {
        Scanner keyIn = new Scanner(System.in);

        System.out.println("Welcome to TyperFasterer!");
        System.out.println("TyperFasterer tests your typing speed and accuracy. Choose from the two options below.");
        System.out.println("1. Paragraph");
        System.out.println("2. Words");

        String menuOption = keyIn.nextLine().toLowerCase();
        if (menuOption.indexOf("1") >= 0 || menuOption.indexOf("paragraph") >= 0) {
            System.out.println("You chose paragraph! Are you ready? (The timer will begin once you say yes.)");

            String ready = keyIn.nextLine();
            while (ready.compareTo("yes") != 0) {
                System.out.println("Are you ready now?");
                ready = keyIn.nextLine();
            }
            paragraph();
        } else if (menuOption.indexOf("2") >= 0 || menuOption.indexOf("words") >= 0) {
            System.out.println("You chose words! Are you ready? (The timer will begin once you say yes.)");

            String ready = keyIn.nextLine();
            while (ready.compareTo("yes") != 0) {
                System.out.println("Are you ready now?");
                ready = keyIn.nextLine();
            }
            words();
        } else {
            menu();
        }
    }

    private static void paragraph() {
        System.out.println("This is a paragraph.");
    }

    private static void words() {
        System.out.println("This is a word.");
    }
}
