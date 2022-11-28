package hangman;
import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Welcome to Hangman game!");

        System.out.println("Hangman is a word-guessing game. It keeps asking the user to guess characters until:");

        System.out.println("1. They guess every character correctly (win).");

        System.out.println("2. They miss 6 guesses (loss).");

        System.out.println("So, when you're ready, type the first letter!");

        String wordToGuess = randomWord(words);

        char [] word = wordToGuess.toCharArray();

        String [] wordSoFar = new String[word.length];

        for (int i = 0; i < word.length; i++) {
            wordSoFar[i]="_";
        }


        int numberOfMaximumMistakePossible=6;

        int countMisses = 0;

        char[] wrongLetters = new char[numberOfMaximumMistakePossible];

        boolean guess;

        boolean completeWordGuess;

        while (true) {

            String currentState;
            char letter = askUser();
            guess = isThereAGuessLetter(word, letter);

            wordSoFar = wordSoFar(word, wordSoFar, letter);

            if (!guess) {
                countMisses++;
                wrongLetters[countMisses-1]=letter;
            }

            currentState = appropriateGallows(gallows, countMisses);

            System.out.println(currentState);

            System.out.println(Arrays.toString(wordSoFar));

            System.out.println();

            System.out.print("Misses: ");

            System.out.println(Arrays.toString(wrongLetters));

            completeWordGuess = finalGuessCheck(wordSoFar,word);

            if (countMisses==6) {
                System.out.println();
                System.out.println("Sorry, you failed!");
                System.out.println("The word was: " + wordToGuess);
                break;
            }

            else if (completeWordGuess) {
                System.out.println();
                System.out.println("Congrats! You've made it!");
                break;
            }

        }

        scan.close();
    }

    public static boolean finalGuessCheck (String [] wordSoFar, char [] word) {
        boolean thatIsTheWord = false;
        int count = 0;
        for (int i = 0; i < word.length; i++) {
            if (wordSoFar[i].equals(String.valueOf(word[i]))) {
                count++;
            }
        }
        if (count== word.length) {
            thatIsTheWord=true;
        }
        return thatIsTheWord;
    }

    public static boolean isThereAGuessLetter (char [] word, char letter) {

        boolean guess=false;
        int count=0;

        for (int i = 0; i < word.length; i++) {

            System.out.println();

            if (word[i]==letter) {
                count++;
            }
        }
        if (count>0) {
            guess=true;
        }

        return guess;

    }
    public static String appropriateGallows (String [] gallows, int count) {
        switch (count) {
            case 0 : return gallows[0];
            case 1 : return gallows[1];
            case 2 : return gallows[2];
            case 3 : return gallows[3];
            case 4 : return gallows[4];
            case 5 : return gallows[5];
            case 6 : return gallows[6];
            default: return "error";
        }
    }

    public static char askUser () {
        System.out.print("Guess: ");
        String letters = scan.nextLine().toLowerCase();
        boolean isLetter = isAlpha(letters);

        while (!isLetter) {
            System.out.println("Please input a small letter(a-z), try again: ");
            letters = scan.nextLine().toLowerCase();
            isLetter = isAlpha(letters);
            if (isLetter) {
                break;
            }

        }

        char letter = letters.charAt(0);

        return letter;

    }

    public static String[] wordSoFar (char[] word, String[] wordSoFar, char letter) {

        for (int i = 0; i < word.length; i++) {
            if (letter==word[i]) {
                wordSoFar[i]= String.valueOf(word[i]);
            }

            else if (wordSoFar[i].equals(String.valueOf(word[i]))) {
                wordSoFar[i]= String.valueOf(word[i]);
            }
            else if (letter!=word[i]){
                wordSoFar[i]="_";
            }

            else {
                wordSoFar[i]="_";

            }

        }
        return wordSoFar;

    }

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra"};

    public static String randomWord (String[] words) {
        int x = (int) (Math.random()*words.length);

        return words[x];

    }

    public static boolean isAlpha(String word)
    {
        String s=word.toLowerCase();
        for(int i=0; i<s.length();i++)
        {
            if((s.charAt(i)>='a' && s.charAt(i)<='z'))
            {
                continue;
            }
            else
            {
                return false;
            }

        }
        return true;
    }

    public static String[] gallows = {"+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\ |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n"};

}
