package com.example.messengerlite.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.widget.Toast;

import com.example.messengerlite.R;

import java.io.ByteArrayOutputStream;

public class Launcher extends AppCompatActivity {

    public static final String SHARED_PREF="sharedPrefs";
    public static final String Full_name = "full_name";
    public static final String prof_img = "Profile_image";
    public static final String ACCOUNT="account";

    public static String ID;

    static public String SP_FULL_NAME ="";
    static public String SP_ACCOUNT ="";
    static public String SP_PROFILE_IMAGE ="";

    int FirstTime;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancher);
        sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);

        FirstTime= sharedPreferences.getInt(ID,0);

        if(FirstTime != 0)
        {

            SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
            SP_FULL_NAME = sharedPreferences.getString(Full_name ,"") ;
            SP_ACCOUNT = sharedPreferences.getString(ACCOUNT ,"") ;
            SP_PROFILE_IMAGE = sharedPreferences.getString(prof_img,"");


            Toast.makeText(Launcher.this, "Welcome " + SP_FULL_NAME , Toast.LENGTH_LONG).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent homeIntent = new Intent(Launcher.this, Home.class);
                    startActivity(homeIntent);
                    finish();

                }
            },2500);
        }

        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent login = new Intent(Launcher.this,Login.class);
                    startActivity(login);
                    finish();
                }
            }, 2500);
        }

    }
    /**
     * convert Bitmap to String
     **/
    static public String encodeImage(Bitmap imagee) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagee.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }


    /**
     * to convert string to Bitmap
     **/
    static public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
