package group.wenjian.takeout;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import group.wenjian.takeout.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Branch> mBranches;
    public ActivityMainBinding mBinding;
    public ObservableField<String> mlocation = new ObservableField<>("正在定位中");
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    public int i = 0;
    private static final int BAIDU_READ_PHONE_STATE =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        initData();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setVariable(BR.mainActivity, this);

        mBinding.branchList.setLayoutManager(new LinearLayoutManager(this)); // 设置recycleview布局
        mBinding.branchList.setAdapter(new BranchAdapter(this)); // 为recycleview设置adapter

        dd.g.setBar(R.id.homeBar, this);
        getSupportActionBar().setTitle("选择店铺");
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerNotifyLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        startLocation();
    }

    private void startLocation(){

       if(Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
           Toast.makeText(this,"没有权限,请手动开启定位权限",Toast.LENGTH_SHORT).show();
           requestPermissions( new String[]{
                   Manifest.permission.ACCESS_FINE_LOCATION},BAIDU_READ_PHONE_STATE );
       }else{
           mLocationClient.start();
       }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case BAIDU_READ_PHONE_STATE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationClient.start();
                } else{
                    Toast.makeText(this,"没有权限,请手动开启定位权限",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    // 初始化数据
    private void initData(){
        try{
            JSONObject dd = new JSONObject(Model.dd);
            JSONArray branches = dd.getJSONArray("branches");
            mBranches = Branch.loader(branches);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 百度定位的回调函数
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location){

            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            String address = location.getAddrStr();                      //获取位置信息
            getLocation(locationDescribe,address);
        }
    }

    // 获取详细位置信息
    private void getLocation(String describe, String address){
        if(describe == null || address ==null){
            i++;
            mLocationClient.restart();
            if(i>=3){
                Toast.makeText(MainActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                mLocationClient.stop();
                return;
            }
        }else{
            mlocation.set(address+describe);
            mLocationClient.stop();
            Toast.makeText(MainActivity.this, "定位成功", Toast.LENGTH_SHORT).show();
        }
    }

    // 重新定位
    public void reStart(View view){
        Toast.makeText(MainActivity.this, "正在重新定位...", Toast.LENGTH_SHORT).show();
        mLocationClient.restart();
    }
}