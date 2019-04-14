package com.example.yej_win.game2048;

import android.app.AlertDialog;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class LogicClass {
    private  Card[][] cardsMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<>();
    public LogicClass(Card[][] cardsMap){
        this.cardsMap =cardsMap;
    }
    public  void moveLogic(float offsetX,float offsetY){
        if(Math.abs(offsetX)>Math.abs(offsetY)){
            if(offsetX<-5){
                swipeLeft();
            }else if(offsetX>5){
                swipeRight();
            }
        }else{
            if(offsetY<-5){
                swipeUp();
            }else if(offsetY>5){
                swipeDown();
            }
        }
        //新块生产
        if(Math.abs(offsetX)>5||Math.abs(offsetY)>5){
            addRandomNum(); addRandomNum();
        }


    }


    private void swipeLeft(){
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x+1; x1 < 4; x1++) {
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            //继续判断是否能继续往下移动
                            x--;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());

                        }
                        break;
                    }
                }
            }
        }
    }

    private void swipeRight(){
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x-1; x1 >= 0; x1--) {
                    if(cardsMap[x1][y].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            //继续判断是否能继续往下移动
                            x++;
                        }else if(cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum()*2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());

                        }
                        break;
                    }
                }
            }
        }
    }

    private void swipeUp(){
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y+1; y1 < 4; y1++) {
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            //继续判断是否能继续往下移动
                            y--;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            //加分
                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());

                        }
                        break;
                    }
                }
            }
        }

    }

    private void swipeDown(){
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y-1; y1 >= 0; y1--) {
                    if(cardsMap[x][y1].getNum()>0){
                        if(cardsMap[x][y].getNum()<=0){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            ///继续判断是否能继续往下移动
                            y++;
                        }else if(cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum()*2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore( cardsMap[x][y].getNum());

                        }
                        break;
                    }
                }
            }
        }
    }

    //随机生成新块
    public void addRandomNum(){
        emptyPoints.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(cardsMap[x][y].getNum()<=0){
                    emptyPoints.add(new Point(x,y));
                }
            }
        }
        if(emptyPoints.size()>0){
            Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
//        System.out.println("px"+p.x+"py"+p.y);
            cardsMap[p.x][p.y].setNum(Math.random()>0.1?2:4);
        }


    }

    public boolean checkComplete(){
        boolean complete = true;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if(cardsMap[x][y].getNum()==0||
                        (x>0&&cardsMap[x-1][y].equals(cardsMap[x][y]))||
                        (x<3&&cardsMap[x+1][y].equals(cardsMap[x][y]))||
                        (y>0&&cardsMap[x][y-1].equals(cardsMap[x][y]))||
                        (y<3&&cardsMap[x][y+1].equals(cardsMap[x][y]))){
                    complete = false;
                }
                cardsMap[x][y].updateColor();
            }
        }
        return complete;
    }
}
