package com.ehappy.exmenu;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;  //宣告 google map 物件
    private double[] v={25.033611,24.123456, 24.654321, 24.987654, 24.333333, 24.222222, 24.555555, 24.777777, 24.888888, 24.000000, 24.111111, 24.444444, 24.333333, 24.666666, 24.444444, 24.777777, 24.888888};
    private double[] v1={121.565000,121.123456, 121.654321, 121.987654, 121.333333, 121.222222, 121.555555, 121.777777, 121.888888, 121.000000, 121.111111, 121.444444, 121.333333, 121.666666, 121.444444, 121.777777, 121.888888};
    private int position;
    Spinner spnGeoPoint,spnMapType;

    String[] MapTypes= new String[] {"一般地圖","混合地圖","衛星地圖","地形圖"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        spnMapType=(Spinner) findViewById(R.id.spnMapType);

        // 建立 ArrayAdapter
        ArrayAdapter<String> adapterspnMapType=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,MapTypes);

        // 設定 Spinner 顯示的格式
        adapterspnMapType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // 設定 Spinner 的資料來源
        spnMapType.setAdapter(adapterspnMapType);

        // 設定 spnGeoPoint 元件 ItemSelected 事件的 listener
        spnMapType.setOnItemSelectedListener(spnMapTypeListener);

        // 利用 getSupportFragmentManager() 方法取得管理器
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        // 以非同步方式取得 GoogleMap 物件
        mapFragment.getMapAsync(this);
        // 取得介面元件
        Button btnHome=(Button)findViewById(R.id.btn);
        // 設定 button 的 Listener
        btnHome.setOnClickListener(btnHomeListener);


        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        position=bundle.getInt("POS");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);       // 一般地圖
        mMap.getUiSettings().setCompassEnabled(true);     // 顯示指南針
        mMap.getUiSettings().setZoomControlsEnabled(true);// 顯示縮放圖示
        LatLng locat = new LatLng(v[position], v1[position]);
        float zoom=17;
        mMap.addMarker(new MarkerOptions().position(locat).title("地點"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locat, zoom));

    }
    private Button.OnClickListener btnHomeListener = new Button.OnClickListener(){
        public void onClick(View v){
            finish();
        }
    };

    private Spinner.OnItemSelectedListener spnMapTypeListener=
            new Spinner.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    String sel=parent.getSelectedItem().toString();
                    switch (sel){
                        case "一般地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // 一般地圖
                            break;
                        case "混合地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // 混合地圖
                            break;
                        case "衛星地圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // 衛星地圖
                            break;
                        case "地形圖":
                            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);   // 地形圖
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // TODO Auto-generated method stub
                }
            };

}