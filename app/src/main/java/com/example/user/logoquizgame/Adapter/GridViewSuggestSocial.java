package com.example.user.logoquizgame.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.user.logoquizgame.Common.Common;
import com.example.user.logoquizgame.Food;
import com.example.user.logoquizgame.Social;

import java.util.List;

/**
 * Created by user on 24/01/2018.
 */

public class GridViewSuggestSocial extends BaseAdapter{

    private List<String> suggestSource;
    private Context context;
    private Social socialActivity;

    public GridViewSuggestSocial(List<String> suggestSource, Context context, Social mainActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.socialActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return suggestSource.size();
    }

    @Override
    public Object getItem(int i) {
        return suggestSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        Button button;
        if(convertView==null){
            if(suggestSource.get(i).equals("null")){
                button=new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
            }
            else{
                button=new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85,85));
                button.setPadding(8,8,8,8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.YELLOW);
                button.setText(suggestSource.get(i));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(String.valueOf(Food.answer).contains(suggestSource.get(i))){
                            char compare =suggestSource.get(i).charAt(0);//get char
                            for(int i=0;i<Food.answer.length;i++){
                                if(compare==Food.answer[i])
                                    Common.user_submit_answer[i]=compare;

                            }

                            GridViewAnswerAdapter answerAdapter=new GridViewAnswerAdapter(Common.user_submit_answer,context);

                            socialActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            socialActivity.suggestSource.set(i,"null");
                            socialActivity.suggestAdapter=new GridViewSuggestSocial(socialActivity.suggestSource,context,socialActivity);
                            socialActivity.gridViewSuggest.setAdapter(socialActivity.suggestAdapter);
                            socialActivity.suggestAdapter.notifyDataSetChanged();
                        }else{
                            socialActivity.suggestSource.set(i,"null");
                            socialActivity.suggestAdapter=new GridViewSuggestSocial(suggestSource,context,socialActivity);
                            socialActivity.gridViewSuggest.setAdapter(socialActivity.suggestAdapter);
                            socialActivity.suggestAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        }else
            button=(Button)convertView;
        return button;


    }

}
