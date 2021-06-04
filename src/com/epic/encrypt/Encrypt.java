package com.epic.encrypt;

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

public class Encrypt {


    public static void main(String[] args) {


        try {
            Encrypt ee = new Encrypt();
            String val = ee.textSlotAESEncryption(0,"sahanbcs");
//            String val = ee.textSlotEncryption("sahanbcs");
//            String val = ee.textSlotDESEncryption(8,"sahanbcs");

            Print.sysOutPrint("The Enccrypted VAlue " + val );
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



    public static String textAESEncryption(String plaintxt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String cipertxt = null;
        String s = "sahan";

        SecureRandom sr1 = SecureRandom.getInstance("SHA1PRNG");
        sr1.setSeed(s.getBytes());
        byte[] k = new byte[128/8];
        sr1.nextBytes(k);
        SecretKeySpec encKey = new SecretKeySpec(k,"AES");

        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,encKey);

        byte[] cb =  cipher.doFinal(plaintxt.getBytes());

        cipertxt = Base64.getEncoder().encodeToString(cb);
        return cipertxt;
    }


    public static String textSlotAESEncryption(int sloat, String plaintxt ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        System.out.println( "slot "  +sloat);
        String  cipertxt = null;
        byte[] k = new byte[128/8];
        String  key = TemKeyStore.getInstance().getProperty("slot_"+sloat);
        System.out.println( " key  =" + key  +" bytes" );
        k = ISOUtil.hex2byte(key);

        SecretKeySpec encKey = new SecretKeySpec(k,"AES");
        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,encKey);
        byte[] cb =  cipher.doFinal(plaintxt.getBytes());
        cipertxt =  ISOUtil.byte2hex(cb);
        return cipertxt;
    }


    public static String textSlotDESEncryption(int sloat, String plaintxt ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String  cipertxt = null;
        byte[] k = new byte[64/8];
        String  key = TemKeyStore.getInstance().getProperty("slot_"+sloat);
        k = ISOUtil.hex2byte(key);
        SecretKeySpec encKey = new SecretKeySpec(k,"DES");
        Cipher cipher=Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,encKey);
        byte[] cb =  cipher.doFinal(plaintxt.getBytes());
        cipertxt =  ISOUtil.byte2hex(cb);
        return cipertxt;
    }


}
