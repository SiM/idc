package idc;

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.math.*;

public class CryptoManager {

    static private PublicKey public_key;
    static private PrivateKey private_key;

    private Signature IdSign;

    public CryptoManager() {
        File FilePub = new File("id.pub");
        File FilePriv = new File("id.private");

        try {
            FileOutputStream FileStreamPub = new FileOutputStream(FilePub);
            FileOutputStream FileStreamPriv = new FileOutputStream(FilePriv);

            KeyPairGenerator KeyFact = KeyPairGenerator.getInstance("RSA");
            KeyFact.initialize(512);
            KeyPair PairOfKey = KeyFact.genKeyPair();

            public_key = PairOfKey.getPublic();
            private_key = PairOfKey.getPrivate();

            byte[] PubKey = public_key.getEncoded();
            byte[] PrivKey = private_key.getEncoded();

            if (!FilePub.isFile()) {
                if (FilePub.createNewFile()) {
                    FileStreamPub.write(PubKey);
                }
            }


            if (!FilePriv.isFile()) {
                if (FilePriv.createNewFile()) {
                    FileStreamPriv.write(PrivKey);
                }
            }

            //------------- AUTHENTIFICATION ---------------------

            IdSign = Signature.getInstance("SHA1withRSA");
            IdSign.initSign(private_key);

            /* Update and sign the data */
            //IdSign.update(data);
            byte[] sig = IdSign.sign();

            IdSign.initVerify(public_key);

            /* Update and verify the data */
            //IdSign.update(data);
            boolean verifies = IdSign.verify(sig);


            System.out.println("signature verifies: " + verifies);

        } catch (SignatureException err) {
            System.out.println(err);
        } catch (NoSuchAlgorithmException err) {
            System.out.println(err);
        } catch (Exception err) {
            System.out.println(err);
        }

        integrity();

    }

    public Message decode(Message msg) {
        integrity();
        return msg;
    }
    static public String getId() {return "ID:BLABLA";}
    private void integrity() {

    }
}
