package com.ehappy.exmenu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Second extends AppCompatActivity {
    ListView lstPrefer;
    int[] resIds = new int[]{ R.drawable.p1,R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5,R.drawable.p6,
            R.drawable.p7,R.drawable.p8,R.drawable.p9,
            R.drawable.p10,R.drawable.p11,R.drawable.p12,
            R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        // 取得介面元件
        Button btnHome=(Button)findViewById(R.id.btnHome);
        // 設定 button 的 Listener
        btnHome.setOnClickListener(btnHomeListener);
        // 取得介面元件
        lstPrefer=(ListView)findViewById(R.id.lstPrefer);

        // 建立自訂的 Adapter
        MyAdapter adapter=new MyAdapter(this);

        // 設定 ListView 的資料來源
        lstPrefer.setAdapter(adapter);

        registerForContextMenu(lstPrefer);

    }
    //ContextMenu
    protected static final int MENU_1 = Menu.FIRST ;
    protected static final int MENU_2 = Menu.FIRST +1;
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v==lstPrefer){
            menu.add(0,MENU_1 ,1,"圖片備註");
            menu.add(0,MENU_2 ,2,"地點");
        }
    }
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()){
            case MENU_1: {
                break;
            }
            case MENU_2:
                Intent intent=new Intent();
                intent.setClass(Second.this,MapsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("POS", position);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }



    private Button.OnClickListener btnHomeListener = new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        public MyAdapter(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return resIds.length;
        }
        @Override
        public Object getItem(int position){
            return resIds[position];
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.mylayout, null);

            // 取得 mylayout.xml 中的元件
            ImageView imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            TextView txtName = ((TextView) convertView.findViewById(R.id.txtName));
            TextView txtInfo = ((TextView) convertView.findViewById(R.id.txtInfo));
            TextView textView = ((TextView) convertView.findViewById(R.id.textView));
            // 設定顯示圖片名稱
            imgLogo.setImageResource(resIds[position]);
            String fullResourceName = getResources().getResourceName(resIds[position]);
            String resourceName = fullResourceName.substring(fullResourceName.lastIndexOf('/') + 1);
            txtName.setText(resourceName);
            // 顯示圖片解析度
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true; // 設定為true表示只獲取圖片基本資訊而不解析整個圖片
            BitmapFactory.decodeResource(getResources(), resIds[position], options);
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;
            String resolution = imageWidth + " x " + imageHeight;
            txtInfo.setText(resolution);
            //顯示圖片大小
            InputStream is = getResources().openRawResource(resIds[position]);

            try {
                int fileSize = is.available();

                // 將檔案大小轉換為 MB，取小數點後一位
                double fileSizeInMB = fileSize / (1024.0 * 1024.0);
                String sizeText = String.format("%.1f MB", fileSizeInMB);

                textView.setText("Size: " + sizeText);
            }catch (IOException e) {
                e.printStackTrace();
            }



            return convertView;
        }
    }
}