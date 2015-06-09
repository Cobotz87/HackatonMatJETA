package com.jeta.materialise.languageprocessor;

import android.support.v4.util.Pair;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.manager.MDatabase;
import com.jeta.materialise.model.Attributes;
import com.jeta.materialise.model.Interface.IObject;

import java.util.ArrayList;

/**
 * Created by hoong_000 on 5/30/2015.
 */
public class ContextIntrepreter {

    private String[]    mPOSTags;
    private String[]    mNamesFound;

    public void setPOSTags(String[] tags){
        mPOSTags = tags;
    }

    public void setNamesFound(String[] names){
        mNamesFound = names;
    }

    public void Intrepret(){
        for(String name : mNamesFound){
            Attributes newObject = new Attributes();
            newObject.setLabel(name);
            generateObject(newObject);
        }

        for(String tag : mPOSTags){
            if(tag.contains("_")){
                String[] tags_split = tag.split("_");
                setActiveObject(tags_split);
                processGender(tags_split);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    private void generateObject(Attributes input){
        MDatabase database = JETAapp.getDatabase();
        ArrayList<Attributes> all_objects = database.getObjects();

        boolean isAdd = true;
        if(all_objects.isEmpty())
            database.addObject(input);
        else{
            for(Attributes object : all_objects){
                if(object.getLabel().equals(input.getLabel().toString()))
                    isAdd = false;
                }

            if(isAdd) {
                database.addObject(input);
                database.setActiveObject(input);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    private void setActiveObject(String name){
        MDatabase database = JETAapp.getDatabase();
        database.setActiveObject(name);
    }

    /////////////////////////////////////////////////////////////////////////////

    private void setActiveObject(String[] splitted){
        String words = splitted[0];
        String tagger = splitted[1];

        MDatabase database = JETAapp.getDatabase();

        if(tagger.equals("NNP")){
            Attributes found_object = database.getObjectByName(words);
            if(found_object != null){
                database.setActiveObject(found_object);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////

    private void processGender(String[] splitted){
        String word = splitted[0];
        String tagger = splitted[1];
        MDatabase database = JETAapp.getDatabase();

        ArrayList<String> female_nouns = getFemaleNouns();
        ArrayList<String> male_nouns = getMaleNouns();

        if(tagger.equals("NN")){
            Attributes active_object = database.getActiveObject();

            if(female_nouns.contains(word))
                active_object.setGender(Attributes.EGender.FEMALE);
            else if (male_nouns.contains(word))
                active_object.setGender(Attributes.EGender.MALE);
            }
        }

    /////////////////////////////////////////////////////////////////////////////

    private ArrayList<String> getFemaleNouns(){
        ArrayList<String> female_nouns = new ArrayList<>();
        female_nouns.add("girl");
        female_nouns.add("woman");
        female_nouns.add("gal");
        female_nouns.add("lady");
        female_nouns.add("aunt");
        female_nouns.add("wife");
        female_nouns.add("princess");

        return female_nouns;
    }
    /////////////////////////////////////////////////////////////////////////////

    private ArrayList<String> getMaleNouns(){
        ArrayList<String> male_nouns = new ArrayList<>();
        male_nouns.add("boy");
        male_nouns.add("man");
        male_nouns.add("guy");
        male_nouns.add("gentlemen");
        male_nouns.add("uncle");
        male_nouns.add("husband");
        male_nouns.add("prince");

        return male_nouns;
    }
}
