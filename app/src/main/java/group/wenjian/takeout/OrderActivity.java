package group.wenjian.takeout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import group.wenjian.takeout.databinding.ActivityOrderBinding;

/**
 * Created by Sumdoo on 2018/3/10.
 */

public class OrderActivity extends AppCompatActivity {

    public ActivityOrderBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);
        binding.setVariable(BR.g, dd.g);
        binding.setVariable(BR.orderActivity, this);
        binding.orderList.setLayoutManager(new LinearLayoutManager(this));
        binding.orderList.setAdapter(new OrderAdapter(this));

        dd.g.setBar(R.id.orderBar,this);        // 设置导航条
    }

    public void ordered(View view){
        Intent intent = new Intent();
        intent.setClass(OrderActivity.this, PaymentActivity.class);
        startActivity(intent);
    }
}
