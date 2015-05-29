package com.jeta.materialise.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.adapter.ConversationAdapter;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
import com.jeta.materialise.matjeta.MainActivity;
import com.jeta.materialise.matjeta.R;
import com.jeta.materialise.model.Message;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class AddInfoContextPresenter {

    private AddInfoContextActivity mActivity;
    private String mInfoContext;

    public AddInfoContextPresenter(AddInfoContextActivity activity){
        mActivity = activity;
    }

    public void handleEdInfoContext(final EditText edInfoContext){
     //TODO
        edInfoContext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mInfoContext = edInfoContext.getText().toString();
            }
        });
    }

    public void handleBtnSentencing(Button btnSentencing) {

        btnSentencing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInfoContext != null && !mInfoContext.isEmpty())
                    createSentencingAlertBox();
            }
        });
    }

    private void createSentencingAlertBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Do Sentencing?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                InputStream sentences_detector_is = mActivity.getResources().openRawResource(R.raw.ensent);

                try {
                    Log.d("JETA", "Doing SENTENCING...");
                    String[] sentences = JETAapp.getNLProcessor().SentenceDetect(mInfoContext, sentences_detector_is);
                    Log.d("JETA", "Done!");

                    commitMessages(sentences);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("JETA", "Failed to SENTENCING information context.");
                }

                dialog.dismiss();
                mActivity.finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void handleBtnTokenize(Button btnTokenize){
        btnTokenize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInfoContext != null && !mInfoContext.isEmpty())
                    createTokenizeAlertDialog();
            }
        });
    }

    private void createTokenizeAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Do Tokenization?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                InputStream tokenization_is = mActivity.getResources().openRawResource(R.raw.entoken);

                try {
                    Log.d("JETA", "Processing TOKENIZATION...");
                    String[] tokenized_strings = JETAapp.getNLProcessor().SentenceDetect(mInfoContext, tokenization_is);
                    Log.d("JETA", "Done!");

                    commitMessages(tokenized_strings);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("JETA", "Failed to SENTENCING information context.");
                }

            dialog.dismiss();
            mActivity.finish();
        }
    });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void commitMessages(String[] strings)
    {
        JETAapp.getMessageManager().clearMessages();

        for(String curr_string : strings){
            Message curr_message = new Message("JETA", curr_string);
            JETAapp.getMessageManager().addMessage(curr_message);
        }
    }

}
