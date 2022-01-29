import java.util.ArrayList;
import java.util.Scanner;

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
    private final String[] woerter = {"Haus", "Baum", "Hurensohn"};
    private int wort;
    ArrayList<Character> wortChar = new ArrayList<>();

    private void start(int wort) {
        this.wort = wort;

        char[] temp = woerter[wort].toCharArray();
        for (Character i : temp) {
            wortChar.add(Character.toUpperCase(i));
        }

        for (Character i : wortChar) {
            buchstabenListe.add('_');
        }

        while (gewonnen() != true && verloren() != true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            printWort();

            char eingabe = eingabe();
            if (getippteBuchstabenListe.contains(eingabe) == true) {
                addHangman();
            } else {
                if (wortChar.contains(eingabe) == false) {
                    addHangman();
                } else {
                    replaceGetippt(eingabe);
                }
            }
            getippteBuchstabenListe.add(eingabe);
        }

        if (gewonnen() == true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            printWort();
            System.out.println("GEWONNEN!");
        }
        if (verloren() == true) {
            System.out.println("\033[H\033[2J");
            System.out.flush();
            printHangman();
            System.out.printf("Du hast leider verloren!%nDas wort war \"%s\"", woerter[wort]);
        }
    }

    private void printWort() {
        for (Character i : buchstabenListe) {
            System.out.printf("%s ", i);
        }

        System.out.printf("     %s Woerter!%n", buchstabenListe.size());
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
        if (buchstabenListe.equals(wortChar)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verloren() {
        if (hangmanAuswahl == 6) {
            return true;
        } else {
            return false;
        }
    }

    public Hangman(int i) {
        start(i);
    }
}
