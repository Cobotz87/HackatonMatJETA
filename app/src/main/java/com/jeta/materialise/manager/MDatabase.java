package com.jeta.materialise.manager;

import com.jeta.materialise.model.Attributes;
import com.jeta.materialise.model.Interface.IObject;

import java.util.ArrayList;

/**
 * Created by ArifHBtz on 5/30/2015.
 */
public class MDatabase {

    private static MDatabase mInstance;

    private static ArrayList<String> mContextStrings;
    private static ArrayList<Attributes> mDatabaseObjects;

    private static Attributes mActiveObject;

    public static MDatabase getInstance(){
        if(mInstance == null)
            mInstance = new MDatabase();

        return mInstance;
    }

    public MDatabase(){
        mDatabaseObjects = new ArrayList<>();
    }

    public static ArrayList<String> getContextStrings(){
        return mContextStrings;
    }

    public static void AddContextString(String string){
        mContextStrings.add(string);
    }

    public static void AddContextStrings(ArrayList<String> strings){
        mContextStrings.addAll(strings);
    }

    public static ArrayList<Attributes> getObjects(){
        return mDatabaseObjects;
    }

    public static void addObject(Attributes object){
        mDatabaseObjects.add(object);
    }

    public static void addObjects(ArrayList<Attributes> objects) {
        mDatabaseObjects.addAll(objects);
        }

    public static void clearObjects(){mDatabaseObjects.clear();}

    public static Attributes getObjectByName(String name){
        Attributes return_object = null;
        for(Attributes object : mDatabaseObjects) {
            if (object.getLabel().equalsIgnoreCase(name))
                return_object = object;
        }
        return return_object;
    }

    public static void setActiveObject(String name){
        for(Attributes object : mDatabaseObjects) {
            if (object.getLabel() == name)
                mActiveObject = object;
        }
    }

    public static void setActiveObject(Attributes object){
                mActiveObject = object;
    }

    public static Attributes getActiveObject(){return mActiveObject;}
}
