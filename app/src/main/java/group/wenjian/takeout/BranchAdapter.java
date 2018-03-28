package group.wenjian.takeout;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Sumdoo on 2018/3/1.
 */

public class BranchAdapter extends BaseAdapter<Branch> {

    private MainActivity main;

    public BranchAdapter(Context context) {
        super(context,R.layout.item_branch,BR.branch,BR.methods);
        this.methods = new Methods();
        main = (MainActivity) context;
        this.setList(main.mBranches);
    }

    public class Methods{

        public void go() {
            Intent intent = new Intent();
            intent.setClass(main, HomeActivity.class);
            main.startActivity(intent);
        }
        public void onItemClick(Branch branch) {
            for (Branch mBranch : main.mBranches) {
                mBranch.setSelected(branch == mBranch);
            }
        }
    }

}
