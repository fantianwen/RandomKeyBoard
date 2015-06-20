package com.example.radasm.keyboard;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by RadAsm on 15/6/20.
 */
public class KeyboardView implements View.OnClickListener {

    private static final String TAG = KeyboardView.class.getSimpleName();
    private Context context;
    private View mView;
    private OkCallback okCallback;

    private StringBuilder passwordBuilder;
    private MyHandler myHandler;

    private EditText password;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView zero;
    private TextView dot;


    private Button delete;
    private Button ac;
    private Button ok;

    int[] randoms=new int[]{0,1,2,3,4,5,6,7,8,9};

    private class MyHandler extends Handler{
        WeakReference<Context> ctx;
        MyHandler(Context ctx){
            this.ctx=new WeakReference<Context>(ctx);
        }

        @Override
        public void handleMessage(Message msg) {
            int[] newRandoms=(int[])msg.obj;
            zero.setText(newRandoms[0] + "");
            one.setText(newRandoms[1]+"");
            two.setText(newRandoms[2]+"");
            three.setText(newRandoms[3]+"");
            four.setText(newRandoms[4]+"");
            five.setText(newRandoms[5]+"");
            six.setText(newRandoms[6]+"");
            seven.setText(newRandoms[7]+"");
            eight.setText(newRandoms[8]+"");
            nine.setText(newRandoms[9]+"");
        }
    }

    public KeyboardView(Context context) {
        init(context);
    }

    /**
     * 属性，资源的初始化
     */
    private void init(Context context) {
        this.context=context;

    }

    public View setup(OkCallback okCallback){
        myHandler=new MyHandler(context);
        this.okCallback=okCallback;
        mView=View.inflate(context,R.layout.keyboard,null);

        initChild();
        return mView;

    }
    private void initChild() {
        passwordBuilder=new StringBuilder();

        password= (EditText) mView.findViewById(R.id.password);

        one= myfindViewById(R.id.one);
        two=myfindViewById(R.id.two);
        three=myfindViewById(R.id.three);
        four=myfindViewById(R.id.four);
        five=myfindViewById(R.id.five);
        six=myfindViewById(R.id.six);
        seven=myfindViewById(R.id.seven);
        eight=myfindViewById(R.id.eight);
        nine=myfindViewById(R.id.nine);
        zero=myfindViewById(R.id.zero);

        delete= (Button) mView.findViewById(R.id.delete);
        ac= (Button) mView.findViewById(R.id.ac);
        ok= (Button) mView.findViewById(R.id.ok);

        randomNumber();
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);

        delete.setOnClickListener(this);
        ac.setOnClickListener(this);
        ok.setOnClickListener(this);
    }

    private void randomNumber() {
        new Thread(){
            @Override
            public void run() {
                boolean[] flags= new boolean[10];
                for(int i=0;i<10;i++){
                    Random random = new Random();
                    int j;
                    j = Math.abs(random.nextInt()) % 10;
                    Log.e(TAG,j+"");
                    while(flags[j]){
                        j=j+1;
                        j=j%10;
                    }
                    Log.e(TAG,j+"");
                    randoms[i]=j;
                    flags[j]=true;
                }
                Log.e(TAG,"end");
                Message msg=Message.obtain();
                msg.obj=randoms;
                myHandler.sendMessage(msg);
            }
        }.start();
    }

    private TextView myfindViewById(int id){
        return (TextView) mView.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.one:
                passwordBuilder.append(randoms[1]);
                refreshTextView();
                break;
            case R.id.two:
                passwordBuilder.append(randoms[2]);
                refreshTextView();
                break;
            case R.id.three:
                passwordBuilder.append(randoms[3]);
                refreshTextView();
                break;
            case R.id.four:
                passwordBuilder.append(randoms[4]);
                refreshTextView();
                break;
            case R.id.five:
                passwordBuilder.append(randoms[5]);
                refreshTextView();
                break;
            case R.id.six:
                passwordBuilder.append(randoms[6]);
                refreshTextView();
                break;
            case R.id.seven:
                passwordBuilder.append(randoms[7]);
                refreshTextView();
                break;
            case R.id.eight:
                passwordBuilder.append(randoms[8]);
                refreshTextView();
                break;
            case R.id.nine:
                passwordBuilder.append(randoms[9]);
                refreshTextView();
                break;
            case R.id.zero:
                passwordBuilder.append(randoms[0]);
                refreshTextView();
                break;
            case R.id.delete:
                if(TextUtils.isEmpty(passwordBuilder.toString())){
                    return;
                }
                passwordBuilder.deleteCharAt(passwordBuilder.length()-1);
                refreshTextView();
                break;
            case R.id.ac:
                passwordBuilder.delete(0,passwordBuilder.length());
                refreshTextView();
                break;
            case R.id.ok:
                okCallback.ok(passwordBuilder.toString());
                break;
        }
    }

    private void refreshTextView(){
        password.setText(passwordBuilder.toString());
        password.setSelection(passwordBuilder.length());
    }
}
