package group.wenjian.takeout;

import android.content.Context;

/**
 * Created by Sumdoo on 2018/3/10.
 */

public class OrderAdapter extends BaseAdapter<Menu> {

    OrderActivity orderActivity;

    public OrderAdapter(Context context) {
        super(context, R.layout.item_order, BR.order, BR.methods); // 构造orderAdapter
        this.orderActivity = (OrderActivity) context;
        this.methods = new Methods();
        this.setList(dd.g.orders);

    }

    public class Methods{
        public void setOrder(Menu order, int num){

            int index = dd.g.orders.indexOf(order);
            int qty = order.getQty();
            qty += num;
            if(qty <= 0){
                dd.g.orders.remove(index);
                notifyItemRemoved(index);       // 因为orders并没有被观察,所以必须手动刷新
                if(dd.g.orders.size() == 0) orderActivity.finish();
            }
            order.setQty(qty);

            dd.g.getMenu(order).setQty(qty);

            int typeQty = dd.g.getType(order).getQty();
            typeQty += num;
            dd.g.getType(order).setQty(typeQty);
        }



    }
}
