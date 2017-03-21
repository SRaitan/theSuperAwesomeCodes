package com.company;

import java.io.*;

/**
 * Created by hackeru on 3/19/2017.
 */
public class XorCipher implements FileOperation{
    private void xor (File original, int key){
        OutputStream outputStream = null;
        InputStream inputStream = null;
        File newFile = returnFile(original.getAbsolutePath(), true);
        try {
            outputStream = new FileOutputStream(newFile);
            inputStream = new FileInputStream(original);
            int oneByte;
            while ((oneByte = inputStream.read()) != -1) {

            }
            // friendly & more explained exceptions
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.getMessage();
                }
        }
    }

    @Override
    public void encrypt(File original, int key) {

    }

    @Override
    public void decrypt(File original, int key) {

    }
    public File returnFile(String fname, boolean encrypt){
        int pos = fname.lastIndexOf(".");
        if (pos > 0)
            fname = fname.substring(0, pos);
        if(encrypt)
            return new File(fname + "_encrypted.txt");
        return new File(fname + "_decrypted.txt");
    }
}
