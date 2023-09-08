import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnalyseWords();
    }

    public static void AnalyseWords() {
        //Prompt the user with the message: Input string:
        Scanner scanner = new Scanner(System.in);
        //encodeMessage(scanner);
        //decodeMessage(scanner);

        System.out.println(decodeMessage(scanner));
    }

    private static void encodeMessage(Scanner scanner) {
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

    public static String decodeMessage(Scanner scanner){
        System.out.println("Input encoded string:");
        String a = scanner.nextLine();
        System.out.println("The result:");
        if(a.isEmpty())
        {
            return "";
        }

        String BinaryRepresentationAsWhole = decodingtoBinary(a);

        //String[] BinaryRepresentation = new String[BinaryRepresentationAsWhole.length() / 7];
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < (BinaryRepresentationAsWhole.length() / 7); i++) {
            String BinaryRepresentaion = BinaryRepresentationAsWhole.substring(i * 7, (i + 1) * 7);
            int number = Integer.parseInt(BinaryRepresentaion, 2);
            char ch = (char) number;
            message.append(ch);
        }

        return message.toString();

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

    public static String decodingtoBinary(String input){
        StringBuilder decoded = new StringBuilder();
        String[] words = input.split(" ");
        for (int i = 0; i < words.length; i+=2) {
            if(words[i].equals("0"))
            {
                decoded.append("1".repeat(words[i + 1].length()));

            }
            else {

                decoded.append("0".repeat(words[i + 1].length()));
            }
        }

        return decoded.toString();
    }
}