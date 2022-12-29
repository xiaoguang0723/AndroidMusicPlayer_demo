package com.example.mych_login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class NewAdapter extends BaseAdapter {
    //属性1：列表项数据源 属性
    List<Map<String,Object>> datas;
    //属性2：上下文对象 主交互对象
    Context mContext;
    //创建一个构造函数
    NewAdapter(List<Map<String,Object>> datas, Context mContext){
        this.datas=datas;
        this.mContext=mContext;
    }
    @Override
    //列表项(数据集合元素)"个数
    public int getCount() {
        return datas.size();//size方法获取数据项个数
    }

    @Override
    //获取集合中元素索引
    public Object getItem(int i) {
        return datas.get(i);//集合提供的get方法
    }

    @Override
    //通过列表集合中的索引获得元素id
    public long getItemId(int i) {
        return i;//索引即为id
    }

    @Override
    //视图类--View--布局类、视图容器类、button、imageview、textview
    //将数据源放入控件，并返回列表项(整行)，以及单击事件逻辑产生
    public View getView(int i, View view, ViewGroup viewGroup) {
        //实例化内部类
        ViewHolder holder = new ViewHolder();
        //创建列表项,View绑定布局文件,绑定列表项UI
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.list_item_layout,null);
            holder.mImageView=view.findViewById(R.id.image1);
            holder.mTextView=view.findViewById(R.id.text1);
            holder.mButton=view.findViewById(R.id.button1);

            //按钮单击事件的监听
            holder.mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view0) {
                    //弹出消息提示UI
                    Toast.makeText(mContext,"开始播放！",Toast.LENGTH_SHORT).show();
                }
            });
            //列表项与holder关联
            view.setTag(holder);
        }
        else{
            holder=(ViewHolder) view.getTag();
        }
        //绑定数据源
        // i为行的索引,引号内为每一行内部键的名称
        holder.mImageView.setImageResource((Integer) datas.get(i).get("img"));
        holder.mTextView.setText(datas.get(i).get("title").toString());
        holder.mButton.setText(datas.get(i).get("button").toString());
        return view;
    }
    //内部类
    static class ViewHolder{ //只能访问其它静态成员；可在外部根据类名被调用
        //三个UI组件
        ImageView mImageView;
        TextView mTextView;
        Button mButton;
    }
}