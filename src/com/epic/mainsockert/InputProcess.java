package com.epic.mainsockert;


import com.epic.decrypt.Decrypt;
import com.epic.encrypt.Encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class InputProcess {


    public static String handleRequewest(String req) {

        String reqhead = req.substring(0,2);
        String res = null;
        switch (reqhead){
            case "PU":
                String alg = req.substring(2,4);
                switch (alg){
                    case "00":
                        int slot = Integer.valueOf( req.substring(4,6)) ;
                        String plaintext =  req.substring(6,req.length()-1);
                        try {
                            res = "PV00" + Encrypt.textSlotDESEncryption(slot, plaintext) ;
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
                        break;
                }
                break;
            case "PY":
                res = "PX";
                break;
            default:
                res ="ERROR";
        }

        return  res;
    }
}
