package com.example.mych_login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class thirdActivity extends AppCompatActivity {
//    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//    NewAdapter myAdapter;
//    ListView mListView;

    private RadioGroup rg;
    private RadioButton mHome;
    private RadioButton mMe;
    private Fragment mFragment;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        //调用initDatas()方法并传值
//        initDatas();
//        myAdapter = new NewAdapter(list,this);
//
//        mListView=findViewById(R.id.listview);
//        mListView.setAdapter(myAdapter);

        intent=this.getIntent();
//        Bundle bundle=intent.getExtras();
//        String account=bundle.getString("account");
//        TextView show_text1=(TextView)findViewById(R.id.accountname);
//        show_text1.setText(account);



        //导航栏配置


        //初始化View
        initView();

        //初始化Fragment
        initFragment();
//        mFragment.setArguments(intent.getExtras());
//设置RadioGroup的按钮组监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHome:
                        switchFragment(new FragmentHome());
                        break;
                    case R.id.rbMe:
                        switchFragment(new FragmentMine());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //自定义方法,初始化数据源
//    void initDatas(){
//        //创建键值对集合
//        Map<String,Object> map = new HashMap<String,Object>();
//        //放入键值对数据
//        map.put("img",R.mipmap.song01);
//        map.put("title","收敛");
//        map.put("button","▶");
//        //包含三个键值对的集合放入行中
//        list.add(map);
//
//        map = new HashMap<String,Object>();
//        map.put("img",R.mipmap.song02);
//        map.put("title","全世界失眠");
//        map.put("button","▶");
//        //包含三个键值对的集合放入行中
//        list.add(map);
//
//        map = new HashMap<String,Object>();
//        map.put("img",R.mipmap.song03);
//        map.put("title","笑忘书");
//        map.put("button","▶");
//        //包含三个键值对的集合放入行中
//        list.add(map);
//
//        map = new HashMap<String,Object>();
//        map.put("img",R.mipmap.song05);
//        map.put("title","第三人称");
//        map.put("button","▶");
//        //包含三个键值对的集合放入行中
//        list.add(map);
//    }

    private void initFragment() {
//        mFragment = new FragmentMine();
        mFragment = new FragmentHome();
        mFragment.setArguments(intent.getExtras());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mFragment).commit();


    }

    private void switchFragment(Fragment fragment) {
        fragment.setArguments(intent.getExtras());
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.container, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction()
                        .hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }


    /**
     * 动态设置每个tab的图片宽高以及文字间距
     *
     * @param selector RadioButton的样式选择器
     * @param rb RadioButton的样式选择器
     */
    private void setStyle(int selector, RadioButton rb) {
//定义底部标签图片大小和位置
        Drawable drawableHome = getResources().getDrawable(selector);
//当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形， 这里就是设置图片大小的地方
        drawableHome.setBounds(0, 0, 60, 60);
//设置图片在文字的哪个方向
        rb.setCompoundDrawables(null, drawableHome, null, null);
    }


// 动态设置四个tab的样式
    private void initView() {
        rg = findViewById(R.id.radioGroup);
        mHome = findViewById(R.id.rbHome);
        mMe = findViewById(R.id.rbMe);

        setStyle(R.drawable.tab_home_selector, mHome);
        setStyle(R.drawable.tab_me_selector, mMe);
    }

}
