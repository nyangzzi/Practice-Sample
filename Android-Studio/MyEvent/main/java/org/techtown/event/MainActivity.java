package org.techtown.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float curX = event.getX();
                float curY = event.getY();

                if(action == MotionEvent.ACTION_DOWN){
                    println("손가락 눌림 : " + curX + ", " + curY);
                }
                else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락 움직임 : " + curX + ", " + curY);
                }
                else if(action == MotionEvent.ACTION_UP){
                    println("손가락 뗌 : " + curX + ", " + curY);
                }
                return true;
            }
        });
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
               println("onDown 호출됨");
               return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            //얼마만큼의 거리에서 스크롤 되었는지 알려줌
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress 호출됨");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling 호출됨" +velocityX + ", " + velocityY);   //속도 계산
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //키가 눌렸을 때 자동 호출
        if (keyCode == KeyEvent.KEYCODE_BACK){
            println("시스템 [BACK] 버튼이 눌렸어요");
            return true;    //return true 를 하면 이후의 동작이 취소된다
        }
        return false;
    }

    public void println(String data){
        textView.append((data+"\n"));
    }
}