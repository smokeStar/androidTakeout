package group.wenjian.takeout;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/13.
 */

public class SubmenusAdapter extends BaseAdapter<Menuset> {

    MenusetActivity menusetActivity;

    public SubmenusAdapter(Context context) {

        super(context, R.layout.item_submenu, BR.submenus);
        menusetActivity = (MenusetActivity) context;
        ArrayList<Menuset> subMenus = menusetActivity.menu.getSubMenus();
        // 设置一个标志让最后一项不显示border
        if (subMenus.size()!=0) subMenus.get(subMenus.size()-1).setText("false");
        this.setList(subMenus);
    }
}
