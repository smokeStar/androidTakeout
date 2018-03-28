package group.wenjian.takeout;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class MenuunitsAdapter extends BaseAdapter<Menuunit> {

    MenuunitsActivity menuunitsActivity;
    ArrayList<Menuunit> menuunits;

    public MenuunitsAdapter(Context context) {

        super(context, R.layout.item_menuunits, BR.menuunit, BR.methods);
        menuunitsActivity = (MenuunitsActivity) context;
        this.methods = new Methods();
        menuunits = menuunitsActivity.menuunits;
        this.setList(menuunits);
        new Methods().chooseOne(menuunits.get(0));
        menuunits.get(menuunits.size()-1).setLast(true);
    }

    public class Methods{

        public void chooseOne(Menuunit menuunit){
            for (Menuunit mMenuunit : menuunits) {
                mMenuunit.setSelect(mMenuunit == menuunit);
                if(mMenuunit == menuunit){
                    menuunitsActivity.menu.setPrice(mMenuunit.getPrice());
                    menuunitsActivity.menu.setUnit(mMenuunit.getName());
                }
            }
        }
    }
}
