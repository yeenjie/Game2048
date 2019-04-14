package com.example.yej_win.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class GameView extends GridLayout {
    private int cardWidth;
    public Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();
    public GameView(Context context) {
        super(context);
        initGameView();

    }

    public Card[][] getCardsMap() {
        return cardsMap;
    }

    private int getCardWidth()
    {
        //屏幕信息的对象
        DisplayMetrics displayMetrics;
        displayMetrics = getResources().getDisplayMetrics();
        //获取屏幕信息
        int cardWidth;
        cardWidth = displayMetrics.widthPixels;

        //一行有四个卡片，每个卡片占屏幕的四分之一
        return ( cardWidth - 10 ) / 4;

    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initGameView();
    }

    public void initGameView(){
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        cardWidth = getCardWidth();
        addCard(cardWidth,cardWidth);
        startGame();

        setOnTouchListener(new View.OnTouchListener(){

            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN: {
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        new LogicClass(cardsMap).moveLogic(offsetX,offsetY);

//                        if(Math.abs(offsetX)>Math.abs(offsetY)){
//                            addRandomNum(); addRandomNum();
//                            if(offsetX<-5){
//                                swipeLeft();
//                            }else if(offsetX>5){
//                                swipeRight();
//                            }
//                        }else{
//                            addRandomNum(); addRandomNum();
//                            if(offsetY<-5){
//                                swipeUp();
//                            }else if(offsetY>5){
//                                swipeDown();
//                            }
//                        }
                    }

                    if(new LogicClass(cardsMap).checkComplete()){
                        System.out.println("check！");
                        new AlertDialog.Builder(getContext()).setTitle("你好").setMessage("游戏结束").setNegativeButton("重来吧", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startGame();
                            }
                        }).show();
                    }else{
                        System.out.println("!!!!!!");
                    }
                }

                return true;
            }
        });
    }

    //初始化界面
    private void addCard(int cardWidth,int cardHeight){

        Card card;
        for (int y = 0; y < 4 ; y++) {
            for (int x = 0; x < 4; x++) {
                card = new Card(getContext());
                card.setNum(0);
                addView(card,cardWidth,cardHeight);
                cardsMap[x][y] = card;
            }
        }
    }


//    @Override
////    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
////        super.onSizeChanged(w, h, oldw, oldh);
////        System.out.println("test");
////        cardWidth = (Math.min(w,h)-10)/4;
////        addCard(cardWidth,cardWidth);
////        System.out.println("OK");
////    }

    private void startGame(){
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }
        for (int i = 0; i < 8; i++) {
            new LogicClass(cardsMap).addRandomNum();
        }
    }


//    private void addRandomNum(){
//        emptyPoints.clear();
//        for (int y = 0; y < 4; y++) {
//            for (int x = 0; x < 4; x++) {
//                if(cardsMap[x][y].getNum()<=0){
//                    emptyPoints.add(new Point(x,y));
//                }
//            }
//        }
//        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
//        System.out.println("px"+p.x+"py"+p.y);
//        cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
//    }

//    private void swipeLeft(){
//        for (int y = 0; y < 4; y++) {
//            for (int x = 0; x < 4; x++) {
//                for (int x1 = x+1; x1 < 4; x1++) {
//                    if(cardsMap[x1][y].getNum()>0){
//                        if(cardsMap[x][y].getNum()<=0){
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
//                            cardsMap[x1][y].setNum(0);
//                            //继续判断是否能继续往下移动
//                            x--;
//                            break;
//                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum()*2);
//                            cardsMap[x1][y].setNum(0);
//                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private void swipeRight(){
//        for (int y = 0; y < 4; y++) {
//            for (int x = 3; x >= 0; x--) {
//                for (int x1 = x-1; x1 >= 0; x1--) {
//                    if(cardsMap[x1][y].getNum()>0){
//                        if(cardsMap[x][y].getNum()<=0){
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
//                            cardsMap[x1][y].setNum(0);
//                            //继续判断是否能继续往下移动
//                            x++;
//                            break;
//                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
//                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum()*2);
//                            cardsMap[x1][y].setNum(0);
//                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private void swipeUp(){
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                for (int y1 = y+1; y1 < 4; y1++) {
//                    if(cardsMap[x][y1].getNum()>0){
//                        if(cardsMap[x][y].getNum()<=0){
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
//                            cardsMap[x][y1].setNum(0);
//                            //继续判断是否能继续往下移动
//                            y--;
//                            break;
//                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum()*2);
//                            cardsMap[x][y1].setNum(0);
//                            //加分
//                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    private void swipeDown(){
//        for (int x = 0; x < 4; x++) {
//            for (int y = 3; y >= 0; y--) {
//                for (int y1 = y-1; y1 >= 0; y1--) {
//                    if(cardsMap[x][y1].getNum()>0){
//                        if(cardsMap[x][y].getNum()<=0){
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
//                            cardsMap[x][y1].setNum(0);
//                            ///继续判断是否能继续往下移动
//                            y++;
//                            break;
//                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
//                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum()*2);
//                            cardsMap[x][y1].setNum(0);
//                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }

}
