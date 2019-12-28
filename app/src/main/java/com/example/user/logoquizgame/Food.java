package com.example.user.logoquizgame;

import android.graphics.Color;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.logoquizgame.Adapter.GridViewAnswerAdapter;
import com.example.user.logoquizgame.Adapter.GridViewSuggestAdapter;
import com.example.user.logoquizgame.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Food extends AppCompatActivity {




    public List<String> suggestSource=new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;
    public GridView gridViewAnswer,gridViewSuggest;
    public ImageView imgViewQuestion;
    Button ok;

    int[] image_list={R.drawable.pizzahut,R.drawable.mcdonalds,R.drawable.redbull,R.drawable.kfc,R.drawable.pringles,R.drawable.dominos};

    public static char[] answer;
    String correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        initView();
       
    }
    private void initView(){
        gridViewAnswer=(GridView) findViewById(R.id.gridViewAnswer);
        gridViewSuggest=(GridView)findViewById(R.id.gridViewSuggest);
        imgViewQuestion=(ImageView) findViewById(R.id.imageView);

        setupList();
        ok=(Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result="";
                for(int i=0;i< Common.user_submit_answer.length;i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                    if(result.equals(correct_answer)){
                        Toast.makeText(getApplicationContext(),"finish",Toast.LENGTH_LONG).show();
                        ok.setBackgroundColor(0);

                        Common.count=0;
                        Common.user_submit_answer=new char[correct_answer.length()];
                        GridViewAnswerAdapter answerAdapter=new GridViewAnswerAdapter(setupNulllist(),getApplicationContext());
                        gridViewAnswer.setAdapter(answerAdapter);
                        answerAdapter.notifyDataSetChanged();
                        GridViewSuggestAdapter suggestAdapter=new GridViewSuggestAdapter(suggestSource,getApplicationContext(),Food.this);
                        gridViewSuggest.setAdapter(suggestAdapter);
                        suggestAdapter.notifyDataSetChanged();

                        setupList();

                    }
                    else{
                        ok.setBackgroundColor(Color.RED);
                        Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_LONG).show();
                    }


            }
        });
    }

    private void setupList() {
        Random random=new Random();
        int imageselected=image_list[random.nextInt(image_list.length)];
        imgViewQuestion.setImageResource(imageselected);
        correct_answer=getResources().getResourceName(imageselected);
        correct_answer=correct_answer.substring(correct_answer.lastIndexOf("/")+1);
        answer=correct_answer.toCharArray();
        Common.user_submit_answer=new char[answer.length];
        suggestSource.clear();
        for(char item:answer){
            suggestSource.add(String.valueOf(item));
        }
        for(int i=answer.length;i<answer.length*2;i++)
            suggestSource.add(Common.alphabet_character[random.nextInt(Common.alphabet_character.length)]);


        Collections.shuffle(suggestSource);

        answerAdapter= new GridViewAnswerAdapter(setupNulllist(),this);
        suggestAdapter=new GridViewSuggestAdapter(suggestSource,this,Food.this);

        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();

        gridViewSuggest.setAdapter(suggestAdapter);
        gridViewAnswer.setAdapter(answerAdapter);

    }

    private char[] setupNulllist() {

        char result[] =new char[answer.length];
        for(int i=0;i<answer.length;i++)
            result[i]=' ';
            return result;

    }
}
