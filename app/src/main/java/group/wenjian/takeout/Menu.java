package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/1.
 */

public class Menu extends BaseObservable implements Cloneable  {

    // 浅复制
    @Override
    protected Object clone()  {
        Menu menu = null;
        try{
            menu = (Menu)super.clone();

        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        ArrayList<Menuset> xsubMenus = new ArrayList<>();
        for (Menuset subMenu : subMenus) {
            xsubMenus.add((Menuset) subMenu.clone()) ;
        }
        menu.setSubMenus(new ArrayList<Menuset>());
        menu.setSubMenus(xsubMenus);

        ArrayList<Menuset> xsubMenuset = new ArrayList<>();
        for (Menuset subMenuset : subMenusets) {
//            ArrayList<Menuset> xlist = new ArrayList<>(); // 这样会造成list没有克隆
//            for (Menuset list : subMenuset.getList()) {
//                xlist.add((Menuset) list.clone());
//            }
//            subMenuset.setList(new ArrayList<Menuset>());
//            subMenuset.setList(xlist);
            xsubMenuset.add((Menuset) subMenuset.clone());
        }
        menu.setSubMenusets(new ArrayList<Menuset>());
        menu.setSubMenusets(xsubMenuset);

        for (Menuset subMenuset : menu.getSubMenusets()) {
            ArrayList<Menuset> xlist = new ArrayList<>();
            for (Menuset list : subMenuset.getList()) {
                xlist.add((Menuset) list.clone());
            }
            subMenuset.setList(new ArrayList<Menuset>());
            subMenuset.setList(xlist);
        }
        return menu;
    }


    private String pid;
    private String tid;
    private String id;
    private String name;
    private String spell;
    private String code;
    private String text;
    private String unit;
    private double price;
    private double pricer;
    private double pricem;
    private double pricerm;
    private Boolean isnull;
    private Boolean byseanson;
    private Boolean ismenuset;
    private int isdisc;
    private String pic;
    private boolean show;
    private int qty;
    private ArrayList<Menuset> subMenusets;
    private ArrayList<Menuset> subMenus;
    private String content;

    public Menu(String pid, String tid, String id, String name, String spell, String code,
                String text, String unit, double price, double pricer, double pricem,
                double pricerm, Boolean isnull, Boolean byseanson, Boolean ismenuset,
                int isdisc, String pic, boolean show, int qty, String content,
                ArrayList<Menuset> subMenusets,ArrayList<Menuset> subMenus) {
        this.pid = pid;
        this.tid = tid;
        this.id = id;
        this.name = name;
        this.spell = spell;
        this.code = code;
        this.text = text;
        this.unit = unit;
        this.price = price;
        this.pricer = pricer;
        this.pricem = pricem;
        this.pricerm = pricerm;
        this.isnull = isnull;
        this.byseanson = byseanson;
        this.ismenuset = ismenuset;
        this.isdisc = isdisc;
        this.pic = pic;
        this.show = show;
        this.qty = qty;
        this.subMenus = subMenus;
        this.subMenusets = subMenusets;
        this.content = content;
    }

    public static ArrayList<Menu> load(JSONArray list){
        ArrayList<Menu> menus = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                String pid       = item.getString("pid");
                String tid       = item.getString("tid");
                String id        = item.getString("id");
                String name        = item.getString("name");
                String spell     = item.getString("spell");
                String code      = item.getString("code");
                String text      = item.getString("text");
                String unit      = item.getString("unit");
                double price     = item.getDouble("price");
                double pricer    = item.getDouble("pricer");
                double pricem    = item.getDouble("pricem");
                double pricerm   = item.getDouble("pricerm");
                boolean isnull   = item.getBoolean("isnull");
                boolean byseanson = item.getBoolean("byseanson");
                boolean ismenuset = item.getBoolean("ismenuset");
                int isdisc        = item.getInt("isdisc");
                String pic        = item.getString("pic");
                boolean show      = false;
                int qty           = 0;
                ArrayList<Menuset> subMenusets = new ArrayList<>();
                ArrayList<Menuset> subMenus = new ArrayList<>();
                Menu m = new Menu(pid,tid,id,name,spell,code,text,unit,price,pricer,pricem,pricerm,isnull,byseanson,ismenuset,isdisc,pic,show,qty,"",subMenusets,subMenus );
                menus.add(m);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return menus;
        }

    public String getPid() {
        return pid;
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

    public String getSpell() {
        return spell;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public String getUnit() {
        return unit;
    }

    @Bindable
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

    public Boolean getIsnull() {
        return isnull;
    }

    public Boolean getByseanson() {
        return byseanson;
    }

    public Boolean getIsmenuset() {
        return ismenuset;
    }

    public int getIsdisc() {
        return isdisc;
    }

    public String getPic() {
        return pic;
    }
    @Bindable
    public boolean isShow() {
        return show;
    }
    @Bindable
    public int getQty() {
        return qty;
    }

    public String getContent() {
        return content;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
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

    public void setIsnull(Boolean isnull) {
        this.isnull = isnull;
    }

    public void setByseanson(Boolean byseanson) {
        this.byseanson = byseanson;
    }

    public void setIsmenuset(Boolean ismenuset) {
        this.ismenuset = ismenuset;
    }

    public void setIsdisc(int isdisc) {
        this.isdisc = isdisc;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setShow(boolean show) {
        this.show = show;
        notifyPropertyChanged(BR.show);
    }

    public void setQty(int qty) {
        this.qty = qty;
        dd.g.setTotalQty(); // 手动set totalQty属性
        dd.g.setTotalFee(); // 手动set totalFee属性
        notifyPropertyChanged(BR.qty);
    }

    public ArrayList<Menuset> getSubMenusets() {
        return subMenusets;
    }

    public ArrayList<Menuset> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(ArrayList<Menuset> subMenus) {
        this.subMenus = subMenus;
    }

    public void setSubMenusets(ArrayList<Menuset> subMenusets) {
        this.subMenusets = subMenusets;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
