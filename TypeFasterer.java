
/**
 * TypeFasterer
 *
 * @author Shawn Patel and Bryan Kim
 * @version 1.0
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class TypeFasterer {
    private static ArrayList<String> words = new ArrayList<String>();
    private static ArrayList<String> ewords = new ArrayList<String>();

    public static void main(String [] args) {
        readDataFromFile();
        menu();
    }

    private static void readDataFromFile() {
        // The name of the file to open.
        String fileName = "words.txt";
        String fileName2 = "ewords.txt";

        // This will reference one line at a time
        String line = null;
        String line2 = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);
            FileReader fileReader2 = new FileReader(fileName2);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);

            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.trim());
            }

            while ((line2 = bufferedReader2.readLine()) != null) {
                ewords.add(line2.trim());
            }

            // Always close files.
            bufferedReader.close(); 
            bufferedReader2.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
        }
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
            System.out.println("BTW, please go to full screen!!\n");
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

    private static String [] paragraphs = {"If the Easter Bunny and the Tooth Fairy had babies would they take your teeth and leave chocolate for you? He told us a very exciting adventure story. I am happy to take your donation; any amount will be\ngreatly appreciated."};
    private static void paragraph() {
        int acc = 0;
        double wpm = 0;
        int randP = (int)(Math.random()*paragraphs.length);
        Scanner keyIn = new Scanner(System.in);
        String p = paragraphs[randP];
        String [] before = p.split(" ");
        int startTime = (int) System.currentTimeMillis();
        System.out.println(p);
        String input = keyIn.nextLine();
        String [] after = input.split(" ");
        int shorter = 0;
        if(after.length < before.length)
        {
            shorter = after.length;
        }
        else{
            shorter = before.length;
        }
        for(int i = 0; i < shorter; i++)
        {
            if(before[i].compareTo(after[i]) == 0)
            {
                acc++;
            }
        }
        int endTime = (int) System.currentTimeMillis();
        double minutes = (endTime - startTime) * 0.0000166667;
        wpm = after.length/minutes;
        System.out.println("\nYour accuracy is " + (100*acc)/before.length + "%");
        System.out.println("Your WPM is " + (int)wpm + "\n");

        rerun();
    }

    private static void words() {
        int numberOfWords = 5;
        int accuracy = 0;
        double wpm = 0;
        Scanner keyIn = new Scanner(System.in);

        int startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < numberOfWords; i++) {
            String randomWord;
            
            int randomArr = (int) (Math.random() * 2);
            if (randomArr == 0) {
                int random = (int) (Math.random() * words.size());
                randomWord = words.get(random);
            } else {
                int random = (int) (Math.random() * ewords.size());
                randomWord = ewords.get(random);
            }

            System.out.println(randomWord);
            String input = keyIn.nextLine();
            if (randomWord.compareTo(input) == 0) {
                accuracy++;
            }
        }
        int endTime = (int) System.currentTimeMillis();
        double minutes = (endTime - startTime) * 0.0000166667;
        wpm = numberOfWords / minutes;

        accuracy *= 100;
        accuracy /= numberOfWords;
        System.out.print("Your speed was: ");
        System.out.printf("%.0f", wpm);
        System.out.println(" WPM");
        System.out.println("Your accuracy was: " + accuracy + "%");

        rerun();
    }

    private static void rerun() {
        Scanner keyIn = new Scanner(System.in);

        System.out.println("Do you want to continue? Y or N");
        String input = keyIn.nextLine().toLowerCase();
        if (input.compareTo("y") == 0 || input.compareTo("yes") == 0) {
            System.out.print("\f");
            menu();
        } else {
            System.exit(0);
        }
    }
}
