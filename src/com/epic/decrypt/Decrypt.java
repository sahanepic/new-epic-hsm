package com.epic.decrypt;

import com.epic.store.TemKeyStore;
import com.epic.utill.Print;
import org.jpos.iso.ISOUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Decrypt {

    public static void main(String[] args) {

        try {
            Decrypt ee = new Decrypt();
//            String val = ee.textDecryption("Ir0nDAQA1xBfLblWDi8PJg==");
//            String val = ee.textSlotDecryption("hzotcsTSm6E6oXsoyPHWhA==");
            String val = ee.textSlotAESDecryption( 0 ,"3ae84e5d1cdd1f1e62705c3e1a073ce2");

//            String val = ee.textSlotDESDecryption( 8 ,"40f5bad55e39e3806c99f1f5d0187b13");
            Print.sysOutPrint("The Decrypted VAlue  " + val );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }



    }


    public static String textAESDecryption(String cipertxt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String  plaintxt = null;
        String s = "sahan";

        SecureRandom sr1 = SecureRandom.getInstance("SHA1PRNG");
        sr1.setSeed(s.getBytes());
        byte[] k = new byte[128/8];
        sr1.nextBytes(k);
        SecretKeySpec encKey = new SecretKeySpec(k,"AES");

        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,encKey);

        byte[] cb =  cipher.doFinal(Base64.getDecoder().decode(cipertxt));

        plaintxt =  new String(cb);
        return plaintxt;
    }

    public static String textSlotAESDecryption(int sloat , String cipertxt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String  plaintxt = null;
        byte[] k = new byte[128/8];
        String  key = TemKeyStore.getInstance().getProperty("slot_"+sloat);
        k = ISOUtil.hex2byte(key);
        SecretKeySpec encKey = new SecretKeySpec(k,"AES");
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,encKey);
        byte[] ci = ISOUtil.hex2byte(cipertxt);
        byte[] cb =  cipher.doFinal(ci);
        plaintxt =  new String(cb);
        return plaintxt;
    }


    public static String textSlotDESDecryption(int sloat , String cipertxt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String  plaintxt = null;
        byte[] k = new byte[64/8];
        String  key = TemKeyStore.getInstance().getProperty("slot_"+sloat);
        k = ISOUtil.hex2byte(key);
        SecretKeySpec encKey = new SecretKeySpec(k,"DES");
        Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,encKey);
        byte[] ci = ISOUtil.hex2byte(cipertxt);
        byte[] cb =  cipher.doFinal(ci);
        plaintxt =  new String(cb);
        return plaintxt;
    }


}
