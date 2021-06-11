package com.epic.macgeneration;

import com.epic.store.TemKeyStore;
import com.epic.utill.Print;
import org.jpos.iso.ISOUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class MacGeneration {

    public static void main(String[] args) {
        Print.sysOutPrintln("Mac Generation");
        try {
//            SecretKey ll = generateKey();
            byte[] mac =  calculateHmac160(0,"danidu");

            String smac = ISOUtil.byte2hex(mac);
            Print.sysOutPrintln("S MAc " +  smac);




        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }




    public static byte[] calculateHmac512(int slot, String datastr )
            throws GeneralSecurityException
    {


        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA512");
        Mac hmac = Mac.getInstance("HMacSHA512");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        return hmac.doFinal(data);
    }

    public static byte[] calculateHmac160(int slot, String datastr )
            throws GeneralSecurityException
    {


        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA1");
        Mac hmac = Mac.getInstance("HMacSHA1");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        return hmac.doFinal(data);
    }

    public static byte[] calculateHmac224(int slot, String datastr )
            throws GeneralSecurityException
    {


        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA224");
        Mac hmac = Mac.getInstance("HMacSHA224");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        return hmac.doFinal(data);
    }

    public static byte[] calculateHmac256(int slot, String datastr )
            throws GeneralSecurityException
    {


        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA256");
        Mac hmac = Mac.getInstance("HMacSHA256");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        return hmac.doFinal(data);
    }

}
