package com.example.mych_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //定义字符串用来保存各个信息
    private String account_str = "";
    private String pwd_str = "";
    //组件定义
    EditText account_edit;
    EditText password_edit;
    private List<UserInfo> list;
    private String[] num1;
    private String[] num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User_Database user=new User_Database(MainActivity.this);
        SQLiteDatabase sqLiteDatabase=user.getReadableDatabase();
        //user.dropTable(sqLiteDatabase);
        if(user.tabbleIsExist(sqLiteDatabase))
        {
            user.onCreate(sqLiteDatabase);
        }


        list=user.querydata(sqLiteDatabase);
        //Log.i("SQLite",list.get(0).getUsername());
        //Log.i("SQLite",list.get(0).getPaswd());
//        for(int i=0;i<list.size();i++){
//            num1[i]=list.get(i).getUsername();
//            num2[i]=list.get(i).getPaswd();
//        }
//        if (this.getIntent().getExtras() != null) {
//            Intent intent = this.getIntent();
//            Bundle bundle = intent.getExtras();//获取bundle
//            String phone = bundle.getString("phone");
//            String paswd = bundle.getString("paswd");
//            String sex = bundle.getString("sex");
//            String hobby = bundle.getString("hobby");
//            String city = bundle.getString("city");
//            TextView show_text = (TextView) findViewById(R.id.show_content);
//            show_text.setText("刚刚注册的数据：\n" + "用户名为:" + phone + "\n" +
//                    "密码为:" + paswd + "\n" + "性别是:" + sex + "\n" + "爱好是:" +
//                    hobby + "\n" + "城市是:" + city);
//            ;
//        }

        //组件初始化
        account_edit = (EditText) findViewById(R.id.account);
        password_edit = (EditText) findViewById(R.id.password);
//        监听器监听按钮事件
//        View linkReg = findViewById((R.id.register));
//        linkReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(MainActivity.this,register_Activity.class);
//                startActivity(i);
//            }
//        });

        //为按钮绑定点击事件
        TextView textView = (TextView)findViewById(R.id.admin_btn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到下一个界面
                Intent intent = new Intent(MainActivity.this, Sea_deluser_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void login(View v) {
        Intent intent = new Intent(MainActivity.this, thirdActivity.class);


        //获取手机号和密码
        account_str = account_edit.getText().toString();
        pwd_str = password_edit.getText().toString();
        Log.i("SQLite",account_str);
        //Log.i("SQLite",list.get(0).getUsername());

        int q=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(account_str))
            {
                Log.i("SQLite","用户名校验成功");
                if(list.get(i).getPaswd().equals(pwd_str))
                {
                    q=1;
                    Bundle bundle = new Bundle();
                    bundle.putString("account", account_str);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
        if(q!=1)
            Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();


    }

    //忘记密码Dialog弹窗
    public void forgetpwd(View v) {
        new AlertDialog.Builder(MainActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Warning")
                .setMessage("重置密码？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(MainActivity.this, "重置密码", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
    }

    //注册页面跳转
    public void register1(View v) {
        //显式意图
//        Intent intent = new Intent(MainActivity.this, register_Activity.class);
//        startActivity(intent);
        //隐式意图
        Intent intent = new Intent();

        intent.setAction(register_Activity.ACTION);
        //或直接使用intent.setAction("com.example.mych_login.second");(不推荐
        startActivity(intent);
    }
}
