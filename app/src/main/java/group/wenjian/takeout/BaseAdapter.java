package group.wenjian.takeout;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<T> mList;
    private int layoutId;
    private int varId;
    private int handlerId;
    public Object methods;

    public BaseAdapter(Context context, int layoutId, int varId, int ...handlerId) {
        this.layoutId = layoutId;
        this.varId = varId;
        if(handlerId.length !=0){
            this.handlerId = handlerId[0];
        }else{
            this.handlerId = 0;
        }
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mList = new ArrayList<>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        binding = DataBindingUtil.inflate(mLayoutInflater,layoutId , parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final T m = mList.get(position);
        holder.getBinding().setVariable(varId,m);
        if( handlerId != 0 ) holder.getBinding().setVariable(handlerId,methods);
        holder.getBinding().executePendingBindings(); // 立即执行变更
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public T getItem(int position){
        return mList.get(position);
    }

    public void addAll(List<T> list){
        mList.addAll(list);
    }
    public List<T> getList(){
        return mList;
    }

    public void setList(List<T> list){
        mList = new ArrayList<>();
        notifyDataSetChanged();
        mList = list;
        notifyDataSetChanged();
    }

    public class BaseViewHolder<VDB extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private VDB  mBinding;

        public BaseViewHolder( VDB Binding) {
            super(Binding.getRoot());
            this.mBinding = Binding;
        }
        public VDB getBinding() {
            return mBinding;
        }
    }
}
