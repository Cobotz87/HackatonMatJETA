package com.jeta.materialise.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.adapter.ConversationAdapter;
import com.jeta.materialise.languageprocessor.ContextDigester;
import com.jeta.materialise.languageprocessor.ContextIntrepreter;
import com.jeta.materialise.languageprocessor.QueryData;
import com.jeta.materialise.matjeta.MainActivity;
import com.jeta.materialise.model.Message;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class MainActivityPresenter {

    private MainActivity mActivity;
    private ConversationAdapter mConversationAdapter;

    private String mQuestion;
    private ContextDigester mDigester;

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

                if(mQuestion.isEmpty())
                    return;

                Message userMsg = new Message("You",mQuestion);
                JETAapp.getMessageManager().addMessage(userMsg);

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        String[] tokens = mDigester.getTokens(mQuestion);
//                        String[] namesFound = mDigester.getNames(tokens);

                        QueryData query = new QueryData();
                        String answer = query.queryAnswer(mQuestion);
                        final Message curr_message = new Message("Mia", answer);

                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                JETAapp.getMessageManager().addMessage(curr_message);
                                JETAapp.getMainActivity().setBusy(false);
                                JETAapp.getMainActivity().handleBusy();
                                JETAapp.getMainActivity().clearInputBox();
                                notifyConversationDataSetChanged();
                                mActivity.focusListBoxItem();
                            }
                        });
                    }
                });
                t.start();
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
