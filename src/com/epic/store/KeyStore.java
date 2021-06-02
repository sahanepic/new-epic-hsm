package com.epic.store;

import com.epic.utill.Print;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class KeyStore {

    private final static String path = "E:\\Intern\\software hsm\\new-epic-hsm-up\\store\\key.properties";
    public static void main(String[] args) {

        KeyStore kk =  KeyStore.getInstance();


        Print.sysOutPrintln(kk.getAllPropertyNames().toString());

        for (int x =0 ; x< 8 ; x++){
            if(!kk.containsKey("slot_"+x)){
                kk.setProperty("slot_"+x,"12A0BF00A0BF00"+x);
            }
        }


        Print.sysOutPrintln(kk.getAllPropertyNames().toString());

    }



    private final Properties configProp = new Properties();

    private KeyStore()  {
        //Private constructor to restrict new instances
        InputStream in = null;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Reading all properties from the file");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Bill Pugh Solution for singleton pattern
    private static class LazyHolder
    {
        private static final KeyStore INSTANCE = new KeyStore();
    }

    public static KeyStore getInstance()
    {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key){
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames(){
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key){
        return configProp.containsKey(key);
    }


    public void setProperty(String key, String value){
        configProp.setProperty(key, value);
        try {
            flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() throws FileNotFoundException, IOException {
        try (final OutputStream outputstream
                     = new FileOutputStream(path);) {
            configProp.store(outputstream,"File Updated");
            outputstream.close();
        }
    }

}
