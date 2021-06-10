package com.epic.macgeneration;

import com.epic.utill.Print;
import org.jpos.iso.ISOUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;

public class MacGeneration {

    public static void main(String[] args) {
        Print.sysOutPrintln("Mac Generation");
        try {
            SecretKey ll = generateKey();
            byte[] mac =  calculateHmac(ll,"sahan".getBytes());

            String smac = ISOUtil.byte2hex(mac);
            Print.sysOutPrintln("S MAc " +  smac);


        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }


    public static SecretKey generateKey()
            throws GeneralSecurityException
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    public static byte[] calculateHmac(SecretKey key, byte[] data)
            throws GeneralSecurityException
    {
        Mac hmac = Mac.getInstance("HMacSHA512");
        hmac.init(key);
        return hmac.doFinal(data);
    }

}
