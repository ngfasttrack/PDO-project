package utilities;

import java.io.*;

public class ConvertFileToByte {
    public static byte[] getBytes(File file) throws FileNotFoundException, IOException{
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(file);
        int read;
        while((read = fis.read(buffer))!=-1){
            baos.write(buffer,0,read);
        }
        fis.close();
        return baos.toByteArray();
    }
}
