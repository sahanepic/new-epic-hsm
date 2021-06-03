package com.epic.encrypt;

import com.epic.utill.Print;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.jpos.iso.ISOUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class Encrypt {


    public static void main(String[] args) {


        try {
            Encrypt ee = new Encrypt();
            String val = ee.textSlotEncryption("sahanbcs");
//            String val = ee.textSlotEncryption("sahanbcs");
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



    public String textEncryption(String plaintxt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

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


    public String textSlotEncryption(String plaintxt ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        String  cipertxt = null;
        byte[] k = new byte[128/8];
        k = ISOUtil.hex2byte("12A0BF00A0BF00012A0BF00A0BF00011");
        System.out.println("Byte Lenth " + k.length);
        SecretKeySpec encKey = new SecretKeySpec(k,"AES");

        Cipher cipher=Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,encKey);

        byte[] cb =  cipher.doFinal(plaintxt.getBytes());

//        cipertxt = Base64.getEncoder().encodeToString(cb);
        cipertxt =  ISOUtil.byte2hex(cb);

        return cipertxt;
    }

}
