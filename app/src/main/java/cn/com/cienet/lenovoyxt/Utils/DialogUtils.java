package cn.com.cienet.lenovoyxt.Utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

import cn.com.cienet.lenovoyxt.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 10/28/2015
 */
public class DialogUtils {

    /**
     * 注意这里的Context需要与生命周期绑定.
     */
    private Context context;

    /**
     * 对话框建立者
     */
    private AlertDialog.Builder builder;

    /**
     * 对话框
     */
    private AlertDialog dialog;

    public DialogUtils(Context context) {
        this.context = context;
        builder = getBuilder();
    }

    private AlertDialog.Builder getBuilder() {
        if (null == builder) {
            builder = new AlertDialog.Builder(context);
        }
        return builder;
    }


    /**
     * 展示一个加载对话框
     *
     * @param isCancel
     */
    public void showLoading(boolean isCancel) {
        builder = getBuilder();
        builder.setView(View.inflate(context, R.layout.view_dialog_loading, null));
        builder.setCancelable(isCancel);
        dialog = builder.show();
    }

    /**
     * 消失一个对话框
     */
    public void dismiss() {
        if (null != dialog) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
