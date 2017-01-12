package org.artoolkit.ar.samples.ARSimpleInteraction;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import io.deepstream.DeepstreamClient;
import io.deepstream.Record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by detuur on 13/12/2016.
 */

public class DBHandlerNoSql {

    static String fileName = "data.json";
    static ArrayList<Question> QuestionList;

    DeepstreamClient dsclient = DeepstreamService.getInstance().getDeepstreamClient();

    public DBHandlerNoSql(Context context){

        Gson gson = new Gson();

        String datastring = getData(context);
        if (datastring.equals(""))
            datastring = "[]";
        Question[] questionarray = gson.fromJson(datastring, Question[].class);
        for(int i = 0; i< questionarray.length; i++) {
            QuestionList.add(questionarray[i]); // TODO: change to addQuestion
        }
    }

    public List<String> getQuestionList() {
        final List<String> L = new ArrayList<>();
        //do shit
        return L;
    }

    public void addQuestion(Question question) {
        //do shit
    }

    public void emitAnswer(Answer answer) {
        dsclient.record.getRecord("results").set(answer);
    }

    public void emitInactive() {
        Answer neg = Answer.getTestAnswer();
        String query = new Gson().toJson(neg);
        Log.d("PLSFIX", "about to getrecord");
        Record rec = dsclient.record.getRecord("results");
        Log.d("PLSFIX","got record, setting now");
        rec.set(neg);
        Log.d("PLSFIX","it twerked. how I don't know but it did");

        //dsclient.record.getRecord("results").set("active", false);

    }

    public static void saveData(Context context, String mJsonResponse) {
        try {
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context) {
        try {
            File f = new File(context.getFilesDir().getPath() + "/" + fileName);
            //check whether file exists - if not, create
            if(!f.exists()){
                f.createNewFile();
            }
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }
}
