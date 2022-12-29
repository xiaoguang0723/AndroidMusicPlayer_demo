package com.example.mych_login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class FragmentMine extends Fragment {
    private View view;
    ImageView imageView;
    public String account="Test";
    public String hobby="hobby";
    public int fff=1;
    public FragmentMine() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {


            Bundle bundle = this.getArguments();

            account = bundle.getString("account");


        }catch (Exception e){
            fff=0;
        }
        User_Database user=new User_Database(getActivity());
        SQLiteDatabase sqLiteDatabase=user.getReadableDatabase();
        hobby=user.queryHobbyData(sqLiteDatabase,account);
        Log.i("cccc",hobby);
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        TextView show_text1=(TextView)view.findViewById(R.id.testtt);
        Log.i("cccccccccccc",show_text1.getText().toString());
        if(fff==1) {
            Log.i("cccccccccccc","赋值成功");
            show_text1.setText(hobby);
        }
        Log.i("cccccccccccc",account);
        Log.i("cccccccccccc",hobby);

        imageView = view.findViewById(R.id.information);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化控件
        imageView = getView().findViewById(R.id.information);
        // 给控件设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cccccccccccc","ttttttttt111");
                Intent intent=new Intent(getActivity(),InformationActivity.class);
                startActivity(intent);
            }
        });

//                    startActivity(new Intent(getActivity(),thirdActivity.class));


    }
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("cccccccccccc","ttttttttt111");
//                Intent intent=new Intent(getActivity(),thirdActivity.class);
//                startActivity(intent);
//            }
//        });


    public void inf(View v) {
//        Log.i("cccccccccccc","ttttttttt222");
//        Intent intent=new Intent(getActivity(),thirdActivity.class);
//        startActivity(intent);
    }
}