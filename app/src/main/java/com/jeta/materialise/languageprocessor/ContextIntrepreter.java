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

    public enum EGender{
        MALE, FEMALE, NEUTRAL
    }
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
    }

    private void generateObject(Attributes input){
        MDatabase database = JETAapp.getDatabase();
        ArrayList<Attributes> all_objects = new ArrayList();
        boolean isAdd = true;
        all_objects = database.getObjects();
        if(all_objects.isEmpty())
            database.addObject(input);
        else{
            for(Attributes object : all_objects){
                if(object.getLabel().equals(input.getLabel().toString()))
                    isAdd = false;
                }

            if(isAdd)
                database.addObject(input);
            }
        }
}
