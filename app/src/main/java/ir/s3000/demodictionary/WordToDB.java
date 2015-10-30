package ir.s3000.demodictionary;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by S3000 on 10/30/2015.
 */
public class WordToDB {
    Context context;

    public WordToDB(Context context)
    {
        this.context=context;
    }
    public void start()
    {
        try {
            byte[] gzipData = fullyReadFileToBytes();
            Decompress decompress=new Decompress(gzipData);
            DictDataSource dictDataSource=new DictDataSource(context);
            for(int i=0;i<decompress.mWord.length;i++)
            {
                dictDataSource.createWord(decompress.mWord[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] fullyReadFileToBytes() throws IOException {
        File file=getFile();
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
    private File getFile()
    {
        File file = new File(context.getFilesDir() + File.separator + "DefaultProperties.xml");
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.db);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte buf[]=new byte[1024];
            int len;
            while((len=inputStream.read(buf))>0) {
                fileOutputStream.write(buf,0,len);
            }

            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e1) {}
        return file;
    }

}
