package com.epic.utill;

import com.epic.decrypt.Decrypt;
import com.epic.encrypt.Encrypt;
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

    public static void printEncrypt() throws IOException {

        Print.sysOutPrintln("Enter plain text: ");
        String pText = br.readLine();
        Print.sysOutPrintln("Enter slot");
        int eslot = Integer.parseInt(br.readLine().trim());
        try {
            String enValue = Encrypt.textSlotAESEncryption(eslot, pText);
            Print.sysOutPrintln("The Encrypted Value: " + enValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static void pringDecrypt() throws IOException {
        Print.sysOutPrintln("Enter ciper text: ");
        String ciperText = br.readLine();
        Print.sysOutPrintln("Enter slot");
        int slot = Integer.parseInt(br.readLine().trim());
        try {
            String deValue = Decrypt.textSlotAESDecryption(slot, ciperText);
            Print.sysOutPrintln("The Encrypted Value: " + deValue);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
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
            Print.sysOutPrintln("3. EXIT ");
            Print.sysOutPrintln("Enter your option");
            String option = br.readLine();


                switch (option) {
                    case "1":
                        MessageFlow.printEncrypt();
                        break;
                    case "2":
                        MessageFlow.pringDecrypt();
                        break;
                    case "3":
                        System.exit(0);
                        break;
                    default:
                        Print.sysOutPrintln("Invalid argument");

                }
            }
        }
    }
}
