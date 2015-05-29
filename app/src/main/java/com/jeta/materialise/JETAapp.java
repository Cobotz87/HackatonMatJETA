package com.jeta.materialise;

import android.app.Application;
import android.util.Log;

import com.jeta.materialise.languageprocessor.NaturalLanguageProcessor;
import com.jeta.materialise.manager.MDatabase;
import com.jeta.materialise.manager.MessageManager;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
import com.jeta.materialise.matjeta.MainActivity;

import opennlp.maxent.Main;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class JETAapp extends Application {

    private static MessageManager mMessageManager;
    private static NaturalLanguageProcessor mNaturalLanguageProcessor;
    private static MainActivity mMainActivity;
    private static AddInfoContextActivity mAddInfoContextActivity;
    private static MDatabase mDatabase;

    public JETAapp(){
        Log.d("JETA","Initializing application...");
        mMessageManager = MessageManager.getInstance();
        mNaturalLanguageProcessor = NaturalLanguageProcessor.getInstance();
        mDatabase = MDatabase.getInstance();
    }

    public static MessageManager getMessageManager(){
        return mMessageManager;
    }

    public static NaturalLanguageProcessor getNLProcessor(){
        return mNaturalLanguageProcessor;
    }

    public static void setMainActivity(MainActivity mainActivity){
        mMainActivity = mainActivity;
    }

    public static MainActivity getMainActivity(){
        return mMainActivity;
    }

    public static void setAddInfoContextActivity(AddInfoContextActivity infoContextActivity){
        mAddInfoContextActivity = infoContextActivity;
    }

    public static AddInfoContextActivity getmAddInfoContextActivity(){
        return mAddInfoContextActivity;
    }

    public static MDatabase getDatabase(){
        return mDatabase;
    }
}
