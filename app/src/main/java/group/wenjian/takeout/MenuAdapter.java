package group.wenjian.takeout;

import android.content.Intent;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter<Menu>{

    HomeActivity home;

    public MenuAdapter(HomeActivity home) {
        super(home, R.layout.item_menu, group.wenjian.takeout.BR.menu, BR.methods );
        this.home = home;
        this.methods = new Methods();
    }

    // 显示相应的菜品
    public void setType(Type type){
        this.setList(dd.g.getMenus(type));
        home.binding.menuList.scrollToPosition(0);
    }

    public class Methods{
        public void setMenu(Menu menu, int num) {
            // 套餐
            if(menu.getIsmenuset() && num > 0){
                Intent intent = new Intent();
                intent.putExtra("id",menu.getId());
                intent.setClass(home, MenusetActivity.class);
                home.startActivity(intent);return;
            }
            // 多单位
            ArrayList<Menuunit> menuunits = dd.g.menuunitsMap.get(menu.getId());
            if(menuunits!=null && menuunits.size()>0 && num>0){
                Intent intent = new Intent();
                intent.putExtra("id",menu.getId());
                intent.setClass(home, MenuunitsActivity.class);
                home.startActivity(intent);return;
            }
            // 做法
            ArrayList<Cookway> cookways = dd.g.cookwaysMap.get(menu.getTid());
            ArrayList<Cookway> cookwayx = dd.g.cookwaysMap.get("M"+ menu.getId());
            if((cookways!=null && cookways.size()>0 && num>0)
               ||(cookwayx!=null && cookwayx.size()>0 && num>0)){
                Intent intent = new Intent();
                intent.putExtra("id",menu.getId());
                intent.setClass(home, CookwayActivity.class);
                home.startActivity(intent);return;
            }
            // 单品
            Menu mOrder = (Menu) menu.clone(); // 浅拷贝一份order并初始化qty
            mOrder.setQty(0);

            int qty = menu.getQty();
            if(qty <= 0 && num < 0 ) return;
            qty += num;
            menu.setQty(qty);

            Type type = dd.g.getType(menu);
            int typeQty = type.getQty();
            typeQty += num;
            type.setQty(typeQty);

            HomeActivity.showBottom.set(dd.g.getTotalQty() > 0);

            if(dd.g.orders.size()==0)dd.g.orders.add(mOrder);
            for(int i = 0; i<dd.g.orders.size(); i++){
                Menu order = dd.g.orders.get(i);
                if(menu.getId().equals(order.getId())){
                    int orderQty = order.getQty();
                    orderQty += num;
                    if(orderQty <= 0 ) dd.g.orders.remove(i);
                    order.setQty(orderQty);
                    return;
                }
            }
            mOrder.setQty(1);
            dd.g.orders.add(mOrder);
        }
    }
}
