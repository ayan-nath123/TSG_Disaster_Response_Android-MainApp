package com.tts.www.tsg_disaster_response_app.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAEncryption {
    public static String getSHA(String password)
    {
        try{
            //Static getIstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //digest() method called
            //to calculate message digesr of an input
            //and return array of byte
            byte[] messageDigest = md.digest(password.getBytes("UTF-8"));
            //convert byte array into signum representation
            BigInteger no = new BigInteger(1,messageDigest);

            //convert message digest to hex value
            String hashText = no.toString(16);

            while(hashText.length() < 32){
                hashText = "0" + hashText;
            }
            return hashText;
        }
        //For Specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception Thrown" + " For incorrect algoreithm: " + e);
            return null;
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }

}
