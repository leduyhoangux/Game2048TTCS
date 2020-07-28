package com.example.game2048;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectGesture implements GestureDetector.OnGestureListener { // su dung de vuot hinh
    private final static int MIN_SWIPE_DISTANCE_X = 100; // khoang cah vuot
    private final static int MIN_SWIPE_DISTANCE_Y = 100;

    private final static int MAX_SWIPE_DISTANCE_X = 1000;
    private final static int MAX_SWIPE_DISTANCE_Y = 1000;

    private GamePlay activity = null;

    public GamePlay getActivity() {
        return activity;
    }

    public void setActivity(GamePlay activity) { //ham khoi tao
        this.activity = activity;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) { //

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float X = e2.getX() - e1.getX(); // e1 bat dau . e2 ket thuc
        float Y = e2.getY() - e1.getY();

        float absX = Math.abs(X); // lau gia tri tuyet doi
        float absY = Math.abs(Y);

        if(absX > absY){
            if(absX >= MIN_SWIPE_DISTANCE_X && absX <= MAX_SWIPE_DISTANCE_X){ // khoangcach cho phe[
                if(X < 0){
//                    Toast.makeText(getActivity(), "To left", Toast.LENGTH_SHORT).show();
                    activity.swipeLeft();
                }
                else{
//                    Toast.makeText(getActivity(), "To right", Toast.LENGTH_SHORT).show();
                    activity.swipeRight();
                }
            }
        } else {
            if(absY >= MIN_SWIPE_DISTANCE_Y && absY <= MAX_SWIPE_DISTANCE_Y){
                if(Y > 0){
//                    Toast.makeText(getActivity(), "To down", Toast.LENGTH_SHORT).show();
                    activity.swipeDown();
                }
                else{
//                    Toast.makeText(getActivity(), "To up", Toast.LENGTH_SHORT).show();
                    activity.swipeUp();
                }
            }
        }
        return true;
    }
}
