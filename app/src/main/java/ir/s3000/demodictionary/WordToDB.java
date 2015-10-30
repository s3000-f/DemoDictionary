package ir.s3000.demodictionary;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by S3000 on 10/30/2015.
 */
public class WordToDB {


    public WordToDB(Context context , File file)
    {
        try {
            byte[] gzipData = fullyReadFileToBytes(file);
            Decompress decompress=new Decompress(gzipData);
            decompress.mWord;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    byte[] fullyReadFileToBytes(File file) throws IOException {

        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return bytes;
    }
}
