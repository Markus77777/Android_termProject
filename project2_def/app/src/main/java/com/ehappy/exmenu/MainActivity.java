package com.ehappy.exmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Spinner spnPrefer;
    private GridView gridView;
    private int[] imageIds = {
            R.drawable.p1,R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5,R.drawable.p6,
            R.drawable.p7,R.drawable.p8,R.drawable.p9,
            R.drawable.p10,R.drawable.p11,R.drawable.p12,
            R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnPrefer=(Spinner) findViewById(R.id.spnPrefer);

        String[] extra= new String[] {"縮圖排列","詳細資訊"};
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 建立 ArrayAdapter
        ArrayAdapter<String> adapterExtra=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,extra);

        // 設定 Spinner 顯示的格式
        adapterExtra.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 設定 Spinner 的資料來源
        spnPrefer.setAdapter(adapterExtra);

        // 設定 spnPrefer 元件 ItemSelected 事件的 listener 為  spnPreferListener
        spnPrefer.setOnItemSelectedListener(spnPreferListener);

        // 取得介面元件

        GridView gridView = (GridView) findViewById(R.id.GridView01);

        // 建立自訂的 Adapter
        MyAdapter adapter=new MyAdapter(this);

        // 設定 GridView 的資料來源
        gridView.setAdapter(adapter);



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Third.class);
                Bundle bundle=new Bundle();
                bundle.putInt("POS", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }




    //OptionMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_about:
                Toast.makeText(this, "簡易的圖片顯示器!", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_quit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 自訂的 MyAdapter 類別，繼承 BaseAdapter 類別
    class MyAdapter extends BaseAdapter {
        private Context mContext;
        public MyAdapter(Context c){
            mContext=c;
        }
        @Override
        public int getCount(){
            return imageIds.length; // 圖片共有多少張
        }
        @Override
        public Object getItem(int position){
            return position;
        }
        @Override
        public long getItemId(int position){
            return position; // 目前圖片索引
        }

        // 定義 GridView 顯示的圖片
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ImageView iv = new ImageView(mContext);
            iv.setImageResource(imageIds[position]);
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setLayoutParams(new GridView.LayoutParams(580,300));
            return iv;
        }
    }
    //Spinner
    private Spinner.OnItemSelectedListener spnPreferListener=
            new Spinner.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                           break;
                        case 1:
                            Intent intent=new Intent();
                            intent.setClass(MainActivity.this,Second.class);
                            startActivity(intent);
                            break;
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };
    //將spinner設成默認選項
    protected void onResume() {
        super.onResume();
        spnPrefer.setSelection(0);
    }
}