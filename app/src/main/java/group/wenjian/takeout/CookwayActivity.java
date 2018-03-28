package group.wenjian.takeout;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import group.wenjian.takeout.databinding.ActivityCookwayBinding;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class CookwayActivity extends AppCompatActivity {
    public Menu xmenu;
    public Menu menu;
    public ActivityCookwayBinding binding;
    public ArrayList<Cookway> cookways  = new ArrayList<>();
    public ArrayList<Cookway> xcookways = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String id     = intent.getStringExtra("id");
        xmenu         = dd.g.getmenu(id);
        menu          = (Menu) xmenu.clone(); // 复制一份,不影响基础数据

        ArrayList<Cookway>   c        = dd.g.cookwaysMap.get(menu.getTid());
        ArrayList<Cookway>   cookwayx = dd.g.cookwaysMap.get("M"+ menu.getId());
        if( c != null)       xcookways.addAll(c);
        if(cookwayx != null) xcookways.addAll(cookwayx);

        for (Cookway xcookway : xcookways) {
            cookways.add((Cookway)xcookway.clone());         // 复制一份,不影响基础数据
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_cookway);
        binding.setVariable(BR.menu, menu);
        binding.setVariable(BR.g, dd.g);
        binding.setVariable(BR.cookwayActivity, this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3); // 设置三列
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;                        // 设置每个item占一列
            }
        });
        binding.cookway.setLayoutManager(gridLayoutManager);
        binding.cookway.setAdapter(new CookwayAdapter(this));
        dd.g.setBar(R.id.cookwayBar, this);        // 设置导航条
    }

    public void ordered(View view){

        String content = menu.getContent();

        for (Cookway cookway : cookways) {
            if(cookway.isSelected()) content += cookway.getName()+"、";
        }
        menu.setContent(content);

        if(content.equals("")){
            Toast.makeText(this, "至少选择一种做法", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = xmenu.getQty();
        qty ++; xmenu.setQty(qty);

        int typeQty = dd.g.getType(xmenu).getQty();
        typeQty ++; dd.g.getType(xmenu).setQty(typeQty);

        for (Menu order : dd.g.orders) {
            if(order.getId().equals(menu.getId()) &&
                    order.getContent().equals(menu.getContent())){

                int orderQty = order.getQty();
                orderQty ++;
                order.setQty(orderQty);
                finish();return;
            }
        }
        menu.setQty(1);
        dd.g.orders.add(menu);finish();
    }
}
