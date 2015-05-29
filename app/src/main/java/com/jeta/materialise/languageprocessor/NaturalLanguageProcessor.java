package com.jeta.materialise.languageprocessor;

import android.content.res.Resources;
import android.provider.MediaStore;
import android.util.Log;

import com.jeta.materialise.matjeta.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

/**
 * Created by hoong_000 on 5/29/2015.
 */
public class NaturalLanguageProcessor {

    private static NaturalLanguageProcessor mInstance;
    public static NaturalLanguageProcessor getInstance(){
        if(mInstance == null)
            mInstance = new NaturalLanguageProcessor();
        return mInstance;
    }

    public static String[] SentenceDetect(String string, InputStream stream) throws InvalidFormatException,
            IOException {

        // always start with a model, a model is learned from training data
        SentenceModel model = new SentenceModel(stream);
        SentenceDetectorME sdetector = new SentenceDetectorME(model);

        String sentences[] = sdetector.sentDetect(string);
        return sentences;
    }

    public static String[] Tokenize(String i_string, InputStream i_stream) throws InvalidFormatException, IOException {
        TokenizerModel model = new TokenizerModel(i_stream);
        Tokenizer tokenizer = new TokenizerME(model);
        String tokens[] = tokenizer.tokenize(i_string);
        return tokens;
    }

    public static String[] findName(String[] i_token, InputStream i_stream) throws IOException {
        TokenNameFinderModel model = new TokenNameFinderModel(i_stream);
        i_stream.close();

        NameFinderME nameFinder = new NameFinderME(model);

        Span nameSpans[] = nameFinder.find(i_token);
        String[] outputNames = Span.spansToStrings(nameSpans, i_token);
        return outputNames;
    }

}

