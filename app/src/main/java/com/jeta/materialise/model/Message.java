package com.jeta.materialise.model;

import java.util.Date;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class Message {

    private String mOwnerName;
    private String mMessage;
    private Date mTimeStamp;

    public Message(){
        mTimeStamp = new Date();
    }

    public Message(String ownerName, String message){
        mOwnerName = ownerName;
        mMessage = message;
        mTimeStamp = new Date();
    }

    public void setOwnerName(String ownerName){
        mOwnerName = ownerName;
    }

    public String getOwnerName(){
        return mOwnerName;
    }

    public void setMessage(String message){
        mMessage = message;
    }

    public String getMessage(){
        return mMessage;
    }

    public void setTimeStamp(Date timeStamp){
        mTimeStamp = timeStamp;
    }

    public Date getTimeStamp(){
        return mTimeStamp;
    }
}
