package com.example.mych_login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentHome extends Fragment {
    List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    NewAdapter myAdapter;
    ListView mListView;
    private View view;
    public String account="Test";
    public int fff=1;
    public String[] name={"走进你心里——空匪","陈奕迅——全世界失眠","王菲——笑忘书"};
    public static int[] icons={R.mipmap.song01,R.mipmap.song02,R.mipmap.song03};
    public FragmentHome() {
    }

//    @Override
//    public void onStart() {
//        Log.d("TAG","onStart");
//        //调用initDatas()方法并传值
//
//        super.onStart();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        try {
            Bundle bundle = this.getArguments();
            account = bundle.getString("account");
        }catch (Exception e){
            fff=0;
        }
//        特别注意！！！！
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mListView=(ListView)view.findViewById(R.id.music_listview);
        TextView show_text1=(TextView)view.findViewById(R.id.accountname);
        if(fff==1)
            show_text1.setText(account);

        //实例化一个适配器
        MyBaseAdapter adapter=new MyBaseAdapter();
        //列表设置适配器
        mListView.setAdapter(adapter);
        //列表元素的点击监听器
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //创建Intent对象，参数就是从frag1跳转到MusicActivity
                Intent intent=new Intent(FragmentHome.this.getContext(), MusicActivity.class);
                //将歌曲名和歌曲的下标存入Intent对象
                intent.putExtra("name",name[position]);
                intent.putExtra("position",String.valueOf(position));
                //开始跳转
                startActivity(intent);
            }
        });





        //initDatas();
        //myAdapter = new NewAdapter(list,view.getContext());

        //mListView.setAdapter(myAdapter);
        //Log.i("测试", "list"+list);
        //Log.i("测试", "myAdapter"+myAdapter.datas);
        //Log.i("测试", "mListView"+mListView.getAdapter().getItem(1).toString());

        return view;

    }


    //自定义方法,初始化数据源
    void initDatas(){
        //创建键值对集合
        Map<String,Object> map = new HashMap<String,Object>();
        //放入键值对数据
        map.put("img",R.mipmap.song01);
        map.put("title","收敛");
        map.put("button","▶");
        //包含三个键值对的集合放入行中
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.song02);
        map.put("title","全世界失眠");
        map.put("button","▶");
        //包含三个键值对的集合放入行中
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.song03);
        map.put("title","笑忘书");
        map.put("button","▶");
        //包含三个键值对的集合放入行中
        list.add(map);

        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.song05);
        map.put("title","第三人称");
        map.put("button","▶");
        //包含三个键值对的集合放入行中
        list.add(map);
    }


    //这里是创建一个自定义适配器，可以作为模板
    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount(){return  name.length;}
        @Override
        public Object getItem(int i){return name[i];}
        @Override
        public long getItemId(int i){return i;}

        @Override
        public View getView(int i ,View convertView, ViewGroup parent) {
            //绑定好VIew，然后绑定控件
            View view=View.inflate(FragmentHome.this.getContext(),R.layout.item_layout,null);
            TextView tv_name=view.findViewById(R.id.item_name);
            ImageView iv=view.findViewById(R.id.iv);
            //设置控件显示的内容，就是获取的歌曲名和歌手图片
            tv_name.setText(name[i]);
            iv.setImageResource(icons[i]);
            return view;
        }
    }
}