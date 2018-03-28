package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Type extends BaseObservable{

    private String pid;
    private String id;
    private String name;
    private int qty;
    private boolean choose;


    public Type(String pid, String id, String name, int qty, boolean choose) {
        this.pid = pid;
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.choose = choose;

    }

    public static ArrayList<Type> load(JSONArray list){
        ArrayList<Type> types = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                JSONObject item = list.getJSONObject(i);
                String pid  = item.getString("pid");
                String id   = item.getString("id");
                String name = item.getString("name");
                boolean choose = false;
                if(i == 0){
                    choose = true;
                }
                Type t = new Type(pid,id,name,0,choose);
                types.add(t);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return types;
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
    @Bindable
    public boolean isChoose() {
        return choose;
    }

    @Bindable
    public int getQty() {
        return qty;
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

    public void setQty(int qty) {
        if (this.qty == qty) return;
            this.qty =  qty;
        notifyPropertyChanged(BR.qty);
    }

    public void setChoose(boolean choose) {
        this.choose = choose;
        notifyPropertyChanged(BR.choose);
    }
}
