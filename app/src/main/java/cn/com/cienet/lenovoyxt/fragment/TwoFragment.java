package cn.com.cienet.lenovoyxt.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import cn.com.cienet.lenovoyxt.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/28/2015
 */
public class TwoFragment extends BaseFragment {
    @Override
    protected int getLayoutView() {
        return R.layout.two;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("Tanck", "two--->" + hidden);
    }
}
