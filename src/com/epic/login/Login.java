package com.epic.login;

import com.epic.utill.Print;

import java.io.*;

public class Login {
    private static String data = "E:\\Intern\\software hsm\\new-epic-hsm-up\\loginDetails\\login.txt";
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

    public static void authLogin(String username, String password) {

        Login.countLines();
        try {
            String tmp;
            RandomAccessFile raf = new RandomAccessFile(data, "rw");
            for (int i = 0; i < ln; i+=2) {
                String user = raf.readLine().substring(10);
                String pass = raf.readLine().substring(10);
                System.out.println(user);
                if ((username.equals(user)) && (password.equals(pass))) {
                    Print.sysOutPrintln("Login success welcome " + username);
                    break;
                } else {
                    Print.sysOutPrintln("Invalid credintials");
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
