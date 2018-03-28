package group.wenjian.takeout;

import android.databinding.BaseObservable;
import android.databinding.Bindable;


public class Payment extends BaseObservable {

    private int img_url;
    private String name;
    private String info;
    private boolean selected;

    public Payment(int img_url, String name, String info, boolean selected) {
        this.img_url = img_url;
        this.name = name;
        this.info = info;
        this.selected = selected;
    }

    @Bindable
    public int getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }
}
