package com.example.mych_login;

import android.content.ComponentName;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register_Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    //定义字符串用来保存各个信息
    private String phone_str="";
    private String paswd_str="";
    //默认为男性选中
    private String sex_str="男性";
    private String hobby_str="1";
    private String city_str="";

    //组件定义
    EditText phone_edit, paswd_edit;
    RadioGroup sex_group;
    RadioButton nan_but,nv_but;
    CheckBox cs1,cs2,cs3,cs4,cs5,cs6;
//    Button register;
    Spinner spinner;



    static final String ACTION="com.example.mych_login.second";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        //组件初始化
        phone_edit=(EditText)findViewById(R.id.account);
        paswd_edit=(EditText)findViewById(R.id.password);
        sex_group=(RadioGroup) findViewById(R.id.sex);
        //添加性别单选按钮组的监听事件
        sex_group.setOnCheckedChangeListener(this);
        nan_but=(RadioButton)findViewById(R.id.radiobutton1);

        cs1=(CheckBox)findViewById(R.id.android);
        cs2=(CheckBox)findViewById(R.id.ios);
        cs3=(CheckBox)findViewById(R.id.linux);
        cs4=(CheckBox)findViewById(R.id.java);
        cs5=(CheckBox)findViewById(R.id.CPlus);
        cs6=(CheckBox)findViewById(R.id.VB);
//        register=(Button)findViewById(R.id.zhuce);

        spinner=(Spinner)findViewById(R.id.spinner);
        final String[] city=new String[]{"北京","上海","武汉","南京", "南昌","信阳"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,city);
        spinner.setAdapter(adapter);
        //城市下拉列表添加监听事件
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView,View view, int i,long l) {
                city_str=city[i];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });


    }
    //返回Login页面
    public void backToMain(View v) {
        ComponentName componentname = new ComponentName(register_Activity.this, MainActivity.class);
        Intent intent = new Intent();
        intent.setComponent(componentname);


        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
//根据用户选择来改变sex_str的值
        sex_str=checkedId==R.id.radiobutton1?"男性":"女性";
    }

    //注册成功Toast提示+数据传递+回到login
    public void RegisterSuccess(View v) {
        Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();

        ComponentName componentname = new ComponentName(register_Activity.this, MainActivity.class);
        Intent intent = new Intent();
        intent.setComponent(componentname);

        //获取手机号和密码
        phone_str=phone_edit.getText().toString();
        paswd_str=paswd_edit.getText().toString();
        //获取兴趣爱好即复选框的值
        hobby_str="";//清除上一次已经选中的选项
        if(cs1.isChecked()){
            hobby_str+=cs1.getText().toString()+" ";
        }if(cs2.isChecked()){
            hobby_str+=cs2.getText().toString()+" ";
        }if(cs3.isChecked()){
            hobby_str+=cs3.getText().toString()+" ";
        }if(cs4.isChecked()) {
            hobby_str+=cs4.getText().toString()+" ";
        }if(cs5.isChecked()) {
            hobby_str+=cs5.getText().toString()+" ";
        }if(cs6.isChecked()) {
            hobby_str+=cs6.getText().toString()+" ";
        }

        User_Database us_db=new User_Database(register_Activity.this);
        SQLiteDatabase sqLiteDatabase=us_db.getWritableDatabase();
        us_db.adddata(sqLiteDatabase,phone_str,paswd_str,sex_str,hobby_str);

        Bundle bundle= new Bundle();
        bundle.putString ("phone",phone_str);
        bundle.putString ("paswd",paswd_str);
        bundle.putString ("sex",sex_str);
        bundle.putString( "hobby",hobby_str);
        bundle.putString ("city",city_str);

        //intent.putExtras(bundle);
        startActivity(intent);
    }

}
