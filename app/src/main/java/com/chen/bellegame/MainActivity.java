package com.chen.bellegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity implements View.OnClickListener{

    private ImageButton man,woman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.welcome_aty);
        man=findViewById(R.id.btn_man);
        woman=findViewById(R.id.btn_woman);
        man.setOnClickListener(this);
        woman.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,GameUi.class);
        switch (view.getId()){
            case R.id.btn_man:
               intent.putExtra("sex","男");
                break;
            case R.id.btn_woman:
                intent.putExtra("sex","女");
                break;
        }
        startActivity(intent);
    }
}
