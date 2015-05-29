package com.jeta.materialise.manager;

import com.jeta.materialise.model.Message;

import java.util.ArrayList;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class MessageManager {
    private static MessageManager mInstance;
    private ArrayList<Message> mMessages;

    public MessageManager(){
        mMessages = new ArrayList();
    }

    public ArrayList<Message> getMessages(){
        return mMessages;
    }

    public void addMessage(Message message){
        mMessages.add(message);
    }

    public void addMessages(ArrayList<Message> messages){
        mMessages.addAll(messages);
    }

    public static MessageManager getInstance()
    {
        if(mInstance == null)
            mInstance = new MessageManager();

        return mInstance;
    }
}
