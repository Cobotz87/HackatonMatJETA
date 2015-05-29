package com.jeta.materialise.model;

import android.support.v4.util.Pair;

import com.jeta.materialise.model.Interface.IObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ArifHBtz on 5/30/2015.
 */
public class Attributes implements IObject{

    private Object mTag;

    private String mLabel;
    private String mGender;
    private ArrayList<Pair<String, String>> mReferencePair;
    private String mFrom;
    private String mTo;
    private String mAt;
    private String mPlace;
    private String mBehaviour;
    private String mAction;
    private String mAge;
    private ArrayList<String> mAttributes;

    public Attributes(){

    }

    @Override
    public Object getTag() {
        return mTag;
    }

    @Override
    public void setTag(Object object) {
        mTag = object;
    }

    public String getLabel(){
        return mLabel;
    }

    public void setLabel(String label){
        mLabel = label;
    }

    public String getGender(){
        return mGender;
    }

    public void setGender(String gender){
        mGender = gender;
    }

    public ArrayList<Pair<String,String>> getReferencePair(){
        return mReferencePair;
    }

    public void setReferencePair(ArrayList<Pair<String,String>> referencePair){
        mReferencePair = referencePair;
    }

    public String getFrom(){
        return mFrom;
    }

    public void setFrom(String from){
        mFrom = from;
    }

    public String getTo(){
        return mTo;
    }

    public void setTo(String to){
        mTo = to;
    }

    public String getAt(){
        return mAt;
    }

    public void setAt(String at){
        mAt = at;
    }

    public String getPlace(){
        return mPlace;
    }

    public void setPlace(String place){
        mPlace = place;
    }

    public String getBehaviour(){
        return mBehaviour;
    }

    public void setBehaviour(String behaviour){
        mBehaviour = behaviour;
    }

    public String getAction(){
        return mAction;
    }

    public void setAction(String action){
        mAction = action;
    }

    public ArrayList<String> getAttributes(){
        return mAttributes;
    }

    public void setmAttributes(ArrayList<String> attributes){
        mAttributes = attributes;
    }

    public String getAge(){
        return mAge;
    }

    public void setAge(String age){
        mAge = age;
    }
}
