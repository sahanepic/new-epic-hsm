package com.epic.utill;

import com.epic.decrypt.Decrypt;
import com.epic.encrypt.Encrypt;
import com.epic.hashing.Hash;
import com.epic.login.Login;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MessageFlow {

    private static InputStreamReader rr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(rr);
    private static final String ENMSG = "The Encrypted Value: ";
    private static final String DEMSG = "The Decrypted Value: ";
    private static final String HASHMSG = "The Hashed Value: ";

    public static void printEncrypt() throws IOException {

        String choice = MessageFlow.printTxtFlow("Encryption");
        Print.sysOutPrintln("Enter plain text: ");
        String pText = br.readLine();
        Print.sysOutPrintln("Enter slot");
        int eslot = Integer.parseInt(br.readLine().trim());

        if (choice.equals("1")) {
            try {
                String enValue = Encrypt.textSlotDESEncryption(eslot, pText);
                Print.sysOutPrintln(ENMSG + enValue);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("2")) {
            try {
                String enValue = Encrypt.textSlotAESEncryption(eslot, pText);
                Print.sysOutPrintln(ENMSG + enValue);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } else {
            Print.sysOutPrintln("Invalid argument...!!");
            System.exit(0);
        }
    }

    public static void printDecrypt() throws IOException {


        String choice = MessageFlow.printTxtFlow("Decryption");
        Print.sysOutPrintln("Enter ciper text: ");
        String ciperText = br.readLine();
        Print.sysOutPrintln("Enter slot");
        int slot = Integer.parseInt(br.readLine().trim());
        if (choice.equals("1")) {
            try {
                Print.sysOutPrintln(DEMSG + Decrypt.textSlotDESDecryption(slot, ciperText));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("2")) {
            try {

                Print.sysOutPrintln(ENMSG + Decrypt.textSlotAESDecryption(slot, ciperText));
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } else {
            Print.sysOutPrintln("Invalid argument...!!");
            System.exit(0);
        }
    }

    public static void printHashing() throws IOException {
        String choice = MessageFlow.printTxtFlow("Hashing");
        Print.sysOutPrintln("Enter plain text: ");
        String plainText = br.readLine();
        if (choice.equals("1")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textMD5Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("2")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textSHA1Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("3")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textSHA224Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("4")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textSHA256Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("5")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textSHA384Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else if (choice.equals("6")) {
            try {
                Print.sysOutPrintln(HASHMSG + Hash.textSHA512Hashing(plainText));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            Print.sysOutPrintln("Invalid argument...!!");
            System.exit(0);
        }

    }

    private static String printTxtFlow(String type) throws IOException {
        if (type.equals("Hashing")) {
            Print.sysOutPrintln("Enter " + type + " mode");
            Print.sysOutPrintln("1. MD5 " + type);
            Print.sysOutPrintln("2. SHA1 " + type);
            Print.sysOutPrintln("3. SHA224 " + type);
            Print.sysOutPrintln("3. SHA256 " + type);
            Print.sysOutPrintln("3. SHA384 " + type);
            Print.sysOutPrintln("3. SHA512 " + type);
            Print.sysOutPrintln("Enter Mode");
        } else {
            Print.sysOutPrintln("Enter " + type + " mode");
            Print.sysOutPrintln("1. DES " + type + " - Available slots [8-15]");
            Print.sysOutPrintln("2. AES " + type + " - Available slots [0-7]");
            Print.sysOutPrintln("3. Exit ");
            Print.sysOutPrintln("Enter Mode");
        }
        return br.readLine();
    }

    public static void loginFlow() throws IOException {
        Print.sysOutPrint("Enter your User name: ");
        String username = br.readLine();
        Print.sysOutPrint("Enter your password: ");
        String password = null;
        Console c = System.console();
        if (c == null) { //IN  IDE
            password = br.readLine().trim();
            if (password.length() > 0) {
                Print.sysOutPrintln("Valid Password ");
            } else {
                Print.sysOutPrintln("Invalid Password ");
            }
        } else { //Outside Eclipse IDE
            password = new String(c.readPassword("Password: "));
            Print.sysOutPrint(password);
        }

        if (Login.authLogin(username, password)) {
            while (true) {
                Print.sysOutPrintln("1. Encrypt a text: ");
                Print.sysOutPrintln("2. Decrypt a text: ");
                Print.sysOutPrintln("3. Hash a text ");
                Print.sysOutPrintln("4. Exit ");
                Print.sysOutPrintln("Enter your option");
                String option = br.readLine();


                switch (option) {
                    case "1":
                        MessageFlow.printEncrypt();
                        break;
                    case "2":
                        MessageFlow.printDecrypt();
                        break;
                    case "3":
                        MessageFlow.printHashing();
                        break;
                    case "4":
                        System.exit(0);
                        break;
                    default:
                        Print.sysOutPrintln("Invalid argument");

                }
            }
        }
    }
}
