/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FinalsProject;
import java.util.Scanner;
public class EndecryptSystem {
   static Scanner input = new Scanner(System.in);
   static final char[][] POLYBIUS = {
       {'A', 'B', 'C', 'D', 'E'},
       {'F', 'G', 'H', 'I', 'K'},
       {'L', 'M', 'N', 'O', 'P'},
       {'Q', 'R', 'S', 'T', 'U'},
       {'V', 'W', 'X', 'Y', 'Z'}
   };
   public static void main(String[] args) {
       boolean running = true;
       while (running) {
           displayMainMenu();
           int choice = getIntegerInput("Enter your choice: ");
           switch (choice) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
                   repeatOperation(choice);
                   break;
               case 7:
                   System.out.println("\nThank you for using ENDECRYPTER DEV!");
                   System.out.println("Program terminated.");
                   running = false;
                   break;
               default:
                   System.out.println("\nERROR: Invalid menu selection!");
                   System.out.println("Please select between 1 and 7.");
           }
       }
       System.out.println("Group Endecrypt\nLEADER:\tGregorio, Paul Efhraim N.\nMEMBERS:\n\tAbuedo, Lorenze Jake F.\n\tVelasco, Ezra Shadrock B.\n\tNatividad, Angel John G.\n\tCayosa, Ralph Ed Emerson P.");
       input.close();
   }
   public static void displayMainMenu() {
       System.out.println("\n==========================================");
       System.out.println("             ENDECRYPTER DEV");
       System.out.println("==========================================");
       System.out.println(" A STANDARD ENCRYPTION AND DECRYPTION SYSTEM");
       System.out.println("\n--------------- MAIN MENU ---------------");
       System.out.println("[1] ENCRYPT MESSAGE");
       System.out.println("[2] DECRYPT MESSAGE");
       System.out.println("[3] CAESAR BRUTE FORCE");
       System.out.println("[4] FREQUENCY ANALYSIS");
       System.out.println("[5] ENCRYPTION METHODS GUIDE");
       System.out.println("[6] TEXT STATISTICS");
       System.out.println("[7] EXIT PROGRAM");
       System.out.println("==========================================");
   }
   public static void repeatOperation(int choice) {
       boolean repeat = true;
       while (repeat) {
           switch (choice) {
               case 1:
                   encryptMessage();
                   break;
               case 2:
                   decryptMessage();
                   break;
               case 3:
                   caesarBruteForce();
                   break;
               case 4:
                   frequencyAnalysis();
                   break;
               case 5:
                   displayEncryptionGuide();
                   break;
               case 6:
                   displayTextStatistics();
                   break;
           }
           repeat = askYesNo("\nWould you like to use this feature again? (Y/N): ");
       }
   }
   public static int getIntegerInput(String prompt) {
       while (true) {
           System.out.print(prompt);
           String value = input.nextLine().trim();
           try {
               return Integer.parseInt(value);
           } catch (NumberFormatException e) {
               System.out.println("ERROR: Please enter a valid number.");
           }
       }
   }
   public static String getNonEmptyInput(String prompt) {
       while (true) {
           System.out.print(prompt);
           String value = input.nextLine();
           if (!value.trim().isEmpty()) {
               return value;
           }
           System.out.println("ERROR: Input cannot be empty!");
       }
   }
   public static boolean askYesNo(String prompt) {
       while (true) {
           System.out.print(prompt);
           String answer = input.nextLine().trim();
           if (answer.equalsIgnoreCase("Y")) {
               return true;
           }
           if (answer.equalsIgnoreCase("N")) {
               return false;
           }
           System.out.println("ERROR: Please enter Y or N.");
       }
   }
   public static int getShiftNumber() {
       while (true) {
           int shift = getIntegerInput("Enter shift number (1-25): ");
           if (shift >= 1 && shift <= 25) {
               return shift;
           }
           System.out.println("ERROR: Shift must be between 1 and 25.");
       }
   }
   public static int getMethodChoice() {
       while (true) {
           System.out.println("\nSELECT ENCRYPTION METHOD");
           System.out.println("[1] Caesar Cipher");
           System.out.println("[2] Atbash Cipher");
           System.out.println("[3] Keyword Cipher");
           System.out.println("[4] Polybius Square");
           int choice = getIntegerInput("Select method: ");
           if (choice >= 1 && choice <= 4) {
               return choice;
           }
           System.out.println("ERROR: Invalid method selection!");
       }
   }
   public static void encryptMessage() {
       System.out.println("\n==========================================");
       System.out.println("              ENCRYPT MESSAGE");
       System.out.println("==========================================");
       String plainMessage = getNonEmptyInput("Enter your plain message: ");
       int method = getMethodChoice();
       String encryptedMessage = "";
       String keyInformation = "";
       String methodName = "";
       int shift = 0;
       String keyword = "";
       switch (method) {
           case 1:
               methodName = "Caesar Cipher";
               shift = getShiftNumber();
               encryptedMessage = caesarCipher(plainMessage, shift, true);
               keyInformation = "Shift: " + shift;
               break;
           case 2:
               methodName = "Atbash Cipher";
               encryptedMessage = atbashCipher(plainMessage);
               keyInformation = "No key required";
               break;
           case 3:
               methodName = "Keyword Cipher";
               keyword = getValidKeyword();
               encryptedMessage = keywordCipher(plainMessage, keyword, true);
               keyInformation = "Keyword: " + keyword;
               break;
           case 4:
               methodName = "Polybius Square";
               encryptedMessage = polybiusEncrypt(plainMessage);
               keyInformation = "Standard 5x5 Square";
               break;
       }
       System.out.println("\n==========================================");
       System.out.println("             ENCRYPTION RESULT");
       System.out.println("==========================================");
       System.out.println("Original Message: " + plainMessage);
       System.out.println("Encryption Method: " + methodName);
       System.out.println("Key Information: " + keyInformation);
       System.out.println("------------------------------------------");
       System.out.println("Encrypted Message: " + encryptedMessage);
       System.out.println("==========================================");
       displayEncryptionSteps(method, plainMessage, encryptedMessage, shift, keyword);
   }
   public static void decryptMessage() {
       System.out.println("\n==========================================");
       System.out.println("              DECRYPT MESSAGE");
       System.out.println("==========================================");
       int method = getMethodChoice();
       int shift = 0;
       String keyword = "";
       String methodName = "";
       String keyInformation = "";
       switch (method) {
           case 1:
               methodName = "Caesar Cipher";
               shift = getShiftNumber();
               keyInformation = "Shift: " + shift;
               break;
           case 2:
               methodName = "Atbash Cipher";
               keyInformation = "No key required";
               break;
           case 3:
               methodName = "Keyword Cipher";
               keyword = getValidKeyword();
               keyInformation = "Keyword: " + keyword;
               break;
           case 4:
               methodName = "Polybius Square";
               keyInformation = "Standard 5x5 Square";
               System.out.println("Enter coordinates in the generated format, such as (11)(12){0020}(13).");
               break;
       }
       String encryptedMessage = getNonEmptyInput("\nEnter your encrypted message: ");
       try {
           String decryptedMessage = "";
           switch (method) {
               case 1:
                   decryptedMessage = caesarCipher(encryptedMessage, shift, false);
                   break;
               case 2:
                   decryptedMessage = atbashCipher(encryptedMessage);
                   break;
               case 3:
                   decryptedMessage = keywordCipher(encryptedMessage, keyword, false);
                   break;
               case 4:
                   decryptedMessage = polybiusDecrypt(encryptedMessage);
                   break;
           }
           System.out.println("\n==========================================");
           System.out.println("             DECRYPTION RESULT");
           System.out.println("==========================================");
           System.out.println("Encrypted Message: " + encryptedMessage);
           System.out.println("Decryption Method: " + methodName);
           System.out.println("Key Information: " + keyInformation);
           System.out.println("------------------------------------------");
           System.out.println("Decrypted Message: " + decryptedMessage);
           System.out.println("==========================================");
           displayDecryptionSteps(method, encryptedMessage, decryptedMessage, shift, keyword);
       } catch (IllegalArgumentException e) {
           System.out.println("\nDECRYPTION ERROR: " + e.getMessage());
       }
   }
   public static void displayEncryptionSteps(int method, String original, String encrypted, int shift, String keyword) {
       System.out.println("\n--------------- HOW ENCRYPTION WORKS ---------------");
       switch (method) {
           case 1:
               System.out.println("1. Each alphabetic character is moved forward by " + shift + " position(s).");
               System.out.println("2. The alphabet wraps around after Z and preserves letter case.");
               System.out.println("3. Spaces, numbers, and punctuation remain unchanged.");
               System.out.println("Example: A becomes " + caesarCipher("A", shift, true) + " with this shift.");
               break;
           case 2:
               System.out.println("1. Each letter is replaced by its opposite in the alphabet.");
               System.out.println("2. A becomes Z, B becomes Y, and C becomes X.");
               System.out.println("3. Spaces, numbers, punctuation, and letter case are preserved.");
               break;
           case 3:
               System.out.println("1. The keyword is used to create a substitution alphabet.");
               System.out.println("2. Repeated letters in the keyword are included only once.");
               System.out.println("3. The remaining unused letters are added alphabetically.");
               System.out.println("Generated alphabet: " + generateKeywordAlphabet(keyword));
               break;
           case 4:
               System.out.println("1. The message is converted to uppercase for letter lookup.");
               System.out.println("2. Each letter is replaced with its row and column coordinates.");
               System.out.println("3. I and J share one cell in the standard 5x5 square.");
               System.out.println("4. Non-letter characters are preserved using hexadecimal notation.");
               break;
       }
       System.out.println("Input length: " + original.length() + " character(s).");
       System.out.println("Output length: " + encrypted.length() + " character(s).");
       System.out.println("-----------------------------------------------------");
   }
   public static void displayDecryptionSteps(int method, String encrypted, String decrypted, int shift, String keyword) {
       System.out.println("\n--------------- HOW DECRYPTION WORKS ---------------");
       switch (method) {
           case 1:
               System.out.println("1. Each alphabetic character is moved backward by " + shift + " position(s).");
               System.out.println("2. The alphabet wraps around before A when necessary.");
               break;
           case 2:
               System.out.println("1. Each letter is replaced by its opposite in the alphabet.");
               System.out.println("2. Applying Atbash again reverses the transformation.");
               break;
           case 3:
               System.out.println("1. The same keyword is used to rebuild the substitution alphabet.");
               System.out.println("2. Each encrypted letter is located in that alphabet.");
               System.out.println("3. Its position identifies the original alphabet letter.");
               System.out.println("Generated alphabet: " + generateKeywordAlphabet(keyword));
               break;
           case 4:
               System.out.println("1. Each (row,column) pair is located in the 5x5 square.");
               System.out.println("2. The letter at that position is added to the result.");
               System.out.println("3. Hexadecimal values inside braces are converted back to characters.");
               break;
       }
       System.out.println("Encrypted length: " + encrypted.length() + " character(s).");
       System.out.println("Decrypted length: " + decrypted.length() + " character(s).");
       System.out.println("-----------------------------------------------------");
   }
   public static void caesarBruteForce() {
       System.out.println("\n==========================================");
       System.out.println("             CAESAR BRUTE FORCE");
       System.out.println("==========================================");
       String encryptedMessage = getNonEmptyInput("Enter the encrypted message: ");
       System.out.println("\nPossible decryptions using all 25 shifts:");
       for (int shift = 1; shift <= 25; shift++) {
           String possibleMessage = caesarCipher(encryptedMessage, shift, false);
           System.out.println("Shift " + shift + ": " + possibleMessage);
       }
       System.out.println("\nReview the results and identify the message that makes sense.");
       System.out.println("Note: Brute force displays possibilities; it does not automatically identify the correct one.");
   }
   public static void frequencyAnalysis() {
       System.out.println("\n==========================================");
       System.out.println("             FREQUENCY ANALYSIS");
       System.out.println("==========================================");
       String message = getNonEmptyInput("Enter a message to analyze: ");
       int[] frequencies = new int[26];
       int totalLetters = 0;
       for (int i = 0; i < message.length(); i++) {
           char character = Character.toUpperCase(message.charAt(i));
           if (character >= 'A' && character <= 'Z') {
               frequencies[character - 'A']++;
               totalLetters++;
           }
       }
       System.out.println("\nLETTER FREQUENCIES");
       System.out.println("Letter | Count | Percentage");
       System.out.println("-----------------------------");
       for (int i = 0; i < frequencies.length; i++) {
           double percentage = totalLetters == 0 ? 0.0
               : (frequencies[i] * 100.0) / totalLetters;
           System.out.printf("   %c   |   %3d | %7.2f%%%n", (char) ('A' + i), frequencies[i], percentage);
       }
       System.out.println("-----------------------------");
       System.out.println("Total alphabetic characters: " + totalLetters);
       System.out.println("Frequency analysis counts English letters A-Z only.");
   }
   public static void displayEncryptionGuide() {
       System.out.println("\n==========================================");
       System.out.println("          ENCRYPTION METHODS GUIDE");
       System.out.println("==========================================");
       System.out.println("1. Caesar Cipher: Shifts each letter by a chosen number.");
       System.out.println("2. Atbash Cipher: Replaces each letter with its alphabet opposite.");
       System.out.println("3. Keyword Cipher: Uses a keyword to create a substitution alphabet.");
       System.out.println("4. Polybius Square: Converts letters into row-column coordinates.");
       System.out.println("\nAdditional tools:");
       System.out.println("- Caesar Brute Force: Shows all 25 possible Caesar decryptions.");
       System.out.println("- Frequency Analysis: Counts how often each letter appears.");
       System.out.println("\nThese classical ciphers are educational and are not suitable for protecting sensitive data.");
   }
   public static void displayTextStatistics() {
       System.out.println("\n==========================================");
       System.out.println("              TEXT STATISTICS");
       System.out.println("==========================================");
       String message = getNonEmptyInput("Enter a message: ");
       int letters = 0;
       int digits = 0;
       int spaces = 0;
       int punctuation = 0;
       int words = message.trim().isEmpty() ? 0 : message.trim().split("\\s+").length;
       for (int i = 0; i < message.length(); i++) {
           char character = message.charAt(i);
           if (Character.isLetter(character)) {
               letters++;
           } else if (Character.isDigit(character)) {
               digits++;
           } else if (Character.isWhitespace(character)) {
               spaces++;
           } else {
               punctuation++;
           }
       }
       System.out.println("\nTotal characters: " + message.length());
       System.out.println("Words: " + words);
       System.out.println("Letters: " + letters);
       System.out.println("Digits: " + digits);
       System.out.println("Spaces: " + spaces);
       System.out.println("Punctuation/symbols: " + punctuation);
   }
   public static String caesarCipher(String message, int shift, boolean encrypt) {
       StringBuilder result = new StringBuilder();
       if (!encrypt) {
           shift = 26 - shift;
       }
       for (int i = 0; i < message.length(); i++) {
           char character = message.charAt(i);
           if (character >= 'A' && character <= 'Z') {
               character = (char) ((character - 'A' + shift) % 26 + 'A');
           } else if (character >= 'a' && character <= 'z') {
               character = (char) ((character - 'a' + shift) % 26 + 'a');
           }
           result.append(character);
       }
       return result.toString();
   }
   public static String atbashCipher(String message) {
       StringBuilder result = new StringBuilder();
       for (int i = 0; i < message.length(); i++) {
           char character = message.charAt(i);
           if (character >= 'A' && character <= 'Z') {
               character = (char) ('Z' - (character - 'A'));
           } else if (character >= 'a' && character <= 'z') {
               character = (char) ('z' - (character - 'a'));
           }
           result.append(character);
       }
       return result.toString();
   }
   public static String getValidKeyword() {
       while (true) {
           String keyword = getNonEmptyInput("Enter your keyword: ");
           if (keyword.matches("(?s).*\\p{L}.*")) {
               return keyword;
           }
           System.out.println("ERROR: Keyword must contain at least one letter.");
       }
   }
   public static String generateKeywordAlphabet(String keyword) {
       StringBuilder alphabet = new StringBuilder();
       boolean[] used = new boolean[26];
       for (int i = 0; i < keyword.length(); i++) {
           char character = Character.toUpperCase(keyword.charAt(i));
           if (character >= 'A' && character <= 'Z') {
               int index = character - 'A';
               if (!used[index]) {
                   alphabet.append(character);
                   used[index] = true;
               }
           }
       }
       for (char character = 'A'; character <= 'Z'; character++) {
           int index = character - 'A';
           if (!used[index]) {
               alphabet.append(character);
               used[index] = true;
           }
       }
       return alphabet.toString();
   }
   public static String keywordCipher(String message, String keyword, boolean encrypt) {
       StringBuilder result = new StringBuilder();
       String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
       String keywordAlphabet = generateKeywordAlphabet(keyword);
       for (int i = 0; i < message.length(); i++) {
           char character = message.charAt(i);
           boolean lowercase = Character.isLowerCase(character);
           char upperCharacter = Character.toUpperCase(character);
           if (upperCharacter >= 'A' && upperCharacter <= 'Z') {
               int index;
               if (encrypt) {
                   index = alphabet.indexOf(upperCharacter);
                   character = keywordAlphabet.charAt(index);
               } else {
                   index = keywordAlphabet.indexOf(upperCharacter);
                   character = alphabet.charAt(index);
               }
               if (lowercase) {
                   character = Character.toLowerCase(character);
               }
           }
           result.append(character);
       }
       return result.toString();
   }
   public static String polybiusEncrypt(String message) {
       StringBuilder result = new StringBuilder();
       for (int i = 0; i < message.length();) {
           int codePoint = message.codePointAt(i);
           i += Character.charCount(codePoint);
           char character = Character.toUpperCase(Character.toString(codePoint).charAt(0));
           if (codePoint <= Character.MAX_VALUE && character >= 'A' && character <= 'Z') {
               if (character == 'J') {
                   character = 'I';
               }
               boolean found = false;
               for (int row = 0; row < 5 && !found; row++) {
                   for (int col = 0; col < 5; col++) {
                       if (POLYBIUS[row][col] == character) {
                           result.append('(').append(row + 1).append(col + 1).append(')');
                           found = true;
                           break;
                       }
                   }
               }
           } else {
               result.append('{').append(String.format("%04X", codePoint)).append('}');
           }
       }
       return result.toString();
   }
   public static String polybiusDecrypt(String encryptedMessage) {
       StringBuilder result = new StringBuilder();
       int i = 0;
       while (i < encryptedMessage.length()) {
           char character = encryptedMessage.charAt(i);
           if (character == '(') {
               if (i + 3 >= encryptedMessage.length() || encryptedMessage.charAt(i + 3) != ')') {
                   throw new IllegalArgumentException("Invalid coordinate format.");
               }
               char rowChar = encryptedMessage.charAt(i + 1);
               char colChar = encryptedMessage.charAt(i + 2);
               if (!Character.isDigit(rowChar) || !Character.isDigit(colChar)) {
                   throw new IllegalArgumentException("Coordinates must contain two digits.");
               }
               int row = Character.getNumericValue(rowChar);
               int col = Character.getNumericValue(colChar);
               if (row < 1 || row > 5 || col < 1 || col > 5) {
                   throw new IllegalArgumentException("Coordinates must be between 11 and 55 and use valid row-column combinations.");
               }
               result.append(POLYBIUS[row - 1][col - 1]);
               i += 4;
           } else if (character == '{') {
               int closingBrace = encryptedMessage.indexOf('}', i);
               if (closingBrace == -1) {
                   throw new IllegalArgumentException("Invalid Unicode character format.");
               }
               String hex = encryptedMessage.substring(i + 1, closingBrace);
               try {
                   int codePoint = Integer.parseInt(hex, 16);
                   if (!Character.isValidCodePoint(codePoint)
                           || (codePoint >= 0xD800 && codePoint <= 0xDFFF)) {
                       throw new IllegalArgumentException("Invalid Unicode character.");
                   }
                   result.appendCodePoint(codePoint);
               } catch (NumberFormatException e) {
                   throw new IllegalArgumentException("Invalid hexadecimal character.");
               }
               i = closingBrace + 1;
           } else {
               throw new IllegalArgumentException("Unexpected character in Polybius coordinates.");
           }
       }
       return result.toString();
   }
}
