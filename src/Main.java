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

        StringBuilder combinedBinaryRepresentation = new StringBuilder();
        //Output all characters in the string, each separated by a single space.
        for (String letter : letters) {

            //System.out.printf("%s = %s\n", letter,ChartoBinary(letter) );
            combinedBinaryRepresentation.append(ChartoBinary(letter));

        }

        System.out.println(encoding(combinedBinaryRepresentation.toString()));




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



    public static String encoding(String combined){
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < combined.length(); i++) {
            int j = i;

            while(j < combined.length() - 1 && combined.charAt(j) == combined.charAt(j+1) )
            {
                j++;
            }
            if(combined.charAt(i) == '1')
            {
                encoded.append("0 ");
            }
            else{
                encoded.append("00 ");
            }

            encoded.append("0".repeat(Math.max(0, j - i + 1)));
            encoded.append(" ");
            i = j;
        }

        return encoded.toString();
    }
}