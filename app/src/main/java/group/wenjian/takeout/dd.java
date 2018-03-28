package group.wenjian.takeout;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class dd extends BaseObservable{

    public ArrayList<Type> types;
    public ArrayList<Menu> menus;
    public ArrayList<Menu> orders;
    public ArrayList<Menuset> menusets;
    public ArrayList<Menuunit> menuunits;
    public ArrayList<Cookway> cookways;
    public Map<String,ArrayList<Menuunit>> menuunitsMap;
    public Map<String,ArrayList<Cookway>> cookwaysMap;

    public double totalFee ;
    public int totalQty;

    public static dd g = new dd(); // 单实例全局数据

    // 数据的初始化
    public dd() {
        try{
            JSONObject obj = new JSONObject(Model.dd);
            this.types = Type.load(obj.getJSONArray("types"));
            this.menus = Menu.load(obj.getJSONArray("menus"));
            this.menusets = Menuset.load(obj.getJSONArray("menusets"));
            this.menuunits = Menuunit.load(obj.getJSONArray("menuunits"));
            this.cookways = Cookway.load(obj.getJSONArray("cookways"));
            this.totalFee = 0;
            this.totalQty = 0;
            this.orders = new ArrayList<>();
            this.menuunitsMap = new HashMap<>();
            this.cookwaysMap = new HashMap<>();
            loadMenuset();
            loadMenuunits();
            loadCookways();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 通过menu获取type
    public  Type getType(Menu menu){
        for( Type type : this.types ){
            if(type.getId().equals(menu.getPid())){
                return type;
            }
        }
        return null;
    }

    // 通过type获得menus
    public  ArrayList<Menu> getMenus(Type type){

        ArrayList<Menu> list = new ArrayList<>();
        for(Menu menu : this.menus){
            if(menu.getPid().equals(type.getId())) {
                list.add(menu);
            }
        }
        return list;
    }

    // 通过订单获得menu
    public Menu getMenu(Menu order){
        for( Menu menu:this.menus ){
            if(order.getId().equals(menu.getId())){
                return menu;
            }
        }
        return null;
    }

    // 通过id获得menu
    public Menu getmenu(String id){
        for( Menu menu:this.menus ){
            if(id.equals(menu.getId())){
                return menu;
            }
        }
        return null;
    }

    // 返回上一个activity
    public void finish(AppCompatActivity activity){
        activity.finish();
    }

    // 设置toolbar
    public void setBar(int id, final AppCompatActivity activity){
        Toolbar homeBar =  (Toolbar) activity.findViewById(id);
        activity.setSupportActionBar(homeBar);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 设置箭头
        activity.getSupportActionBar().setTitle("微信点餐");
        homeBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    // 初始化套餐
    private void loadMenuset(){

        Map<String, ArrayList<Menuset>> map = new HashMap<>();

        for (Menuset menuset : this.menusets) {
            if(map.get(menuset.getPid()) != null){
                map.get(menuset.getPid()).add(menuset);
            }else{
                ArrayList<Menuset> list = new ArrayList<>();
                list.add(menuset);
                map.put(menuset.getPid(),list);
            }
        }

        for (Menu menu : this.menus) {

            ArrayList<Menuset> menusets = map.get(menu.getId());
            if(menusets == null) continue;  // 如果为null, 跳过这项

            for( Menuset item :menusets ){
                if(!item.ismenuset()){
                    menu.getSubMenus().add(item);
                }else{
                    item.setList(map.get(item.getId()));
                    if(item.getQty() < 0){
                        item.setInfo("(全选)");
                    }else if(item.getQty() == 0){
                        item.setInfo("(任选)");
                    }else{
                        String info ="("+ item.getList().size()+"选"+item.getQty()+")";
                        item.setInfo(info);
                    }
                    menu.getSubMenusets().add(item);
                }
            }
        }
    }

    // 多单位
    private void loadMenuunits(){
        for (Menuunit menuunit : this.menuunits) {
            if(this.menuunitsMap.get(menuunit.getPid())!= null){
                this.menuunitsMap.get(menuunit.getPid()).add(menuunit);
            }else{
                ArrayList<Menuunit> list = new ArrayList<>();
                list.add(menuunit);
                this.menuunitsMap.put(menuunit.getPid(), list);
            }
        }
    }

    // 做法
    private void loadCookways(){
        for (Cookway cookway : this.cookways) {
            if(this.cookwaysMap.get(cookway.getTid())!= null){
                this.cookwaysMap.get(cookway.getTid()).add(cookway);
            }else{
                ArrayList<Cookway> list = new ArrayList<>();
                list.add(cookway);
                this.cookwaysMap.put(cookway.getTid(), list);
            }
        }
    }


    // 全局的计算属性的实现
    @Bindable
    public int getTotalQty() {
        int mTotal = 0;                  // 当手动setter以后,会调用此方法, 进而自动得到totalQty;
        for( Menu order:orders ){
            int qty = order.getQty();
            mTotal += qty;
        }
        totalQty = mTotal;
        return totalQty;
    }

    public void setTotalQty() {
        notifyPropertyChanged(BR.totalQty);
    }

    @Bindable
    public double getTotalFee() {
        double mTotalFee = 0;               // 当手动setter以后,会调用此方法, 进而自动得到totalFee;
        for( Menu order:orders ){
            int qty = order.getQty();
            double price = order.getPrice();
            mTotalFee += qty*price;
        }
        totalFee = mTotalFee;
        return totalFee;
    }

    public void setTotalFee() {
        notifyPropertyChanged(BR.totalFee);
    }
}
