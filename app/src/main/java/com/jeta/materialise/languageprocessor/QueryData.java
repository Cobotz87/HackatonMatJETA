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

    public String queryAnswer(String[] tags, String[] namesFound){
        String answer = new String();
        if(isQuestion(tags)){
            ArrayList<Attributes> database = JETAapp.getDatabase().getObjects();
            if(mQuestionType == EQType.HOW_MANY)
                answer = ("There are " + Integer.toString(database.size()) + ".");
            else if (mQuestionType == EQType.WHO){
                ArrayList<String> existObj = new ArrayList<>();
                ArrayList<String> nonExist = new ArrayList<>();
                for(String s : namesFound){
                    for(Attributes obj : database){
                        String label = obj.getLabel();
                        if(label.equals(s))
                            existObj.add(s);
                        else
                            nonExist.add(s);
                    }
                }
                String positive = null;
                for(String p : existObj)
                    positive += p + (", ");
                answer = "I know " + positive;

                String negative = null;
                for(String n : nonExist)
                    negative += n + (", ");
                answer += ". I do not know " + negative;
            }
            else
                answer = "I'm afraid I do not have answer for that.";
        }
        else
        answer = "I'm afraid that is not a question.";

        return answer;
    }

    private boolean isQuestion(String[] tags){
        for(String s : tags){
            String[] split = s.split("_");
            if(split[1].equals("WP")){
                if(split[0].equalsIgnoreCase("who"))
                    mQuestionType = EQType.WHO;
                else if(split[0].equalsIgnoreCase("why"))
                    mQuestionType = EQType.WHY;
                else if(split[0].equalsIgnoreCase("what"))
                    mQuestionType = EQType.WHAT;
                else if(split[0].equalsIgnoreCase("when"))
                    mQuestionType = EQType.WHEN;
                else if(split[0].equalsIgnoreCase("WHERE"))
                    mQuestionType = EQType.WHERE;
                else if(split[0].contains("how many"))
                    mQuestionType = EQType.HOW_MANY;
            }
        }
        return true;
    }
}
