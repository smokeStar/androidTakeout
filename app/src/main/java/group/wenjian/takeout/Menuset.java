package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Sumdoo on 2018/3/13.
 */

public class Menuset extends BaseObservable implements Cloneable{

    private String pid;
    private String tid;
    private String id;
    private String name;
    private String text;
    private String unit;
    private int    qty;
    private double price;
    private boolean isnull;
    private boolean ismenuset;
    private boolean selected;
    private int num;
    private String info;
    private ArrayList<Menuset> list;


    public Menuset(String pid, String tid, String id, String name, String text,
                   String unit, int qty, double price, boolean isnull,boolean selected,
                   int num,boolean ismenuset,ArrayList<Menuset> list,String info ) {
        this.pid = pid;
        this.tid = tid;
        this.id = id;
        this.name = name;
        this.text = text;
        this.unit = unit;
        this.qty = qty;
        this.price = price;
        this.isnull = isnull;
        this.ismenuset = ismenuset;
        this.selected = selected;
        this.list = list;
        this.info = info;
        this.num = num;

    }

    public static ArrayList<Menuset> load(JSONArray list){
        ArrayList<Menuset> menusets = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                JSONObject item     = list.getJSONObject(i);
                String pid          = item.getString("pid");
                String tid          = item.getString("tid");
                String id           = item.getString("id");
                String name         = item.getString("name");
                String text         = item.getString("text");
                String unit         = item.getString("unit");
                int     qty         = item.getInt("qty");
                double price        = item.getDouble("price");
                boolean isnull      = item.getBoolean("isnull");
                boolean ismenuset   = item.getBoolean("ismenuset");
                ArrayList<Menuset> mlist = new ArrayList<>();
                Menuset mt = new Menuset(pid,tid,id,name,text,unit,qty,price,isnull,false,0,ismenuset,mlist,"");
                menusets.add(mt);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return menusets;
    }

    // 浅复制
    @Override
    protected Object clone()  {
        Menuset menuset = null;
        try{
            menuset = (Menuset)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return menuset;
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

    public String getText() {
        return text;
    }

    public String getUnit() {
        return unit;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public boolean isnull() {
        return isnull;
    }

    public boolean ismenuset() {
        return ismenuset;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }
    @Bindable
    public int getNum() {
        return num;
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

    public void setText(String text) {
        this.text = text;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIsnull(boolean isnull) {
        this.isnull = isnull;
    }

    public void setIsmenuset(boolean ismenuset) {
        this.ismenuset = ismenuset;
    }

    public ArrayList<Menuset> getList() {
        return list;
    }

    public void setList(ArrayList<Menuset> list) {
        this.list = list;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public void setNum(int num) {
        this.num = num;
        notifyPropertyChanged(BR.num);
    }

}
