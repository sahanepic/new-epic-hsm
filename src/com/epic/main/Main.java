package com.epic.main;

import com.epic.login.Login;
import com.epic.utill.Print;

import com.epic.utill.Print;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {

        InputStreamReader  rr = new InputStreamReader(System.in);
        BufferedReader br =  new BufferedReader(rr);
        Login.readFile();
        Print.sysOutPrint("Enter your username: ");
        String userName=br.readLine();
        Print.sysOutPrint("Enter your password: ");
        String password = br.readLine();
        Login.authLogin(userName,password);

    }



}
