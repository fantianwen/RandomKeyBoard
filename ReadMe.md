#Random_Keyboard
##like this:
![randomkeyboard](https://github.com/fantianwen/MarkDown/raw/master/RandomKeyboard/randomkeyboard.png)
##CallBack
implement OkCallback to get what you input

a sample like this:	

```java
public void show(View view){
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
```


