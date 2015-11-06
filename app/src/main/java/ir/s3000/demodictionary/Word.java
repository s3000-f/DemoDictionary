package ir.s3000.demodictionary;

import android.util.Log;

/**
 * Created by S3000 on 10/30/2015.
 */
public class Word {
    private long id;
    private String word;
    private String deff;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        Log.e("kk","fucl");
        this.word = word;
    }
    public String getDeff() {
        return deff;
    }

    public void setDeff(String deff) {
        this.deff = deff;
    }

}
