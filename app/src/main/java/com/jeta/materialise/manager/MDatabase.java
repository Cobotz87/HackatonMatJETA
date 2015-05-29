package com.jeta.materialise.manager;

import com.jeta.materialise.model.Interface.IObject;

import java.util.ArrayList;

/**
 * Created by ArifHBtz on 5/30/2015.
 */
public class MDatabase {

    private static MDatabase mInstance;

    private static ArrayList<String> mContextStrings;
    private static ArrayList<IObject> mDatabaseObjects;

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

    public static ArrayList<IObject> getObjects(){
        return mDatabaseObjects;
    }

    public static void addObject(IObject object){
        mDatabaseObjects.add(object);
    }

    public static void addObjects(ArrayList<IObject> objects){
        mDatabaseObjects.addAll(objects);
    }

}
