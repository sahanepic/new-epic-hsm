package com.epic.mainsockert;


import java.io.*;
import java.net.Socket;

public class ClientServerThread implements Runnable {


    private Socket socket=null;
    private BufferedReader is = null;
    private PrintWriter os=null;


    public ClientServerThread(Socket ss) {
        this.socket =ss;
    }


    @Override
    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()) );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error in server thread");
        }

        String req= null;
        String res=null;

        try {
            req=is.readLine() ;

            res = InputProcess.handleRequewest(req);
            os.println(res);
            os.flush();
            System.out.println("Request from Client  :  "+ req);
            System.out.println("Response to Client  :  "+res);
        } catch (IOException e) {
            e.printStackTrace();
            req=  Thread.currentThread().getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client "+req+" terminated abruptly");
        }catch(NullPointerException e){
            req=  Thread.currentThread().getName(); //reused String line for getting thread name
            System.out.println("Client "+req+" Closed");
        }
        finally {
            try {
//                System.out.println("Connection Closing");
                if(is != null){
                    is.close();
//                    System.out.println(" Socket Input Stream Closed");
                }
                if(os != null){
                    os.close();
//                    System.out.println(" Socket OutPut Stream Closed");
                }
                if(socket != null){
                    socket.close();
//                    System.out.println(" Socket Closed");
                }

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Socket Closed Error ");
            }
        }
    }
}
