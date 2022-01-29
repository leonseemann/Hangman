import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Hangman {

    private final String[] hangman = {
            """
          +---+
          |   |
              |
              |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
              |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
          |   |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|   |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
              |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
         /    |
              |
        =========""",
            """
          +---+
          |   |
          O   |
         /|\\  |
         / \\  |
              |
        ========="""
    }; // Hangman ASCII // 7
    private int hangmanAuswahl = 0;
    private ArrayList<Character> getippteBuchstabenListe = new ArrayList<>();
    private ArrayList<Character> buchstabenListe = new ArrayList<>();
    private ArrayList<String> woerter = new ArrayList<>() {{
        Scanner s = new Scanner(new File("src\\words.txt"));
        while (s.hasNext()) {
            add(s.next());
        }
        s.close();
    }};
    private ArrayList<Character> wortChar = new ArrayList<>();

    private void start(int wort) {

        char[] temp = woerter.get(wort).toCharArray();
        for (Character i : temp) {
            wortChar.add(Character.toUpperCase(i));
        }

        for (Character i : wortChar) {
            buchstabenListe.add('_');
        }

        while (!gewonnen() && !verloren()) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            printWort();

            char eingabe = eingabe();
            if (getippteBuchstabenListe.contains(eingabe)) {
                addHangman();
            } else {
                if (wortChar.contains(eingabe)) {
                    replaceGetippt(eingabe);
                } else {
                    addHangman();
                }
            }
            getippteBuchstabenListe.add(eingabe);
        }

        if (gewonnen()) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            printWort();
            System.out.println("GEWONNEN!");
        }
        if (verloren()) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            System.out.printf("Du hast leider verloren!%nDas wort war \"%s\"", woerter.get(wort));
        }
    }

    private void printWort() {
        for (Character i : buchstabenListe) {
            System.out.printf("%s ", i);
        }

        System.out.printf("     %s Buchstaben!%n", buchstabenListe.size());
    }

    private void printHangman() {
        System.out.println(hangman[hangmanAuswahl]);
    }

    private char eingabe() {
        Scanner scan = new Scanner(System.in);
        return Character.toUpperCase(scan.next().charAt(0));
    }

    private void addHangman() {
        if (hangmanAuswahl < hangman.length) {
            hangmanAuswahl++;
        }
    }

    private void replaceGetippt(char x) {
        x = Character.toUpperCase(x);
        for (int i = 0; i < wortChar.size(); i++) {
            if (wortChar.get(i).equals(x)) {
                buchstabenListe.set(i, x);
            }
        }
    }

    private boolean gewonnen() {
        return buchstabenListe.equals(wortChar);
    }

    private boolean verloren() {
        return hangmanAuswahl == 6;
    }

    public int printSize() {
        return woerter.size();
    }

    public Hangman() throws FileNotFoundException {
        start(ThreadLocalRandom.current().nextInt(0, woerter.size() - 1));
    }

    public Hangman(int i) throws FileNotFoundException {
        start(i);
    }
}
