public class StartHangman {
    public static void main(String[] args) {
        Hangman man = new Hangman();
        for (int i = 0; i < 7; i++){
            System.out.printf("%s%n",man.printHangman(i));
        }
    }
}
