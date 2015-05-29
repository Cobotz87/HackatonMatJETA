package com.jeta.materialise.model;

import com.jeta.materialise.model.Interface.IObject;

/**
 * Created by ArifHBtz on 5/30/2015.
 */
public class Events implements IObject {

    Object mTag;
    String mEventType;

    public Events(){

    }

    public String getEventType(){
        return mEventType;
    }

    @Override
    public Object getTag() {
        return mTag;
    }

    @Override
    public void setTag(Object object) {
        mTag = object;
    }
}
