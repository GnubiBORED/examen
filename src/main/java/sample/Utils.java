package sample;

import sample.Test.JavaFX_OpenFile;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    public String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text).append("\n");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }
}
