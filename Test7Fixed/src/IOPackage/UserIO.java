package IOPackage;

import java.io.*;

/**
 * Created by hackeru on 4/6/2017.
 */
public class UserIO implements IO{
    @Override
    public String input() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void output(String s) {
        System.out.println(s);

    }
}