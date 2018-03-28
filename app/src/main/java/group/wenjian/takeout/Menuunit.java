package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/20.
 */

public class Menuunit extends BaseObservable implements Cloneable{

    private String pid;
    private String id;
    private String name;
    private double price;
    private double pricer;
    private double pricem;
    private double pricerm;
    private boolean select;
    private boolean isLast;

    public Menuunit(String pid, String id, String name, double price, boolean isLast,
                    double pricer, double pricem, double pricerm, boolean select) {
        this.pid     = pid;
        this.id      = id;
        this.name    = name;
        this.price   = price;
        this.pricer  = pricer;
        this.pricem  = pricem;
        this.pricerm = pricerm;
        this.select  = select;
        this.isLast  = isLast;
    }

    public static ArrayList<Menuunit> load(JSONArray list){
        ArrayList<Menuunit> menuunits = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                String pid       = item.getString("pid");
                String id        = item.getString("id");
                String name      = item.getString("name");
                double price     = item.getDouble("price");
                double pricer    = item.getDouble("pricer");
                double pricem    = item.getDouble("pricem");
                double pricerm   = item.getDouble("pricerm");

                Menuunit m = new Menuunit(pid,id,name,price,false,pricer,pricem,pricerm,false);
                menuunits.add(m);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return menuunits;
    }

    // 浅复制
    @Override
    protected Object clone()  {
        Menuunit menuunit = null;
        try{
            menuunit = (Menuunit)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return menuunit;
    }

    public String getPid() {
        return pid;
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

    public double getPricer() {
        return pricer;
    }

    public double getPricem() {
        return pricem;
    }

    public double getPricerm() {
        return pricerm;
    }

    @Bindable
    public boolean isSelect() {
        return select;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public void setPricer(double pricer) {
        this.pricer = pricer;
    }

    public void setPricem(double pricem) {
        this.pricem = pricem;
    }

    public void setPricerm(double pricerm) {
        this.pricerm = pricerm;
    }

    public void setSelect(boolean select) {
        this.select = select;
        notifyPropertyChanged(BR.select);
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
