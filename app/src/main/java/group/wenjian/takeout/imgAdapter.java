package group.wenjian.takeout;

import android.content.Context;

/**
 * Created by Sumdoo on 2018/3/23.
 */

public class imgAdapter extends BaseAdapter<Menu>{

    public imgAdapter(Context context) {
        super(context,R.layout.item_img,BR.menu);
        System.out.println(dd.g.orders.size());
        this.setList(dd.g.orders);
    }
}
