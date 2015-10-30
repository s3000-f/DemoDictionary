package ir.s3000.demodictionary;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Created by S3000 on 10/30/2015.
 */
public class Decompress {
    public Word[] mWord;
    public Decompress (byte[] compressed) throws IOException {
        mWord=decompress(compressed);
    }

    public static Word[] decompress(byte[] compressed) throws IOException {
        final int BUFFER_SIZE = 32;
        ByteArrayInputStream is = new ByteArrayInputStream(compressed);
        GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
        StringBuilder string = new StringBuilder();
        byte[] data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(data)) != -1) {
            string.append(new String(data, 0, bytesRead));
        }
        gis.close();
        is.close();

        String s=string.toString();
        String[] s1=s.split("!@#");
        Word[] word = new Word[s1.length];
        for(int i=0;i<s1.length;i++)
        {
            String[] s2=s1[i].split("$%^");
            word[i].setWord(s2[0]);
            word[i].setDeff(s2[1]);

        }
        return word;
    }
}
