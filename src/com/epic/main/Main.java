package com.epic.main;

//import com.epic.utill.Print;

import com.epic.decrypt.Decrypt;
import com.epic.encrypt.Encrypt;
import com.epic.login.Login;
import com.epic.utill.Print;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {


    public static void main(String[] args) throws IOException {


        InputStreamReader  rr = new InputStreamReader(System.in);
        BufferedReader br =  new BufferedReader(rr);
        Print.sysOutPrint("Enter your User name: ");
        String username=br.readLine();
        Print.sysOutPrint("Enter your password: ");
        String password= null;
        Console c=System.console();
        if (c==null) { //IN  IDE
            password = br.readLine().trim();
            if (password.length()>0) {
                Print.sysOutPrintln("Valid Password ");
            } else {
                Print.sysOutPrintln("Invalid Password ");
            }
        } else { //Outside Eclipse IDE
            Print.sysOutPrint( password = new String(c.readPassword("Password: ")));
        }

//        Print.sysOutPrint("Enter your Name "+username + "  and  password: " + password );
        if(Login.authLogin(username,password))
        {
            Print.sysOutPrintln("1. Encrypt a text: ");
            Print.sysOutPrintln("2. Decrypt a text: ");
            Print.sysOutPrintln("3. EXIT ");
            Print.sysOutPrintln("Enter your option");
            String option = br.readLine();
            switch (option)
            {
                case "1":
                    Print.sysOutPrintln("Enter plain text: ");
                    String pText = br.readLine();
                    Print.sysOutPrintln("Enter slot");
                    int eslot = Integer.parseInt(br.readLine().trim());
                    try {
                        String enValue = Encrypt.textSlotAESEncryption(eslot, pText);
                        Print.sysOutPrint("The Enccrypted VAlue " + enValue );
                    }
                    catch (NoSuchAlgorithmException| NoSuchPaddingException |InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    Print.sysOutPrintln("Enter ciper text: ");
                    String ciperText = br.readLine();
                    Print.sysOutPrintln("Enter slot");
                    int slot = Integer.parseInt(br.readLine().trim());
                    try {
                        String deValue =Decrypt.textSlotAESDecryption(slot,ciperText);
                        Print.sysOutPrint("The Enccrypted VAlue " + deValue );
                    }
                    catch (NoSuchAlgorithmException| NoSuchPaddingException |InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Print.sysOutPrintln("Invalid argument");

            }
        }

    }




}
