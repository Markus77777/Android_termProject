package com.ehappy.exmenu;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;


public class Third extends AppCompatActivity {

    private ImageView imgShow;
    int[] resIds = new int[]{ R.drawable.p1,R.drawable.p2,R.drawable.p3,
            R.drawable.p4,R.drawable.p5,R.drawable.p6,
            R.drawable.p7,R.drawable.p8,R.drawable.p9,
            R.drawable.p10,R.drawable.p11,R.drawable.p12,
            R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page3);

        // 取得介面元件
        Button btnHome=(Button)findViewById(R.id.btnHome);
        // 設定 button 的 Listener
        btnHome.setOnClickListener(btnHomeListener);
        imgShow=(ImageView) findViewById(R.id.imgShow);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        int position=bundle.getInt("POS");

        imgShow.setImageResource(resIds[position]);
    }



    private Button.OnClickListener btnHomeListener = new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };

}