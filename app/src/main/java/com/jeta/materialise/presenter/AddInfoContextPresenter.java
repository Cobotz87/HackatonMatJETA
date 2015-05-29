package com.jeta.materialise.presenter;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.matjeta.AddInfoContextActivity;
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

                JETAapp.getMainActivity().setBusy(true);
                JETAapp.getMainActivity().handleBusy();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        InputStream sentences_detector_is = mActivity.getResources().openRawResource(R.raw.ensent);
                        try {
                            Log.d("JETA", "Doing SENTENCING...");
                            String[] sentences = JETAapp.getNLProcessor().SentenceDetect(mInfoContext, sentences_detector_is);
                            Log.d("JETA", "Done!");

                            commitMessages(sentences);
                            updateMainActivity();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("JETA", "Failed to SENTENCING information context.");
                            updateMainActivity();
                        }
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

                        try {
                            Log.d("JETA", "Processing TOKENIZATION...");
                            String[] tokenized_strings = JETAapp.getNLProcessor().Tokenize(mInfoContext, tokenization_is);
                            Log.d("JETA", "Done!");

                            commitMessages(tokenized_strings);
                            updateMainActivity();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("JETA", "Failed to TOKENIZATION information context.");
                            updateMainActivity();
                        }

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
                        InputStream sentences_detector_is = mActivity.getResources().openRawResource(R.raw.ensent);
                        try {
                            Log.d("JETA", "Doing TAGGERING...");
                            String[] sentences = JETAapp.getNLProcessor().SentenceDetect(mInfoContext, sentences_detector_is);
                            Log.d("JETA", "Done!");

                            commitMessages(sentences);

                            for(String s : sentences){
                                s = s.substring(0, s.length() - 1);
                                InputStream tagger_detector_is = mActivity.getResources().openRawResource(R.raw.enposmaxent);
                                String tagged_sentences = new String();

                                try {
                                    Log.d("JETA", "Processing TAGGERS...");
                                    tagged_sentences = JETAapp.getNLProcessor().tagPartOfSpeech(s, tagger_detector_is);
                                    Log.d("JETA", "Done!");

                                    Message curr_message = new Message("JETA", tagged_sentences);
                                    JETAapp.getMessageManager().addMessage(curr_message);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Log.d("JETA", "Failed to TAGGER information context.");
                                }
                            }

                            updateMainActivity();

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("JETA", "Failed to SENTENCING information context.");
                            updateMainActivity();
                        }
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

                        String[] tokens = new String[0];
                        InputStream tokenizer_is = mActivity.getResources().openRawResource(R.raw.entoken);
                        try {
                            Log.d("JETA", "Doing TOKENIZING in NAMEDENTITY...");
                            tokens = JETAapp.getNLProcessor().Tokenize(mInfoContext, tokenizer_is);
                            Log.d("JETA", "Done!");

                            tokenizer_is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("JETA", "Failed to TOKENIZE during NAMEDENTITY.");
                        }

                        InputStream name_detector_is = mActivity.getResources().openRawResource(R.raw.ennerperson);
                        InputStream location_detector_is = mActivity.getResources().openRawResource(R.raw.ennerlocation);
                        try {
                            Log.d("JETA", "Doing NAMEDENTITY...");
                            String[] names = JETAapp.getNLProcessor().findName(tokens, name_detector_is);
                            String[] locations = JETAapp.getNLProcessor().findName(tokens, location_detector_is);
                            Log.d("JETA", "Done!");

                            for (String n : names) {
                                Message curr_message = new Message("JETA", n);
                                JETAapp.getMessageManager().addMessage(curr_message);
                            }

                            for (String n : locations) {
                                Message curr_message = new Message("JETA", n);
                                JETAapp.getMessageManager().addMessage(curr_message);
                            }

                            name_detector_is.close();
                            location_detector_is.close();

                            updateMainActivity();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("JETA", "Failed doing NAMEDENTITY...");
                            updateMainActivity();
                        }
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

    private void commitMessages(String[] strings){
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
