
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
    
    public static void main(String [] args) {
        readDataFromFile();
        menu();
    }
    
    private static void readDataFromFile() {
        // The name of the file to open.
        String fileName = "words.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.trim());
            }

            // Always close files.
            bufferedReader.close();         
        }
        catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
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
        String [] ogWords = new String[5];
        String [] inpWords = new String[5];
        int startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < numberOfWords; i++) {
            int random = (int) (Math.random() * words.size());
            String randomWord = words.get(random);
            ogWords[i] = randomWord;
            System.out.println(randomWord);
            String input = keyIn.nextLine();
            inpWords[i] = input;
            if (ogWords[i].compareTo(inpWords[i]) == 0) {
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
        String input = keyIn.nextLine().toUpperCase();
        if (input.compareTo("Y") == 0) {
            System.out.print("\f");
            menu();
        } else {
            System.exit(0);
        }
    }
}
