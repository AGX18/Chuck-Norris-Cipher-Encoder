import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnalyseWords();
    }

    public static void AnalyseWords() {
        //Prompt the user with the message: Input string:
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String a = scanner.nextLine();
        System.out.println("The result:");
        if(a.isEmpty())
        {
            return;
        }
        String[] letters = a.split("");


        //Output all characters in the string, each separated by a single space.
        for (String letter : letters) {

            System.out.printf("%s = %s\n", letter,ChartoBinary(letter) );
        }



    }

    public static String ChartoBinary(String letter) {
        if(letter.isEmpty())
        {
            return "";
        }
        char ch = letter.charAt(0);
        StringBuilder binaryRepresentation = new StringBuilder();
        for (int i = 6; i >= 0; i--) {
            // Shift the character's bits to the right and check the least significant bit
            int bit = (ch >> i) & 1;
            binaryRepresentation.append(bit);
        }

        return binaryRepresentation.toString();
    }

    public static String ChartoBinary2(String letter) {
        if(letter.isEmpty())
        {
            return "";
        }
        String binaryRepresentation = Integer.toBinaryString(letter.codePointAt(0));
        if(binaryRepresentation.length() < 7)
        {
            binaryRepresentation = "0" + binaryRepresentation;
        }
        return binaryRepresentation;
    }
}