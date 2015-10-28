package cn.com.cienet.lenovoyxt.activity;

import cn.com.cienet.lenovoyxt.R;
import cn.com.cienet.lenovoyxt.fragment.OneFragment;
import cn.com.cienet.lenovoyxt.fragment.TwoFragment;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/28/2015
 */
public class TestActivity extends BaseActivity {

    private OneFragment oneFragment;

    private TwoFragment twoFragment;


    @Override
    protected int getViewId() {
        return R.layout.test;
    }

    @Override
    protected void onActivityCreate() {

        initView();

    }

    private void initView() {

        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        addFragment(R.id.fl_content, oneFragment);
        addFragment(R.id.fl_content, twoFragment);
        hideFragment(twoFragment);
//        showFragment(oneFragment);
    }
}
