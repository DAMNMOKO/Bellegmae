package com.chen.bellegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameUi extends Activity implements View.OnTouchListener,View.OnClickListener{

    private RelativeLayout relativeLayout;
    private LinearLayout list;
    private Button btnPeishi,btnYifu,btn_Xiezi,btn_fanhui;
    private View yifu_aty,peishi_aty;
    private ImageButton xiezi_aty;
    private Button btnSave,btnReset;
    private ImageView taozhuang,changku,duanku,huangshangyi,lanshangyi;
    private ImageView fashi,yanjing;
    private ImageView trash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_aty);
        initApp();
    }

    /**
     * 初始化程序
     */
    private void initApp(){
        relativeLayout=findViewById(R.id.relativelayout);
        isFirst();

        btn_Xiezi=findViewById(R.id.game_btn_xiezi);
        btnPeishi=findViewById(R.id.game_btn_peishi);
        btnYifu=findViewById(R.id.game_btn_yifu);
        list=findViewById(R.id.game_Linear);
        btnSave=findViewById(R.id.game_btn_Save);
        btnReset=findViewById(R.id.game_btn_reset);
        btn_fanhui=findViewById(R.id.game_btn_fanhui);


        btn_Xiezi.setOnClickListener(this);
        btnYifu.setOnClickListener(this);
        btnPeishi.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btn_fanhui.setOnClickListener(this);

        trash=findViewById(R.id.game_img_trash);

        LayoutInflater inflater=(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        yifu_aty=inflater.inflate(R.layout.yifu_aty,null);
        peishi_aty=inflater.inflate(R.layout.peishi_aty,null);
        xiezi_aty=new ImageButton(this);
        xiezi_aty.setImageResource(R.mipmap.xiezi);
        xiezi_aty.setBackgroundResource(00000000);
        xiezi_aty.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        xiezi_aty.setOnClickListener(this);
    }

    /**
     */
    private void isFirst(){
        Toast.makeText(this, "手指点击衣服给她穿上吧！！！\n推到垃圾桶可清除这个衣服呢！", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                int TRASH_X=(int)trash.getX();
                int TRASH_Y=(int)trash.getY();

                int x = (int) event.getRawX();
                int y = (int) event.getRawY();
                Log.i("info", "View-X:"+x+"-Y:"+y);
                Log.i("info", "TRASH-X:"+TRASH_X+"-Y:"+TRASH_Y+"- +Y:"+(TRASH_Y+trash.getHeight()));
                if(TRASH_X<x && TRASH_X+trash.getWidth()>x){
                    Log.i("info", "onTouch: X成立");
                    if(TRASH_Y<y && TRASH_Y+trash.getHeight()>y){
                        Log.i("info", "onTouch: Y成立");
                        relativeLayout.removeView(view);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX()-view.getWidth();
                int dy = (int) event.getRawY()-view.getHeight();
                view.setX(dx);
                view.setY(dy);
                break;
        }
        view.invalidate();
        return true;
    }

    private int y=1,x=1,p=1;
    @Override
    public void onClick(View view) {
        list.removeAllViews();
        ImageView imageView=new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        relativeLayout.addView(imageView);
        imageView.setOnTouchListener(this);
        switch (view.getId()){
            case R.id.game_btn_peishi:
                p*=-1;
                y=1;
                x=1;
                if(p==-1) {
                    list.addView(peishi_aty);
                    addViewContentForPeishi();
                }
                else
                    list.removeView(peishi_aty);
                break;
            case R.id.game_btn_xiezi:
                x*=-1;
                y=1;
                p=1;
                if(x==-1)
                    list.addView(xiezi_aty);
                else
                    list.removeView(xiezi_aty);
                break;
            case R.id.game_btn_yifu:
                y*=-1;
                x=1;
                p=1;
                if(y==-1) {
                    list.addView(yifu_aty);
                    addViewContentForYifu();
                }
                else
                    list.removeView(yifu_aty);
                break;
            /*****************底部按钮的点击事件***********************/
            case R.id.game_btn_fanhui:
                Intent intent = new Intent(GameUi.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.game_btn_Save:
                screenshot(view);
                break;
            case R.id.game_btn_reset:
                relativeLayout.removeAllViews();
                setContentView(R.layout.game_aty);
                initApp();
                break;
            /*****************橱柜的点击事件**************/
            case R.id.yifu_taozhuang:
                imageView.setImageResource(R.mipmap.taozhuang);
                break;
            case R.id.yifu_changku:
                imageView.setImageResource(R.mipmap.changku);
                break;
            case R.id.yifu_duanku:
                imageView.setImageResource(R.mipmap.duanku);
                break;
            case R.id.yifu_huangshangyi:
                imageView.setImageResource(R.mipmap.huangshangyi);
                break;
            case R.id.yifu_lanshangyi:
                imageView.setImageResource(R.mipmap.lanshangyi);
                break;
            case R.id.peishi_fashi:
                imageView.setImageResource(R.mipmap.fashi);
                break;
            case R.id.peishi_yanjing:
                imageView.setImageResource(R.mipmap.yanjing);
                break;
        }
        if(view==xiezi_aty){
            imageView.setImageResource(R.mipmap.xiezi);
        }
    }

    private void addViewContentForYifu(){
        taozhuang=findViewById(R.id.yifu_taozhuang);
        changku=findViewById(R.id.yifu_changku);
        duanku=findViewById(R.id.yifu_duanku);
        huangshangyi=findViewById(R.id.yifu_huangshangyi);
        lanshangyi=findViewById(R.id.yifu_lanshangyi);

        taozhuang.setOnClickListener(this);
        changku.setOnClickListener(this);
        duanku.setOnClickListener(this);
        huangshangyi.setOnClickListener(this);
        lanshangyi.setOnClickListener(this);
    }

    private void addViewContentForPeishi(){
        fashi=findViewById(R.id.peishi_fashi);
        yanjing=findViewById(R.id.peishi_yanjing);

        fashi.setOnClickListener(this);
        yanjing.setOnClickListener(this);
    }

    /**
     */
    private void screenshot(View v)
    {
        //创建一个Toast提示信息
        Toast toast = new Toast(this);
        //设置显示位置
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 100);
        //创建一个ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.icon);
        //创建一个LinearLayout容器
        LinearLayout ll = new LinearLayout(this);
        //设置布局控件排列方向
        ll.setOrientation(LinearLayout.VERTICAL);
        //向LinearLayout中添加图片
        ll.addView(imageView);

        TextView textView = new TextView(this);
        textView.setText("好不容易找到一张很像你的卡通人物");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        ll.addView(textView);

        //设置Toast显示自定义的View
        toast.setView(ll);
        //设置Toast的时间显示
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();

    }
}
