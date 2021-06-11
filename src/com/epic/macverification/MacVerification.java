package com.epic.macverification;

import com.epic.store.TemKeyStore;
import com.epic.utill.Print;
import org.jpos.iso.ISOUtil;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class MacVerification {


    public static void main(String[] args) {
        boolean verfymac = false;
        try {
            verfymac = verifyHmac512(0,"sahan","4d1f1aa73096a8fc1589500b1c3de766e973461fb6e1cc4f4167d7f3f461222468f6e08f50439c0a5eddcdfb6487982180e8264a1acd58b0c0a4d56d8a495a64");
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        Print.sysOutPrintln("The Verification Result " + verfymac );

    }

    public static boolean verifyHmac512(SecretKey key, byte[] data, byte [] hmacverification)
            throws GeneralSecurityException
    {
        Mac hmac = Mac.getInstance("HMacSHA512");
        hmac.init(key);
        byte[] newhmac= hmac.doFinal(data);
        return Arrays.equals(hmacverification,newhmac);
    }



    public static boolean verifyHmac512(int slot, String datastr, String hmacverification)
            throws GeneralSecurityException
    {
        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA512");
        Mac hmac = Mac.getInstance("HMacSHA512");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        byte[] byrehmacverification = ISOUtil.hex2byte(hmacverification);
        byte[] newhmac= hmac.doFinal(data);
        return Arrays.equals(byrehmacverification,newhmac);
    }


    public static boolean verifyHmac160(int slot, String datastr, String hmacverification)
            throws GeneralSecurityException
    {
        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA1");
        Mac hmac = Mac.getInstance("HMacSHA1");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        byte[] byrehmacverification = ISOUtil.hex2byte(hmacverification);
        byte[] newhmac= hmac.doFinal(data);
        return Arrays.equals(byrehmacverification,newhmac);
    }


    public static boolean verifyHmac224(int slot, String datastr, String hmacverification)
            throws GeneralSecurityException
    {
        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA224");
        Mac hmac = Mac.getInstance("HMacSHA224");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        byte[] byrehmacverification = ISOUtil.hex2byte(hmacverification);
        byte[] newhmac= hmac.doFinal(data);
        return Arrays.equals(byrehmacverification,newhmac);
    }


    public static boolean verifyHmac256(int slot, String datastr, String hmacverification)
            throws GeneralSecurityException
    {
        String skey = TemKeyStore.getInstance().getProperty("slot_" + slot);
        byte[] k = ISOUtil.hex2byte(skey);
        SecretKeySpec key = new SecretKeySpec(k , "HmacSHA256");
        Mac hmac = Mac.getInstance("HMacSHA256");
        hmac.init(key);
        byte[] data = datastr.getBytes();
        byte[] byrehmacverification = ISOUtil.hex2byte(hmacverification);
        byte[] newhmac= hmac.doFinal(data);
        return Arrays.equals(byrehmacverification,newhmac);
    }


}
