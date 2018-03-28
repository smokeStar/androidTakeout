package group.wenjian.takeout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import group.wenjian.takeout.databinding.ActivityHomeBinding;

public class HomeActivity  extends AppCompatActivity {

    public ActivityHomeBinding binding;
    public TypeAdapter typeAdapter;
    public MenuAdapter menuAdapter;
    public static ObservableBoolean showBottom = new ObservableBoolean(false); // 模块级变量

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setVariable(BR.g, dd.g);

        // 为菜品类别列表绑定数据,设置布局
        typeAdapter = new TypeAdapter(this);
        binding.typeList.setLayoutManager(new LinearLayoutManager(this));
        binding.typeList.setAdapter(typeAdapter);
        binding.typeList.scrollTo(0,0);

        menuAdapter = new MenuAdapter(this);
        binding.menuList.setLayoutManager(new LinearLayoutManager(this));
        binding.menuList.setAdapter(menuAdapter);

        menuAdapter.setType(typeAdapter.getItem(0));

        dd.g.setBar(R.id.bar,this); // 设置导航栏
    }

    public void goOrder(View view){
        Intent intent = new Intent();
        intent.setClass(HomeActivity.this, OrderActivity.class);
        startActivity(intent);
    }

}
