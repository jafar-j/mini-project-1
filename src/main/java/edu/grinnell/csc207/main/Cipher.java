package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.CipherUtils;

/**
 * Uses methods in cipherutils.java to encrypt/decrypt words.
 * Encodes/decodes the words using the vigenere and caesar cipher calculations.
 * @author Jafar Jarrar.
 */
public class Cipher {

  /**The expected number of strings to be entered in the command line. */
  private static final int EXPECTED_NUM_OF_ARGS = 4;

  /**
   * Runs the program.
   * @param args
   * The command line arguments. They should be four arguments containing a
   * command (encode/decode), method of encryption (caesar/vigenere), word to be
   * encrypted, and a key for encryption.
   */
  public static void main(String[] args) {
    if (args.length != EXPECTED_NUM_OF_ARGS) {
      System.err.println("Error: Expected 4 parameters, received " + args.length);
      return;
    } // if

    String vigenereResult = checkVigenere(args);
    if (vigenereResult.equals("noLowerCase")) {
      System.err.println("Error: strings must be only lowercase letters");
      return;
    } else if (vigenereResult.equals("success")) {
      return;
    } else if (vigenereResult.equals("failed")) {
      String caesarResult = checkCaesar(args);
      if (caesarResult.equals("noLowerCase")) {
        System.err.println("Error: strings must be only lowercase letters");
        return;
      } else if (caesarResult.equals("noKey")) {
        System.err.println("Error: Caesar ciphers require a one-character key");
        return;
      } else if (caesarResult.equals("success")) {
        return;
      } else if (caesarResult.equals("failed")) {
        System.err.println("Error: No valid action specified. "
            + "Legal values are '-caesar' and '-vigenere'");
      } // if
    } // if
  } // main(String[])

  /**
   * Checks if two out of three strings in the array of strings are lowercase,
   * since the third string will be a command that has a '-' preceeding it.
   * @param input
   * Array that should contain three strings.
   * @return true if two of three strings are lowercase, false otherwise.
   */
  public static boolean check2LowerCase(String[] input) {
    int counter = 0;
    for (int i = 0; i < input.length; i++) {
      if (!AllCaesar.checkLowerCase(input[i])) {
        counter++;
      } // if
    } // for
    if (counter > 1) {
      return false;
    } // if
    return true;
  } // checkLowerCase(String[])

  /**
   * Helper method for checkVigenere and checkCaesar methods.
   * Checks line of arguments for given word and removes it it finds it.
   * The first block of code converts the array of strings into one long string to
   * find the given word and the index at which it is at in the array.
   * The second block of code removes the word from the array and shifts the rest
   * of the words accordingly.
   * @param input
   * Array that should contain 4 strings.
   * @param wordToFind
   * String that should be somewhere in the input array of strings.
   * @return the same array of strings inputted but with the wordToFind extracted.
   * Returns array of strings with size 5 if the wordToFind is not found.
   */
  public static String[] checkCode(String[] input, String wordToFind) {
    String inputWords = "";
    for (int i = 0; i < input.length; i++) {
      inputWords += input[i];
    } // for
    if (!inputWords.contains(wordToFind)) {
      return new String[EXPECTED_NUM_OF_ARGS + 1];
    } // if
    int index = 0;
    for (int i = 0; i < input.length; i++) {
      if (input[i].equals(wordToFind)) {
        index = i;
        break;
      } // if
    } // for

    String[] newInput = new String[input.length - 1];
    int counter = 0;
    for (int i = 0; i < input.length; i++) {
      if (i != index) {
        newInput[counter] = input[i];
        counter++;
      } // if
    } // for
    return newInput;
  } // checkCode(String[], String)

  /**
   * Completes a series of checks to determine the commands to carry out, then
   * decrypts/encrypts the word accordingly.
   * @param input
   * Array of strings of size 4 that should be the same as the arguments provided
   * to the main method.
   * @return string value containing the result of encoding/decoding the code,
   * whether it is an error, failure, or success, to determine the output in
   * the main method.
   */
  public static String checkVigenere(String[] input) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String[] removeVigenere = checkCode(input, "-vigenere");
    if (removeVigenere.length == (EXPECTED_NUM_OF_ARGS + 1)) {
      return "failed";
    } // if
    if (!check2LowerCase(removeVigenere)) {
      return "noLowerCase";
    } // if
    String[] removeEncode = checkCode(removeVigenere, "-encode");
    if (removeEncode.length == (EXPECTED_NUM_OF_ARGS + 1)) {
      String[] removeDecode = checkCode(removeVigenere, "-decode");
      if (removeDecode.length == (EXPECTED_NUM_OF_ARGS + 1)) {
        System.err.println("Error: No valid action specified. "
            + "Legal values are '-encode' and '-decode'");
        return "failed";
      } else {
        if (removeDecode[1].isEmpty()) {
          return "failed";
        } else {
          pen.println(CipherUtils.vigenereDecrypt(removeDecode[0], removeDecode[1]));
        } // if
      } // if
    } else {
      if (removeEncode[1].isEmpty()) {
        return "failed";
      } else {
        pen.println(CipherUtils.vigenereEncrypt(removeEncode[0], removeEncode[1]));
      } // if
    } // if
    return "success";
  } // checkVigenere(String[])

  /**
   * Completes a series of checks to determine the commands to carry out, then
   * decrypts/encrypts the word accordingly using the caesar calculations.
   * @param input
   * Array of strings of size 4 that should be the same as the arguments provided
   * to the main method.
   * @return string value containing the result of encoding/decoding the code,
   * whether it is an error, failure, or success, to determine the output in
   * the main method.
   */
  public static String checkCaesar(String[] input) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String[] removeCaesar = checkCode(input, "-caesar");
    if (removeCaesar.length == (EXPECTED_NUM_OF_ARGS + 1)) {
      return "failed";
    } // if
    if (!check2LowerCase(removeCaesar)) {
      return "noLowerCase";
    } // if
    String[] removeEncode = checkCode(removeCaesar, "-encode");
    if (removeEncode.length == (EXPECTED_NUM_OF_ARGS + 1)) {
      String[] removeDecode = checkCode(removeCaesar, "-decode");
      if (removeDecode.length == (EXPECTED_NUM_OF_ARGS + 1)) {
        pen.println("Error: No valid action specified. "
            + "Legal values are '-encode' and '-decode'");
        return "failed";
      } else {
        if (removeDecode[1].isEmpty() || removeDecode[1].length() > 1) {
          return "noKey";
        } else {
          pen.println(CipherUtils.caesarDecrypt(removeDecode[0], removeDecode[1].charAt(0)));
        } // if
      } // if
    } else {
      if (removeEncode[1].isEmpty() || removeEncode[1].length() > 1) {
        return "noKey";
      } else {
        pen.println(CipherUtils.caesarEncrypt(removeEncode[0], removeEncode[1].charAt(0)));
      } // if
    } // if
    return "success";
  } // checkCaesar(String[])
} // class Cipher
