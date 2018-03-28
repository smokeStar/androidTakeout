package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class Cookway extends BaseObservable implements Cloneable {

    private String tid;
    private String id;
    private String name;
    private double price;
    private boolean qty;
    private boolean selected;

    public Cookway(String tid, String id, String name, double price, boolean qty, boolean selected) {
        this.tid = tid;
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.selected = selected;
    }

    public static ArrayList<Cookway> load(JSONArray list){
        ArrayList<Cookway> cookways = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                String tid       = item.getString("tid");
                String id        = item.getString("id");
                String name      = item.getString("name");
                double price     = item.getDouble("price");
                boolean qty      = item.getBoolean("qty");

                Cookway c = new Cookway(tid,id,name,price,qty,false);
                cookways.add(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cookways;
    }

    // 浅复制
    @Override
    protected Object clone()  {
        Cookway cookway = null;
        try{
            cookway = (Cookway) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cookway;
    }

    public String getTid() {
        return tid;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isQty() {
        return qty;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQty(boolean qty) {
        this.qty = qty;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }
}
