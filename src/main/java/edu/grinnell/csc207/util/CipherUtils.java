package edu.grinnell.csc207.util;

/**
 * Implementation of basic encryption technique methods.
 * @author Jafar Jarrar.
 */

public class CipherUtils {

  /** The int value of 'a' used when converting between characters and integers. */
  private static final int A_INTEGER_VALUE = 97;

  /** The maximum value that an alphabetical lower case letter can hold. */
  private static final int MAX_LETTER_CODE = 25;

  /**
   * Converts given character to integer value.
   * @param letter
   * The letter in which we want to get the integer code of.
   * @return the integer code of the given letter.
   */
  private static int letter2int(char letter) {
    int base = A_INTEGER_VALUE;
    return (int) letter - base;
  } // letter2int(char)

  /**
   * Converts given integer to character value.
   * @param i
   * The integer in which we want to get the character value of.
   * @return the character code of the given integer.
   */
  private static char int2letter(int i) {
    int base = A_INTEGER_VALUE;
    return (char) (i + base);
  } // int2letter(int)

  /**
   * Encrypts string of letters using character key and caesar cipher method.
   * @param str
   * Series of characters that should be converted.
   * @param letter
   * Letter key used to convert the String with the caesar cipher method.
   * @return the new, encrypted series of letters.
   */
  public static String caesarEncrypt(String str, char letter) {
    int targetKey = letter2int(letter);
    String encryptedString = "";
    for (int i = 0; i < str.length(); i++) {
      int letterKey = letter2int(str.charAt(i));
      int newKey = letterKey + targetKey;
      if (newKey > MAX_LETTER_CODE) {
        encryptedString += int2letter(newKey % (MAX_LETTER_CODE + 1));
      } else {
        encryptedString += int2letter(newKey);
      } // if
    } // for
    return encryptedString;
  } // caesarEncrypt(String, char)

  /**
   * Decrypts string of letters using character key and caesar cipher method.
   * @param str
   * Series of characters that should be decrypted.
   * @param letter
   * Letter key used to bring back the String to its original word.
   * @return the new, decrypted series of letters.
   */
  public static String caesarDecrypt(String str, char letter) {
    int targetKey = letter2int(letter);
    String decryptedString = "";
    for (int i = 0; i < str.length(); i++) {
      int letterKey = letter2int(str.charAt(i));
      int newKey = letterKey - targetKey;
      if (newKey < 0) {
        decryptedString += int2letter((MAX_LETTER_CODE + 1) + newKey);
      } else {
        decryptedString += int2letter(newKey);
      } // if
    } // for
    return decryptedString;
  } // caesarDecrypt(String, char)

  /**
   * Encrypts string of letters using key string and vigenere cipher method.
   * @param str
   * Series of characters that should be converted.
   * @param key
   * Series of characters used to convert the word to its encrypted version.
   * @return the new, encrypted series of letters.
   */
  public static String vigenereEncrypt(String str, String key) {
    String encrypString = "";
    int counter = 0;
    for (int i = 0; i < str.length(); i++) {
      int letterKey = letter2int(str.charAt(i));
      int targetKey = letter2int(key.charAt(counter));
      int newKey = letterKey + targetKey;
      if (newKey > MAX_LETTER_CODE) {
        encrypString += int2letter(newKey % (MAX_LETTER_CODE + 1));
      } else {
        encrypString += int2letter(newKey);
      } // if
      counter++;
      if (counter > key.length() - 1) {
        counter = 0;
      } // if
    } // for
    return encrypString;
  } // vigenereEncrypt(String, String)

  /**
   * Decrypts string of letters using character key and caesar cipher method.
   * @param str
   * Series of characters that should be decrypted.
   * @param key
   * Letter key used to bring back the String to its original word.
   * @return the new, decrypted series of letters.
   */
  public static String vigenereDecrypt(String str, String key) {
    String decryptedString = "";
    int counter = 0;
    for (int i = 0; i < str.length(); i++) {
      int letterKey = letter2int(str.charAt(i));
      int targetKey = letter2int(key.charAt(counter));
      int newKey = letterKey - targetKey;
      if (newKey < 0) {
        decryptedString += int2letter((MAX_LETTER_CODE + 1) + newKey);
      } else {
        decryptedString += int2letter(newKey);
      } // if
      counter++;
      if (counter > key.length() - 1) {
        counter = 0;
      } // if
    } // for
    return decryptedString;
  } // vigenereDecrypt(String, String)
} // class CipherUtils
