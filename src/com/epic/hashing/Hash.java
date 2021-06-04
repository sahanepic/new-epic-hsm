package com.epic.hashing;

import com.epic.utill.Print;
import org.jpos.iso.ISOUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static void main(String[] args) {

        Hash hh = new Hash();
        try {
//            String ss =  hh.textMD5Hashing("sahanbc2s");
//            String ss =  hh.textSHA1Hashing("sahanbc2s");
//            String ss =  hh.textSHA224Hashing("sahanbc2s");
//            String ss =  hh.textSHA256Hashing("sahanbc2s");
//            String ss =  hh.textSHA384Hashing("sahanbc2s");
            String ss =  hh.textSHA512Hashing("sahanbc2s");

            Print.sysOutPrintln("Hash " + ss );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }



    public String textMD5Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }

    public String textSHA1Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }

    public String textSHA224Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-224");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }

    public String textSHA256Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }

    public String textSHA384Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-384");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }


    public String textSHA512Hashing(String plaintxt) throws NoSuchAlgorithmException {
        String hash=null;
        byte[] b = plaintxt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(b);
        byte[] digest = md.digest();
        hash = ISOUtil.byte2hex(digest);
        return hash;
    }






}
