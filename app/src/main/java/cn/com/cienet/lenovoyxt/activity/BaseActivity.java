package cn.com.cienet.lenovoyxt.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import cn.com.cienet.lenovoyxt.ActivityContainer;
import cn.com.cienet.lenovoyxt.App;
import cn.com.cienet.lenovoyxt.CV;
import cn.com.cienet.lenovoyxt.R;
import cn.com.cienet.lenovoyxt.Utils.DialogUtils;
import cn.com.cienet.lenovoyxt.view.TitleView;
import cn.com.cienet.lenovoyxt.Utils.VolleyUtils;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * @author Tanck
 * @Description TODO 所有Activity的基类
 * @date Jan 16, 2015 5:20:57 PM
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {
    /**
     * 标题
     */
    public TitleView titleView;
    /**
     * 视图填充器
     */
    public LayoutInflater inflater;
    /**
     * Fragment管理
     */
    public FragmentManager fragmentManager;
    /**
     * json数据帮助类
     */
    public Gson gson;
    /**
     * Volley网络工具
     */
    public VolleyUtils volleyUtils;
    /**
     * 上下文
     */
    public Context context;

    /**
     * 对话框工具类
     */
    public DialogUtils dialogUtils;

    /**
     * Fragment处理
     */
    private FragmentTransaction fragmentTransaction;


    private SwipeBackLayout mSwipeBackLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());

        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        fragmentManager = getSupportFragmentManager();
        context = getApplicationContext();
        volleyUtils = App.getInstance().volleyUtils;
        gson = new Gson();
        dialogUtils = new DialogUtils(BaseActivity.this);
        titleView = (TitleView) findViewById(R.id.comm_titleView);

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
//        saveTrackingMode(edgeFlag);

        ActivityContainer.addActivity(this);

        onActivityCreate();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityContainer.finishActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 如果需要点击就直接重写该方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    /**
     * 打印一个Toast
     *
     * @param textId
     */
    public void showToast(int textId) {
        showToast(getString(textId));
    }

    /**
     * 打印一个Toast
     *
     * @param text
     */
    public void showToast(String text) {
        View view = inflater.inflate(R.layout.toast_message, null);
        ((TextView) view.findViewById(R.id.tx_message)).setText(text);
        Toast mToast = new Toast(getApplicationContext());
        mToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 100);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }

    /**
     * 设置布局展示的View
     */
    protected abstract int getViewId();

    /**
     * 初始化布局控件
     */
    protected abstract void onActivityCreate();

    /**
     * 增加布局
     *
     * @param fragment
     */
    public void addFragment(int id, Fragment fragment) {
        if (0 < id && null != fragment) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(id, fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    /**
     * 隐藏布局
     *
     * @param fragment
     */
    public void hideFragment(Fragment fragment) {
        if (null != fragment) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.hide(fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * 展示视图
     *
     * @param fragment
     */
    public void showFragment(Fragment fragment) {
        if (null != fragment) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    /**
     * Activity带参数跳转
     *
     * @param activity
     * @param bundle
     */
    public void changeActivityWithBundle(Class<?> activity, Bundle bundle) {
        if (null == bundle)
            throw new IllegalArgumentException(context.getString(R.string.param_is_non));
        Intent intent = new Intent(this, activity);
        intent.putExtra(CV.FUNCTION_TYPE_TAG, bundle);
        startActivity(intent);
    }

    /**
     * Activity跳转,不带参数
     *
     * @param activity
     */
    public void changeAc(Class<?> activity) {
        Intent intent = new Intent(context, activity);
        startActivity(intent);
    }
}
