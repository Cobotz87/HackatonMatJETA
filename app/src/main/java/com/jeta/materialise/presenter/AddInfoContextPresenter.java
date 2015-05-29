package com.jeta.materialise.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.languageprocessor.ContextDigester;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
import com.jeta.materialise.matjeta.R;
import com.jeta.materialise.model.Message;

import java.io.InputStream;

/**
 * Created by ArifHBtz on 5/29/2015.
 */
public class AddInfoContextPresenter {

    private AddInfoContextActivity mActivity;
    private String mInfoContext;
    private ContextDigester mDigester;

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

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] sentences = mDigester.getSentences(mInfoContext);
                        commitMessages(sentences, true);

                        updateMainActivity();
                    }
                });

                t.start();

                dialog.dismiss();
                mActivity.finish();

            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
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

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InputStream tokenization_is = mActivity.getResources().openRawResource(R.raw.entoken);
                        String[] tokenized_strings = mDigester.getTokens(mInfoContext);
                        commitMessages(tokenized_strings, true);

                        updateMainActivity();
                    }
                });

                t.start();

            dialog.dismiss();
            mActivity.finish();
        }
    });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void handleBtnTagger(Button btnTagger){
        btnTagger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInfoContext != null && !mInfoContext.isEmpty())
                    createTaggerAlertBox();
            }
        });
    }

    private void createTaggerAlertBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Do Taggering?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] sentences = mDigester.getSentences(mInfoContext);
                        commitMessages(sentences, true);

                        for(String s : sentences){
                            s = s.substring(0, s.length() - 1);
                            String tagged_sentences = mDigester.getPOSTags(s);
                            Message curr_message = new Message("JETA", tagged_sentences);
                            JETAapp.getMessageManager().addMessage(curr_message);
                        }
                        updateMainActivity();
                    }
                });

                t.start();

                dialog.dismiss();
                mActivity.finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void handleBtnNamedEntity(Button btnNamedEntity){
        btnNamedEntity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInfoContext != null && !mInfoContext.isEmpty())
                    createNamedEntityAlertDialog();
            }
        });
    }

    private void createNamedEntityAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("Do NAMED ENTITY?");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                JETAapp.getMessageManager().clearMessages();

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String[] tokens = mDigester.getTokens(mInfoContext);
                        String[] namesFound = mDigester.getNames(tokens);
                        commitMessages(namesFound, true);

                        String[] locationsFound = mDigester.getLocations(tokens);
                        commitMessages(locationsFound, false);
                        updateMainActivity();
                    }
                });

                t.start();


                dialog.dismiss();
                mActivity.finish();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void handleBtnUseContext(Button btnUseContext){
        btnUseContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void handleBtnConvertToObj(Button btnConvertObj){
        btnConvertObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void commitMessages(String[] strings, boolean clear){
        if(clear)
            JETAapp.getMessageManager().clearMessages();

        for(String curr_string : strings){
            Message curr_message = new Message("JETA", curr_string);
            JETAapp.getMessageManager().addMessage(curr_message);
        }
    }

    private void updateMainActivity(){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JETAapp.getMainActivity().setBusy(false);
                JETAapp.getMainActivity().handleBusy();
                JETAapp.getMainActivity().getPresenter().notifyConversationDataSetChanged();
            }
        });
    }

}
