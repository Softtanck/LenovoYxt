package cn.com.cienet.lenovoyxt.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cn.com.cienet.lenovoyxt.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/28/2015
 */
public class OneFragment extends BaseFragment {

    private TextView o;
    @Override
    protected int getLayoutView() {
        return R.layout.one;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        o = (TextView) view.findViewById(R.id.tv_one);
        o.setOnClickListener(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d("Tanck", "one--->" + hidden);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
//        showFragment();
    }
}
