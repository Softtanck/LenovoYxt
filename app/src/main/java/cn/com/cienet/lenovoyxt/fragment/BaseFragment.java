package cn.com.cienet.lenovoyxt.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import cn.com.cienet.lenovoyxt.R;
import cn.com.cienet.lenovoyxt.Utils.DialogUtils;
import cn.com.cienet.lenovoyxt.activity.BaseActivity;
import cn.com.cienet.lenovoyxt.Utils.VolleyUtils;

/**
 * @author Tanck
 * @Description 所有碎片的基类
 * @date Jan 16, 2015 8:52:21 PM
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * 碎片基类
     */
    public BaseActivity holder;
    /**
     * 上下文
     */
    public Context context;
    /**
     * Volley工具类
     */
    public VolleyUtils volleyUtils;
    /**
     * gson工具
     */
    public Gson gson;

    /**
     * 对话框工具类
     */
    public DialogUtils dialogUtils;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        holder = (BaseActivity) activity;
        context = holder.getApplicationContext();

        dialogUtils = holder.dialogUtils;

        volleyUtils = holder.volleyUtils;

        gson = holder.gson;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutView(), container, false);
    }

    /**
     * 当视图被改变
     *
     * @param hidden
     */
//    public abstract void onHiddenChanged(boolean hidden);

    /**
     * 获取Fragment填充布局
     */
    protected abstract int getLayoutView();

    /**
     * 当View被创建完毕的时候
     *
     * @param view
     * @param savedInstanceState
     */
    public abstract void onViewCreated(View view, Bundle savedInstanceState);

    /**
     * 需要点击事件就直接重写onClick
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 替换视图
     *
     * @param id
     * @param fragment
     */
    public void replaceFragment(int id, Fragment fragment) {
        if (0 == id && null == fragment)
            throw new IllegalStateException(context.getString(R.string.param_is_non));
        FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
        transaction.replace(id, fragment).commit();
    }


    /**
     * 增加视图
     *
     * @param id
     * @param fragment
     */
    public void addFragment(int id, Fragment fragment) {
        if (0 == id && null == fragment)
            throw new IllegalStateException(context.getString(R.string.param_is_non));
        FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
        transaction.add(id, fragment).addToBackStack(null).commit();
    }


    /**
     * 隐藏一个视图
     *
     * @param fragment
     */
    public void hideFragment(Fragment fragment) {
        if (null == fragment)
            throw new IllegalStateException(context.getString(R.string.param_is_non));
        FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
        transaction.hide(fragment).commit();
    }

    /**
     * 展示一个视图
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (null == fragment)
            throw new IllegalStateException(context.getString(R.string.param_is_non));
        FragmentTransaction transaction = holder.fragmentManager.beginTransaction();
        transaction.show(fragment).commit();
    }
}
