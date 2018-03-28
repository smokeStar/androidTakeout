package group.wenjian.takeout;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class CookwayAdapter extends BaseAdapter<Cookway> {
    public CookwayActivity cookwayActivity;
    public ArrayList<Cookway> cookways;

    public CookwayAdapter(Context context) {

        super(context, R.layout.item_cookway, BR.cookway, BR.methods);
        cookwayActivity = (CookwayActivity) context;
        cookways = cookwayActivity.cookways;
        this.setList(cookways);
        this.methods = new Methods();
    }

    public class Methods{

        public void add(Cookway cookway){
            cookway.setSelected(!cookway.isSelected());
            double price   = cookwayActivity.menu.getPrice();
            if(cookway.isSelected()){
                price   += cookway.getPrice();
            }else{
                price  -= cookway.getPrice();
            }
            cookwayActivity.menu.setPrice(price);
        }
    }
}
