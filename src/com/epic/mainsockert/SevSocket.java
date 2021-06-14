package com.epic.mainsockert;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SevSocket {

    public static void main(String[] args) {

        Socket socket = null;
        ServerSocket serverSocker = null;

        System.out.println("Server Sockert Listening......");

        try {
            serverSocker = new ServerSocket(4000);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Server error");
        }


        while (true) {
            try {
                Socket ss = serverSocker.accept();
                System.out.println("The Connection Established");
                ClientServerThread clientServerThread = new ClientServerThread(ss);
                Thread thread = new Thread(clientServerThread);
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("The Connection Error");
            }




        }


    }
}
