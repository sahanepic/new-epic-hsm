package com.epic.mainsockert;


import com.epic.decrypt.Decrypt;
import com.epic.encrypt.Encrypt;
import com.epic.hashing.Hash;
import com.epic.macgeneration.MacGeneration;
import com.epic.macverification.MacVerification;
import org.jpos.iso.ISOUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class InputProcess {


    public static String handleRequest(String req) {

        String reqhead = req.substring(0, 2);
        String res = null;
        int slot;
        String text = null;
        String alg = null;
        String ver = null;
        switch (reqhead) {
            case "EN":
                alg = req.substring(2, 4);
                switch (alg) {
                    case "00":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "ER00" + Encrypt.textSlotDESEncryption(slot, text);
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "01":
                        slot = Integer.valueOf(req.substring(4, 6));
                        text = req.substring(6, req.length());
                        System.out.println("text" + text);
                        try {
                            res = "ER01" + Encrypt.textSlotAESEncryption(slot, text);
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid code argument..!");
                        System.exit(1);

                }

                break;
            case "DE":
                alg = req.substring(2, 4);
                switch (alg) {
                    case "00":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "DR00" + Decrypt.textSlotDESDecryption(slot, text);
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "01":
                        slot = Integer.parseInt(req.substring(4, 6));
                        text = req.substring(6, req.length());
                        System.out.println("text" + text);
                        try {
                            res = "DR01" + Decrypt.textSlotAESDecryption(slot, text);
                        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid code argument..!");
                        System.exit(1);

                }
                break;
            case "HE":
                alg = req.substring(2, 4);
                switch (alg) {
                    case "00":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR00" + Hash.textMD5Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "01":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR01" + Hash.textSHA1Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "02":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR02" + Hash.textSHA224Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "03":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR03" + Hash.textSHA256Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "04":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR01" + Hash.textSHA384Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "05":
                        text = req.substring(4);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "HR05" + Hash.textSHA512Hashing(text);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid code code argument..!");
                        System.exit(1);

                }
                break;
            case "MG":
                alg = req.substring(2, 4);
                switch (alg) {
                    case "00":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "MR00" + ISOUtil.byte2hex(MacGeneration.calculateHmac160(slot, text));
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "01":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "MR00" + ISOUtil.byte2hex(MacGeneration.calculateHmac224(slot, text));
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "02":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "MR00" + ISOUtil.byte2hex(MacGeneration.calculateHmac256(slot, text));
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "03":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(6);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "MR00" + ISOUtil.byte2hex(MacGeneration.calculateHmac512(slot, text));
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid code argument..!");
                        System.exit(1);

                }
                break;
            case "MV":
                alg = req.substring(2, 4);
                switch (alg) {
                    case "00":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(7,12);
                        ver = req.substring(12);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "VR00" + MacVerification.verifyHmac160(slot, text,ver);
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "01":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(7,12);
                        ver = req.substring(12);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "VR00" + MacVerification.verifyHmac224(slot, text,ver);
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "02":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(7,12);
                        ver = req.substring(12);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "VR00" + MacVerification.verifyHmac256(slot, text,ver);
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "03":
                        slot = Integer.parseInt(req.substring(4, 6));
                        System.out.println("Slot " + slot);
                        text = req.substring(7,12);
                        ver = req.substring(12);
                        try {
//                            System.out.println("Inputs " + text + " " + slot);
                            res = "VR00" + MacVerification.verifyHmac512(slot, text,ver);
                        } catch (GeneralSecurityException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid code argument..!");
                        System.exit(1);

                }
                break;
            default:
                res = "ERROR";
        }

        return res;
    }
}
