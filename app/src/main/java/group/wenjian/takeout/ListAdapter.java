package group.wenjian.takeout;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/13.
 */

public class ListAdapter extends BaseAdapter<Menuset> {

    private MenusetActivity menusetActivity;
    private ArrayList<Menuset> list;
    private int limit;
    private int subNum;

    public ListAdapter(Context context,int position) {

        super(context, R.layout.item_list, BR.list, BR.method);
        menusetActivity = (MenusetActivity) context;
        this.methods = new Methods();

        list   = menusetActivity.menu.getSubMenusets().get(position).getList();
        limit  = menusetActivity.menu.getSubMenusets().get(position).getQty();
        subNum = menusetActivity.menu.getSubMenusets().get(position).getNum();

        if (list.size() != 0) list.get(list.size()-1).setText("false");
        if (limit == 1 ) new Methods().chooseOne(list.get(0));
        for (Menuset mList: list) { mList.setQty(limit);}
        this.setList(list);
    }

    public class Methods{

        public void chooseOne(Menuset item){
            for (Menuset mList : list) {
                mList.setSelected(mList == item);
            }
        }
        public void add(Menuset mList, int sign){

            if(limit == 0){
                int num = mList.getNum();
                double price = menusetActivity.menu.getPrice();
                num += sign; price += sign * mList.getPrice();
                mList.setNum(num);
                menusetActivity.menu.setPrice(price);
            }else{
                int num = mList.getNum();
                if(num <= 0 && sign<0 ) return; // 防止手速过快
                if(subNum >= limit && sign>0){
                    Toast.makeText(menusetActivity, "不能超过"+limit+"项", Toast.LENGTH_SHORT).show();
                    return;
                }
                num += sign;subNum += sign;
                mList.setNum(num);
            }
        }
    }
}
