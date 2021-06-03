package com.epic.main;

//import com.epic.utill.Print;

import com.epic.utill.Print;

import java.io.*;

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

        Print.sysOutPrint("Enter your Name "+username + "  and  password: " + password );


    }




}
