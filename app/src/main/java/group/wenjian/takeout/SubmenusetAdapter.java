package group.wenjian.takeout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/13.
 */

public class SubmenusetAdapter extends BaseAdapter<Menuset> {

    public RecyclerView list;
    private MenusetActivity menusetActivity;

    public SubmenusetAdapter(Context context) {

        super(context, R.layout.item_submenusets, BR.submenuset);
        menusetActivity = (MenusetActivity) context;

        ArrayList<Menuset> subMenusets = menusetActivity.menu.getSubMenusets();

        // 设置一个标志让最后一项不显示border
        if (subMenusets.size()!=0) subMenusets.get(subMenusets.size()-1).setText("false");
        this.setList(subMenusets);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        list = (RecyclerView) holder.itemView.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(menusetActivity));
        list.setAdapter(new ListAdapter(menusetActivity,position));
    }
}
