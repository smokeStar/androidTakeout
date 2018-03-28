package group.wenjian.takeout;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Sumdoo on 2018/3/24.
 */

public class paymentAdapter extends BaseAdapter<Payment> {

    public PaymentActivity paymentActivity;

    public paymentAdapter(Context context) {
        super(context, R.layout.item_payment, BR.payment, BR.methods);
        this.methods    = new Methods();
        paymentActivity = (PaymentActivity) context;
        this.setList(paymentActivity.list);
        new Methods().choose(paymentActivity.list.get(0));
    }

//    @BindingAdapter("android:src")
//    public static void setSrc(ImageView view, Bitmap bitmap) {
//        view.setImageBitmap(bitmap);
//    }
//    @BindingAdapter("android:src")
//    public static void setSrc(ImageView view, int resId) {
//        view.setImageResource(resId);
//    }
//    @BindingAdapter({"app:imageUrl"})
//    public static void loadImage(ImageView imageView, String url) {
//        Glide.with(imageView.getContext())
//                .load(url)
//                .into(imageView);
//    }

    public class Methods{

        public void choose(Payment payment){
            for (Payment payment1 : paymentActivity.list) {
                payment1.setSelected(payment1 == payment);
            }
        }
    }
}
