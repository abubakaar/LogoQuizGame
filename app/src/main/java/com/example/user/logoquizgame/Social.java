package com.example.user.logoquizgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.logoquizgame.Adapter.GridViewAnswerAdapter;
import com.example.user.logoquizgame.Adapter.GridViewSuggestShopping;
import com.example.user.logoquizgame.Adapter.GridViewSuggestSocial;
import com.example.user.logoquizgame.Common.Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Social extends AppCompatActivity {


    public List<String> suggestSource=new ArrayList<>();
    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestSocial suggestAdapter;
    public GridView gridViewAnswer,gridViewSuggest;
    public ImageView imgViewQuestion;
    Button ok;
    int[] image_list={R.drawable.soundcloud,R.drawable.facebook,R.drawable.watsapp,R.drawable.instagram,R.drawable.twitter};

    public static char[] answer;
    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);


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
                for(int i = 0; i< Common.user_submit_answer.length; i++)
                    result+=String.valueOf(Common.user_submit_answer[i]);
                if(result.equals(correct_answer)){
                    Toast.makeText(getApplicationContext(),"finish",Toast.LENGTH_LONG).show();

                    Common.count=0;
                    Common.user_submit_answer=new char[correct_answer.length()];
                    GridViewAnswerAdapter answerAdapter=new GridViewAnswerAdapter(setupNulllist(),getApplicationContext());
                    gridViewAnswer.setAdapter(answerAdapter);
                    answerAdapter.notifyDataSetChanged();
                    GridViewSuggestSocial suggestAdapter=new GridViewSuggestSocial(suggestSource,getApplicationContext(),Social.this);
                    gridViewSuggest.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();

                    setupList();

                }
                else{
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
        suggestAdapter=new GridViewSuggestSocial(suggestSource,this,Social.this);

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
