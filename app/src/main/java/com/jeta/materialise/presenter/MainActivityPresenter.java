package com.jeta.materialise.presenter;

import android.text.Editable;
import android.text.TextWatcher;
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

    private String mQuestion;

    public MainActivityPresenter(MainActivity activity){
        mActivity = activity;
        mConversationAdapter = new ConversationAdapter();
        mQuestion = "";
    }

    public void handleEdUserInput(final EditText edUserInput){
     edUserInput.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
             mQuestion = edUserInput.getText().toString();
         }
     });
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
