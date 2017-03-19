package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\ExFeb19\\textT.txt");
        File fileEX = new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\ExFeb19\\intNums.txt");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            outputStream.write("hello".getBytes());


            //region classEx
            int realmax = Integer.MIN_VALUE;
            outputStream = new FileOutputStream(fileEX);
            Random random = new Random(System.currentTimeMillis());
            int i = Math.abs(random.nextInt(100000)) + 100000;
            byte[] iNt = new byte[4];
            for (int j = 0; j < i; j++) {
                int t = random.nextInt();
                if( t > realmax )
                    realmax = t;
                /*byte[] iNt = new byte[4];
                for (int k = 3; k > 0; k--)
                    iNt[k] = (byte)(t >> 8 * k);*/
                ByteBuffer.wrap(iNt).putInt(t);
                outputStream.write(iNt);
            }
            System.out.println("realmax  " + realmax);



            //ex2 findMax
            int maxInfile = Integer.MIN_VALUE;
            inputStream = new FileInputStream(fileEX);
            byte [] bytesB = new byte[4];
            int actuallyRead1;
            while((actuallyRead1 = inputStream.read(bytesB))!= -1){
                if(actuallyRead1 != 4)
                    throw new InvalidParameterException("not ints");
                int temp = 0;
                temp = ByteBuffer.wrap(bytesB).getInt();
              /*for (int j = 0; j < 4; j++) {
                    temp |= (int) bytesB[j] *//*& 0xff*//*;
                    temp <<= 8;
                }*/
                if(temp > maxInfile)
                    maxInfile = temp;
            }
            System.out.println(maxInfile);

            //endregion

            inputStream = new FileInputStream(file);
            byte [] buffer = new byte[64]; // TODO: buffer with size 2^n is easier for computer
            int actuallyRead;
            StringBuilder stringBuilder = new StringBuilder();
            while((actuallyRead = inputStream.read(buffer))!= -1){
                stringBuilder.append(new String(buffer, 0, actuallyRead));
            }
            System.out.println(stringBuilder.toString());

        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }




        //List<List<Object>> list=new List<List<>>();
    }
}
