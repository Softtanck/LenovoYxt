package cn.com.cienet.lenovoyxt;

import android.app.Application;

import com.android.volley.toolbox.Volley;

import cn.com.cienet.lenovoyxt.Utils.ScreenUtils;
import cn.com.cienet.lenovoyxt.Utils.VolleyUtils;


/**
 * @author Tanck
 * @Description TODO 初始化应用程序数据
 * @date Jan 16, 2015 5:50:13 PM
 */
public class App extends Application {

    private static App instance;

    public VolleyUtils volleyUtils;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        CV.mScreenWidth = ScreenUtils.getScreenWidth(this);
        CV.mScreenHeight = ScreenUtils.getScreenHeight(this);
    }

    /**
     * 初始化数据
     */
    private void init() {
//        CrashHandler.init(this);//异常博获器
        volleyUtils = VolleyUtils.init(Volley.newRequestQueue(this), this);//Volley初始化
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActivityContainer.finishAll();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // TODO 对map降低引用,低内存下适应.
    }

    // TODO SharedPreferences的相关设置

}
