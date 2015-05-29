package com.jeta.materialise.presenter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jeta.materialise.adapter.ConversationAdapter;
import com.jeta.materialise.matjeta.MainActivity;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class MainActivityPresenter {

    private MainActivity mActivity;
    private ConversationAdapter mConversationAdapter;

    public MainActivityPresenter(MainActivity activity){
        mActivity = activity;
        mConversationAdapter = new ConversationAdapter();
    }

    public void handleEdUserInput(EditText edUserInput){
     //TODO
    }

    public void handleBtnAsk(Button btnAsk){

        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void handleLvConversations(ListView lvConversations){
        lvConversations.setAdapter(mConversationAdapter);
    }

    public void notifyConversationDataSetChanged(){
        mConversationAdapter.notifyDataSetChanged();
    }

}
