package com.epic.main;

//import com.epic.utill.Print;

import com.epic.utill.Print;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        InputStreamReader  rr = new InputStreamReader(System.in);
        BufferedReader br =  new BufferedReader(rr);
        Print.sysOutPrint("Enter your name: ");
        String n=br.readLine();
        Print.sysOutPrint("Enter your password: ");
        char[] pass = System.console().readPassword() ;
        System.out.println("Welcome "+new String(pass));

    }




}
