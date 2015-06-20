package com.example.radasm.keyboard;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view){
//        // TODO Auto-generated method stub
//        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
//                .setTitle("title").setMessage("message").create();

        KeyboardView keyboardView = new KeyboardView(MainActivity.this);
        View keyboard = keyboardView.setup(new OkCallback() {
            @Override
            public void ok(String password) {
                Toast.makeText(MainActivity.this,password,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog=new AlertDialog.Builder(MainActivity.this).setView(keyboard).create();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.mystyle);  //添加动画
        dialog.show();// TODO Auto-generated method stub
    }


}
