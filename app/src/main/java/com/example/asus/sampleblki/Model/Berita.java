package com.example.asus.sampleblki.Model;

/**
 * Created by ASUS on 09/05/2017.
 */

public class Berita {

    private int mImage;
    private String mNama;
    private String mTanggal;

    public Berita(int mImage, String mNama, String mTanggal){
        this.mImage = mImage;
        this.mNama = mNama;
        this.mTanggal = mTanggal;
    }

    public int getmImage(){
        return mImage;
    }

    public String getmNama(){
        return mNama;
    }

    public String getmTanggal(){
        return mTanggal;
    }
}
