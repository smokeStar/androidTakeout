package group.wenjian.takeout;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableShort;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Observable;

import group.wenjian.takeout.databinding.ActivityPaymentBinding;

/**
 * Created by Sumdoo on 2018/3/21.
 */

public class PaymentActivity extends AppCompatActivity {

    public ActivityPaymentBinding binding;
    public ObservableField<String> name    = new ObservableField<>();
    public ObservableField<String> tel     = new ObservableField<>();
    public ObservableField<String> branch  = new ObservableField<>();
    public ObservableField<String> address = new ObservableField<>();
    public ObservableField<String> time    = new ObservableField<>();
    public ArrayList<Payment>      list    = new ArrayList<>();
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_payment);
        initData();
        binding.setVariable(BR.paymentActivity, this);
        binding.setVariable(BR.g, dd.g);


        dd.g.setBar(R.id.payBar, this);

        binding.imgList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.imgList.setAdapter(new imgAdapter(this));

        binding.payment.setLayoutManager(new LinearLayoutManager(this));
        binding.payment.setAdapter(new paymentAdapter(this));
    }

    public void initData(){
        list.add( new Payment(R.mipmap.wxpay  ,"微信支付"  ,"推荐使用微信支付"      ,false));
        list.add( new Payment(R.mipmap.rmb    ,"餐到付款"  ,"请准备好零钱,等待收款"  ,false));
        list.add( new Payment(R.mipmap.bankpay,"银行卡结账" ,"请准备好银行卡,等待刷卡",false));
        list.add( new Payment(R.mipmap.card   ,"会员卡结账" ,"请确保会员卡余额充足"   ,false));
    }


    public void pickTime(View view){
        new Thread(){
            String stime = null;
            @Override
            public void run() {
                super.run();
                stime = Utils.getTime();
                Message msg = handler.obtainMessage();
                msg.obj = stime;
                handler.sendMessage(msg);
            }
        }.start();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int    hour = 0;
                int    min  = 0;
                String stime = (String) msg.obj;
                if(time != null){
                    try {
                        hour = Integer.parseInt(stime.split(":")[0]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    try {
                        min = Integer.parseInt(stime.split(":")[1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                new TimePickerDialog(PaymentActivity.this,AlertDialog.THEME_HOLO_LIGHT,new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hour = hourOfDay+"";
                        String min  = minute+"";
                        if(hour.length() == 1) hour = "0"+hour;
                        if( min.length() == 1)  min = "0"+min;
                        time.set(hour + ":"+ min);
                    }

                },hour,min,true).show();
            }
        };
    }
    public void finish(View view){
        Intent intent = new Intent();
        intent.setClass(PaymentActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 清空栈中所有activity
        this.startActivity(intent);
    }
}
