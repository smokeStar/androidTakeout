package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sumdoo on 2018/3/1.
 */

public class Branch extends BaseObservable{

    private String Longitude;
    private String Latitude;
    private String BranchID;
    private String Address;
    private String City;
    private String Phone;
    private String BranchName;
    private String StoreName;
    private int TakeoutStop;
    private boolean Selected;

    public static ArrayList<Branch> loader(JSONArray list){
        ArrayList<Branch> branches = new ArrayList<>();
        try{
            for(int i = 0; i<list.length(); i++){
                Branch b = new Branch();
                JSONObject item = list.getJSONObject(i);
                b.Longitude  = item.getString("Longitude");
                b.Latitude  = item.getString("Latitude");
                b.BranchID  = item.getString("BranchID");
                b.Address  = item.getString("Address");
                b.City  = item.getString("City");
                b.Phone  = item.getString("Phone");
                b.BranchName  = item.getString("BranchName");
                b.StoreName  = item.getString("StoreName");
                b.TakeoutStop  = item.getInt("TakeoutStop");
                b.Selected = i==0;
                branches.add(b);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return branches;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getBranchID() {
        return BranchID;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getPhone() {
        return Phone;
    }

    public String getBranchName() {
        return BranchName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public int getTakeoutStop() {
        return TakeoutStop;
    }

    @Bindable
    public boolean isSelected() {
        return Selected;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setBranchID(String branchID) {
        BranchID = branchID;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public void setTakeoutStop(int takeoutStop) {
        TakeoutStop = takeoutStop;
    }

    public void setSelected(boolean selected) {
        Selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }
}
