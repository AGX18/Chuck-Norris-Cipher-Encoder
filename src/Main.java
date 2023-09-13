import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        while(true)
        {
            System.out.println("Please input operation (encode/decode/exit):");
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine();
            switch (operation) {
                case "encode" -> encodeMessage(scanner);
                case "decode" -> {
                    String decoded = decodeMessage(scanner);
                    if (decoded.isEmpty()) {
                        System.out.println("Encoded string is not valid.");
                        break;
                    }
                    System.out.println("Decoded string:");
                    System.out.println(decoded);
                }
                case "exit" -> {
                    exit();
                    return;
                }
                default -> System.out.printf("There is no '%s' operation.\n", operation);
            }
            System.out.println();
        }
    }
    public static void exit(){
        System.out.println("Bye!");
    }


    private static void encodeMessage(Scanner scanner) {
        System.out.println("Input string:");
        String a = scanner.nextLine();
        System.out.println("Encoded string:");
        if(a.isEmpty())
        {
            return;
        }
        String[] letters = a.split("");

        StringBuilder combinedBinaryRepresentation = new StringBuilder();
        //Output all characters in the string, each separated by a single space.
        for (String letter : letters) {


            combinedBinaryRepresentation.append(ChartoBinary(letter));

        }

        System.out.println(encoding(combinedBinaryRepresentation.toString()));
    }

    public static String decodeMessage(Scanner scanner){

        System.out.println("Input encoded string:");
        String a = scanner.nextLine();

        boolean x = validateInput(a); // validating input
        if(!x)
        {
            return "";
        }

        if(a.isEmpty())
        {
            return "";
        }

        String BinaryRepresentationAsWhole = decodingtoBinary(a);
        if(BinaryRepresentationAsWhole.length() % 7 != 0) // if the length of the binary representation is not divisible by 7 (meaning it's not a valid character), then it is not valid
        {
            return "";
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < (BinaryRepresentationAsWhole.length() / 7); i++) {
            String BinaryRepresentaion = BinaryRepresentationAsWhole.substring(i * 7, (i + 1) * 7);
            int number = Integer.parseInt(BinaryRepresentaion, 2);
            char ch = (char) number;
            message.append(ch);
        }

        return message.toString();

    }

    private static boolean validateInput(String a) {
        for (int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == '1') // input can only contain 0's and spaces
            {
                continue;
            }
            {
                return false;
            }


        }
        String[] checking = a.split(" ");
        if(checking.length % 2 != 0) // input must contain an even number of 0's since it is encoded in pairs
        {
            return false;
        }


        for (int i = 0; i < checking.length; i+=2) {
            if(!checking[i].equals("0") && !checking[i].equals("00")) { // input must contain 0's and 00's in the even positions describing if the next 0's are 1's or 0's
                return false;
            }
        }
        return true;
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