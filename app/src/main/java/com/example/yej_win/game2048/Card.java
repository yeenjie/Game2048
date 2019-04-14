package com.example.yej_win.game2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

    private int num = 0;
    private TextView label;
    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);
        label.setTextColor(Color.WHITE);
        label.getBackground().setAlpha(num<=255?num:255);
        LayoutParams layoutParams = new LayoutParams(-1,-1);
        layoutParams.setMargins(10,10,0,0);
        addView(label,layoutParams);
        setNum(0);

    }

    public void updateColor(){
        this.label.setBackgroundColor(Color.rgb(num*7+40, num*10+120, num*3+30));
        if(this.num>16){
            this.label.setTextSize(32+num/50);
            label.setTextColor(Color.rgb(num*10,num*10,num*10));
        }else {
            label.setTextColor(Color.WHITE);
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;

        if(num<=0){
            label.setText("");
        }else{
            label.setText(num+""); //传入int会变成字符串的id
        }


    }

    public boolean equals(Card card) {
        return getNum() == card.getNum();
    }



}
