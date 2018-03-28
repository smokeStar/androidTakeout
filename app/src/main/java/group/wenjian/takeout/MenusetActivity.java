package group.wenjian.takeout;

import android.content.Intent;
import android.database.Observable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import group.wenjian.takeout.databinding.ActivityMenusetBinding;

/**
 * Created by Sumdoo on 2018/3/13.
 */

public class MenusetActivity extends AppCompatActivity {
    private ActivityMenusetBinding binding;
    public Menu menu;
    public Menu xmenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 接受上个activity的数据
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        xmenu = dd.g.getmenu(id);
        menu = (Menu) xmenu.clone(); // 深度拷贝,即拷贝类对象中的类对象

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menuset);
        binding.setVariable(BR.g, dd.g);
        binding.setVariable(BR.menu, menu);
        binding.setVariable(BR.menusetActivity, this);

        binding.submenuslist.setLayoutManager(new LinearLayoutManager(this));
        binding.submenuslist.setAdapter(new SubmenusAdapter(this));

        binding.submenusets.setLayoutManager(new LinearLayoutManager(this));
        binding.submenusets.setAdapter(new SubmenusetAdapter(this));

        // 设置导航条
        dd.g.setBar(R.id.menusetBar,this);

    }


    public void ordered(View view){

        String content = "";        // 套餐内容
        int totalQty = 0;           // 套餐必须选择一项

        for (Menuset subMenu : this.menu.getSubMenus()) {
            content += subMenu.getName()+"、";
        }

        for (Menuset subMenuset : this.menu.getSubMenusets()) {

            if(subMenuset.getQty() == 1){   // 单项选择
                for (Menuset list : subMenuset.getList()) {
                    if(list.isSelected()){
                        content += list.getName()+"、";
                        totalQty ++;
                    }
                }
            }else if(subMenuset.getQty() == 0){ // 任选
                for (Menuset list : subMenuset.getList()) {
                    totalQty += list.getNum();
                    if(list.getNum()>=1) content += list.getName()+"、";
                }
            }else{                              // 多选
                int should = 0;
                for (Menuset list : subMenuset.getList()) {
                    should += list.getNum();
                    totalQty+=list.getNum();
                    if(list.getNum()>=1) content += list.getName()+"、";
                }
                if(should < subMenuset.getQty()){
                    Toast.makeText(this, subMenuset.getName()+"至少要选"+subMenuset.getQty()+"项", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        // 任选的时候必须选择一项
        if(menu.getSubMenusets().size() != 0 && totalQty == 0){
            Toast.makeText(this,"至少要选一项", Toast.LENGTH_SHORT).show();return;
        }

        // menu 数量+
        int qty = xmenu.getQty();qty++;
        xmenu.setQty(qty);

        // type 数量+
        Type type = dd.g.getType(xmenu);
        int typeQty = type.getQty(); typeQty++;
        type.setQty(typeQty);

        // 初始化order
        menu.setQty(0); menu.setContent(content);

        // order存在数量+1, 不存在新增
        for (Menu order : dd.g.orders) {
            if(order.getContent().equals(menu.getContent()) && order.getId().equals(menu.getId())){
                int orderQty = order.getQty();
                orderQty ++;order.setQty(orderQty);
                finish();return;
            }
        }
        menu.setQty(1);dd.g.orders.add(menu);finish();
    }
}
