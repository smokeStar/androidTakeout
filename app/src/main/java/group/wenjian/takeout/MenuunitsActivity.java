package group.wenjian.takeout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import group.wenjian.takeout.databinding.ActivityMenuunitsBinding;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class MenuunitsActivity extends AppCompatActivity{
    public Menu menu;
    public Menu xmenu;
    public ArrayList<Menuunit> menuunits = new ArrayList<>();
    public ArrayList<Menuunit> xmenuunits ;
    public ActivityMenuunitsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String id     = intent.getStringExtra("id");
        xmenu         = dd.g.getmenu(id);
        menu          = (Menu) xmenu.clone();
        xmenuunits    = dd.g.menuunitsMap.get(menu.getId());

        for (Menuunit xmenuunit : xmenuunits) {
            menuunits.add((Menuunit) xmenuunit.clone());
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menuunits);
        binding.setVariable(BR.menu, menu);
        binding.setVariable(BR.g, dd.g);
        binding.setVariable(BR.menuunitsActivity, this);

        binding.menuunits.setLayoutManager(new LinearLayoutManager(this));
        binding.menuunits.setAdapter(new MenuunitsAdapter(this));

        dd.g.setBar(R.id.menuunitsBar,this);        // 设置导航条
    }

    public void ordered(View view){

        // menu数量++
        int qty = xmenu.getQty();
        qty ++; xmenu.setQty(qty);

        // type数量++
        int typeQty = dd.g.getType(xmenu).getQty();
        typeQty ++; dd.g.getType(xmenu).setQty(typeQty);

        // order数量++
        for (Menu order : dd.g.orders) {
            if(order.getId().equals(menu.getId()) &&
                    order.getUnit().equals(order.getUnit())){
                int orderQty = order.getQty();
                orderQty ++; order.setQty(orderQty);
                finish(); return;
            }
        }
        menu.setQty(1);
        dd.g.orders.add(menu);
        finish();
    }


}
