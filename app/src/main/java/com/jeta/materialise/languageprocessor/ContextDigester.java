package com.jeta.materialise.languageprocessor;

import android.util.Log;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
import com.jeta.materialise.matjeta.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import opennlp.tools.util.InvalidFormatException;

/**
 * Created by hoong_000 on 5/30/2015.
 */
public class ContextDigester {
    public static String[] getSentences(String string){
        String[] outputSentences = new String[0];
        AddInfoContextActivity activity = JETAapp.getAddInfoContextActivity();
        if(activity == null)
            return outputSentences;

        InputStream sentences_detector_is = activity.getResources().openRawResource(R.raw.ensent);
        try {
            Log.d("JETA", "Doing SENTENCING...");
            outputSentences = JETAapp.getNLProcessor().SentenceDetect(string, sentences_detector_is);
            Log.d("JETA", "Done!");

            sentences_detector_is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("JETA", "Failed to SENTENCING information context.");
        }
        return outputSentences;
    }

    public static String[] getTokens(String string){
        String[] outputTokens = new String[0];
        AddInfoContextActivity activity = JETAapp.getAddInfoContextActivity();
        if(activity == null)
            return outputTokens;

        InputStream token_is = activity.getResources().openRawResource(R.raw.entoken);

        try {
            Log.d("JETA", "Doing TOKENIZATION...");
            outputTokens = JETAapp.getNLProcessor().Tokenize(string, token_is);
            Log.d("JETA", "Done!");
            token_is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("JETA", "Failed to TOKENIZATION information context.");
        }
        return outputTokens;
    }

    public static String getPOSTags(String string){
        String outputTags = new String();
        AddInfoContextActivity activity = JETAapp.getAddInfoContextActivity();
        if(activity == null)
            return outputTags;

        InputStream tagger_is = activity.getResources().openRawResource(R.raw.enposmaxent);

        try {
            Log.d("JETA", "Doing TOKENIZATION...");
            outputTags = JETAapp.getNLProcessor().tagPartOfSpeech(string, tagger_is);
            Log.d("JETA", "Done!");
            tagger_is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("JETA", "Failed to TOKENIZATION information context.");
        }
        return outputTags;
    }

    public static String[] getNames(String[] tokens){
        String[] outputNames = new String[0];
        AddInfoContextActivity activity = JETAapp.getAddInfoContextActivity();
        if(activity == null)
            return outputNames;

        InputStream name_is = activity.getResources().openRawResource(R.raw.ennerperson);
        try {
            Log.d("JETA", "Doing NAME_SEARCHING...");
            outputNames = JETAapp.getNLProcessor().findName(tokens, name_is);

            Log.d("JETA", "Done!");
            name_is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("JETA", "Failed to NAME_SEARCHING information context.");
        }
        return outputNames;
    }

    public static String[] getLocations(String[] tokens){
        String[] outputNames = new String[0];
        AddInfoContextActivity activity = JETAapp.getAddInfoContextActivity();
        if(activity == null)
            return outputNames;

        InputStream location_is = activity.getResources().openRawResource(R.raw.ennerlocation);
        try {
            Log.d("JETA", "Doing LOCATION_SEARCHING...");
            outputNames = JETAapp.getNLProcessor().findName(tokens, location_is);

            Log.d("JETA", "Done!");
            location_is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("JETA", "Failed to LOCATION_SEARCHING information context.");
        }
        return outputNames;
    }

    public static String[] getTokenizedPOSTags(String paragraph){
        ArrayList<String> tagsTokens = new ArrayList<String>();
        String[] sentences = getSentences(paragraph);
        for(String s : sentences){
            s = s.substring(0, s.length() - 1);
            String posTags = getPOSTags(s);
            String[] tokens = getTokens(posTags);
            for(String t : tokens)
                tagsTokens.add(t);
        }

        String[] tempString = new String[tagsTokens.size()];
        tempString = tagsTokens.toArray(tempString);
        return tempString;
    }
}
