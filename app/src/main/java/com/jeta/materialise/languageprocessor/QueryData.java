package com.jeta.materialise.languageprocessor;

import com.jeta.materialise.JETAapp;
import com.jeta.materialise.model.Attributes;

import java.util.ArrayList;

/**
 * Created by hoong_000 on 5/30/2015.
 */
public class QueryData {
    public enum EQType{
        WHO, WHY, WHERE, WHAT, WHEN, HOW_MANY
    }

    EQType mQuestionType;

    public String queryAnswer(String question){
        String answer = new String();
        if(isQuestion(question)){
            ArrayList<Attributes> database = JETAapp.getDatabase().getObjects();
            if(mQuestionType == EQType.HOW_MANY)
                answer = ("There are " + Integer.toString(database.size()) + ".");
            else if (mQuestionType == EQType.WHO){
                ArrayList<String> existObj = new ArrayList<>();
                ArrayList<String> genderObj = new ArrayList<>();

                for(Attributes obj : database){
                    String label = obj.getLabel();
                    if(question.toLowerCase().contains(label.toLowerCase())) {
                        existObj.add(label);
                        if(obj.getGender() == Attributes.EGender.FEMALE)
                            genderObj.add("female");
                        else if (obj.getGender() == Attributes.EGender.MALE)
                            genderObj.add("male");
                        else
                            genderObj.add("neutral");
                    }
                }

                String positive = "";
                for(int i =0; i<existObj.size(); i++){
                    if(existObj.size() > 1 && i == existObj.size()-1)
                        positive += ("and ");

                    positive += existObj.get(i);
                    positive += ("(");
                    positive += genderObj.get(i);
                    positive += (")");

                    if(i != existObj.size()-1)
                        positive += (", ");
                    else
                        positive += (".");
                }

                if(!existObj.isEmpty())
                    answer = "I know " + positive;
                else
                    answer = "I do not know.";
            }
            else
                answer = "I'm afraid I do not have answer for that.";
        }
        else
        answer = "I'm afraid that is not a question.";

        return answer;
    }

    private boolean isQuestion(String question){
        if(question.toLowerCase().contains("who"))
            mQuestionType = EQType.WHO;
        else if(question.toLowerCase().contains("why"))
            mQuestionType = EQType.WHY;
        else if(question.toLowerCase().contains("what"))
            mQuestionType = EQType.WHAT;
        else if(question.toLowerCase().contains("when"))
            mQuestionType = EQType.WHEN;
        else if(question.toLowerCase().contains("where"))
            mQuestionType = EQType.WHERE;
        else if(question.toLowerCase().contains("how many"))
            mQuestionType = EQType.HOW_MANY;
        else return false;

        return true;
    }
}
