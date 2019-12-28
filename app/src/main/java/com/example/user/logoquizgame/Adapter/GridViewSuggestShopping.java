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
import com.example.user.logoquizgame.Shopping;

import java.util.List;

/**
 * Created by user on 23/01/2018.
 */

public class GridViewSuggestShopping extends BaseAdapter{
    private List<String> suggestSource;
    private Context context;
    private Shopping ShoppingActivity;

    public GridViewSuggestShopping(List<String> suggestSource, Context context, Shopping mainActivity) {
        this.suggestSource = suggestSource;
        this.context = context;
        this.ShoppingActivity = mainActivity;
    }

    public int getCount() {
        return suggestSource.size();
    }

    public Object getItem(int i) {
        return suggestSource.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

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

                        if(String.valueOf(Shopping.answer).contains(suggestSource.get(i))){
                            char compare =suggestSource.get(i).charAt(0);//get char
                            for(int i=0;i<Shopping.answer.length;i++){
                                if(compare==Shopping.answer[i])
                                    Common.user_submit_answer[i]=compare;

                            }

                            GridViewAnswerAdapter answerAdapter=new GridViewAnswerAdapter(Common.user_submit_answer,context);

                            ShoppingActivity.gridViewAnswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            ShoppingActivity.suggestSource.set(i,"null");
                            ShoppingActivity.suggestAdapter=new GridViewSuggestShopping(ShoppingActivity.suggestSource,context,ShoppingActivity);
                            ShoppingActivity.gridViewSuggest.setAdapter(ShoppingActivity.suggestAdapter);
                            ShoppingActivity.suggestAdapter.notifyDataSetChanged();
                        }else{
                            ShoppingActivity.suggestSource.set(i,"null");
                            ShoppingActivity.suggestAdapter=new GridViewSuggestShopping(ShoppingActivity.suggestSource,context,ShoppingActivity);
                            ShoppingActivity.gridViewSuggest.setAdapter(ShoppingActivity.suggestAdapter);
                            ShoppingActivity.suggestAdapter.notifyDataSetChanged();
                        }

                    }
                });
            }
        }else
            button=(Button)convertView;
        return button;


    }
}
