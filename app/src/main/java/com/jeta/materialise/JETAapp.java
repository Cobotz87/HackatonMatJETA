package com.jeta.materialise;

import android.app.Application;
import android.util.Log;

import com.jeta.materialise.languageprocessor.NaturalLanguageProcessor;
import com.jeta.materialise.manager.MessageManager;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class JETAapp extends Application {

    private static MessageManager mMessageManager;
    private static NaturalLanguageProcessor mNaturalLanguageProcessor;

    public JETAapp(){
        Log.d("JETA","Initializing application...");
        mMessageManager = MessageManager.getInstance();
        mNaturalLanguageProcessor = NaturalLanguageProcessor.getInstance();
    }

    public static MessageManager getMessageManager(){
        return mMessageManager;
    }

    public static NaturalLanguageProcessor getNLProcessor(){
        return mNaturalLanguageProcessor;
    }

}
