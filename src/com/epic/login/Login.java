package com.epic.login;

import com.epic.utill.Print;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {
    private static String data = "G:\\Acadamic\\Epic\\new-epic-hsm\\loginDetails\\login.txt";
//        private static String data = "E:\\Intern\\software hsm\\new-epic-hsm-up\\loginDetails\\login.txt";
    private static int ln;

    public static void readFile() {

        try {
            FileReader fileReader = new FileReader(data);
            Print.sysOutPrintln("file exist");
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(data);
                Print.sysOutPrintln("file created");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    static void countLines() {
        try {
            ln = 0;
            RandomAccessFile raf = new RandomAccessFile(data, "rw");
            for (int i = 0; raf.readLine() != null; i++) {
                ln++;
            }
            System.out.println("number of lines:" + ln);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean authLogin(String username, String password) {
        boolean ok = false;
        Login.countLines();
        try {

            String tmp;
            RandomAccessFile raf = new RandomAccessFile(data, "rw");
            for (int i = 0; i < ln; i += 2) {
                String user = raf.readLine().substring(10);
                String pass = raf.readLine().substring(10);
//                System.out.println(user);
                if ((username.equals(user)) && (getMd5(password).equals(pass))) {
                    Print.sysOutPrintln("Login success welcome " + username);
                    ok = true;
                    break;
                } else if (i == (ln - 2)) {
                    Print.sysOutPrintln("Invalid credintials");

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }


}
