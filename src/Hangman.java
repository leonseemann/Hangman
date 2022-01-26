public class Hangman {
    private final String[] Hangman = {
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
    }; // Hangman ASCII

    private final String[] woerter = {"Haus", "Baum"};

    public String printHangman(int i){
        return Hangman[i];
    }



}
