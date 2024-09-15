package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Uses methods in cipherutils.java to encode and decode encryptions.
 * Uses the calculations of the caesar cipher.
 * @author Jafar Jarrar.
 */

public class AllCaesar {

  /** The int value of 'a' to test if characters are lower case.*/
  private static final int A_INTEGER_VALUE = 97;

  /** The int value of 'z' to test if characters are lower case.*/
  private static final int Z_INTEGER_VALUE = 122;

  /**
   * Run the program.
   * @param args
   * The command line arguments. The first should be a command (encode/decode).
   * The second argument should be the word to be encrypted.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String checkedInput = checkInput(args);
    if (!checkedInput.equals("failed")) {
      String word = args[1];
      if (checkedInput.equals("encode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.println("n = " + ch + ": " + CipherUtils.caesarEncrypt(word, ch));
        } // for
      } else if (checkedInput.equals("decode")) {
        for (char ch = 'a'; ch <= 'z'; ch++) {
          pen.println("n = " + ch + ": " + CipherUtils.caesarDecrypt(word, ch));
        } // for
      } // if
    } // if
    pen.close();
  } // main(String[])

  /**
   * Checks if inputted string contains all lower case letters.
   * @param input
   * String containing characters to be checked.
   * @return true if all characters in the string are lowercase, false otherwise.
   */
  public static boolean checkLowerCase(String input) {
    for (int i = 0; i < input.length(); i++) {
      if ((int) input.charAt(i) < A_INTEGER_VALUE || (int) input.charAt(i) > Z_INTEGER_VALUE) {
        return false;
      } // if
    } // for
    return true;
  } // checkLowerCase(String)

  /**
   * Completes multiple checks on inputted array of Strings.
   * @param input
   * Array of strings containing command (encode/decode) and word to be encrypted/decrypted.
   * @return the command (encode/decode).
   */
  public static String checkInput(String[] input) {
    if (input.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
      return "failed";
    } else if (!input[0].equals("encode") && !input[0].equals("decode")) {
      System.err.println("Error: Invalid option: \"" + input[0]
          + "\". Valid options are \"encode\" or \"decode\".");
      return "failed";
    } else if (!checkLowerCase(input[1])) {
      System.err.println("Error: String contains characters other than lowercase letters.");
      return "failed";
    } else {
      return input[0];
    } // if
  } // checkInput(String[])
} // class AllCaesar
