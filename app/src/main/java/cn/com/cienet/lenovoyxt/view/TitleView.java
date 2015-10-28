package cn.com.cienet.lenovoyxt.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.cienet.lenovoyxt.R;
import cn.com.cienet.lenovoyxt.Utils.ScreenUtils;


/**
 * @author Tanck
 * @Description 自定义标题管理
 * @date Jan 19, 2015 11:24:26 AM
 */
public class TitleView extends FrameLayout {

    private TextView txTitle;
    private int defaultHeight;
    private LinearLayout layLeft, layRight;
    private TextView textLeft, textRight;
    private LinearLayout linearLayout;
    private static int menuId = Integer.MAX_VALUE / 2;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 组合控件
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        linearLayout = (LinearLayout) findViewById(R.id.linear1);
        txTitle = (TextView) findViewById(R.id.tx_title);
        layLeft = (LinearLayout) findViewById(R.id.layout_left);
        layRight = (LinearLayout) findViewById(R.id.layout_right);
        textLeft = (TextView) findViewById(R.id.text_left);
        textRight = (TextView) findViewById(R.id.text_right);
        defaultHeight = ScreenUtils.dip(context, 50);

        FrameLayout title_view_fly = (FrameLayout) findViewById(R.id.title_view_fly);
        LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, defaultHeight);
        title_view_fly.setLayoutParams(titleLp);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TitleView);
        String title = ta.getString(R.styleable.TitleView_mytitle);
        txTitle.setText(title);
        ta.recycle();
    }

    public void setTitleViewBackGround(int color) {
        linearLayout.setBackgroundColor(getResources().getColor(color));
    }

    public void setTitle(int textId) {
        txTitle.setText(textId);
    }

    public void setTitleColor(int color) {
        txTitle.setTextColor(color);
    }

    public TextView getTitle() {
        return this.txTitle;
    }

    public void setTitle(String text) {
        txTitle.setText(text);
    }

    public View addLeftDrawableMenu(Context context, int drawableId, int width,
                                    int height, OnClickListener onMenuClickListener) {
        View view = createDrawableMenu(context, drawableId, width, height,
                onMenuClickListener);
        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        int left = ScreenUtils.dip(getContext(), 10);
        int right = ScreenUtils.dip(getContext(), 0);
        view.setPadding(left, 0, right, 0);
        view.setLayoutParams(LP);
        layLeft.addView(view);
        layLeft.setOnClickListener(onMenuClickListener);
        setIdTagForMenu(view);
        return view;
    }

    /**
     * 标题左边结束当前activity按钮
     *
     * @param context
     * @return
     */
    public View addLeftDrawableFinish(final Activity context) {
        int size = ScreenUtils.dip(context, 10);
        View view = createDrawableMenu(context, R.drawable.btn_back, size,
                size, new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        context.finish();
                    }
                });
        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        int left = ScreenUtils.dip(getContext(), 10);
        int right = ScreenUtils.dip(getContext(), 20);
        view.setPadding(left, 0, right, 0);
        view.setLayoutParams(LP);
        layLeft.addView(view);
        setIdTagForMenu(view);
        return view;
    }

    public View addRightDrawableMenu(Context context, int drawableId,
                                     int width, int height, OnClickListener onMenuClickListener) {
        View view = createDrawableMenu(context, drawableId, width, height,
                onMenuClickListener);
        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        int left = ScreenUtils.dip(getContext(), 20);
        int right = ScreenUtils.dip(getContext(), 10);
        view.setPadding(left, 0, right, 0);
        view.setLayoutParams(LP);
        layRight.addView(view);
        setIdTagForMenu(view);
        return view;
    }

    public void addRightTextMenu(Context context, int textId, int paddings,
                                 OnClickListener onMenuClickListener) {
        Button button = createTextMenu(context, textId, paddings,
                onMenuClickListener);
        layRight.addView(button);
    }


    public void addLeftTextMenu(Context context, int textId, int paddings, OnClickListener onMenuClickListener) {
        Button button = createTextMenu(context, textId, paddings, onMenuClickListener);
        layLeft.addView(button);
    }

    public Button createTextMenu(Context context, int textId, int paddings,
                                 OnClickListener onMenuClickListener) {
        Button button = new Button(context);
        button.setText(textId);
        button.setTextSize(16);
        button.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams btnLP = null;

        button.setTextColor(Color.GRAY);
        button.setBackgroundColor(Color.TRANSPARENT);
        btnLP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paddings = ScreenUtils.dip(context, paddings);
        button.setPadding(paddings, paddings, paddings, paddings);
        button.setLayoutParams(btnLP);

        button.setOnClickListener(onMenuClickListener);
        return button;
    }

    /**
     * 在标题栏右侧加文字
     *
     * @param context
     * @param onMenuClickListener
     * @param text
     */
    public void addRightText(Activity context,
                             OnClickListener onMenuClickListener, String text) {
        textRight.setVisibility(View.VISIBLE);
        textRight.setOnClickListener(onMenuClickListener);
        textRight.setText(text);
    }

    // public void addRightTextMenu(Context context, int textId, int width, int
    // height, int margins,
    // OnClickListener onMenuClickListener) {
    // Button button = createTextMenu(context, textId, width, height, margins,
    // onMenuClickListener);
    // layRight.addView(button);
    // }

    public View addLeftMenu(Context context, View view,
                            OnClickListener onMenuClickListener) {
        view.setOnClickListener(onMenuClickListener);
        layLeft.addView(view);
        setIdTagForMenu(view);
        return view;
    }


    public View addRightMenu(Context context, View view,
                             OnClickListener onMenuClickListener) {
        view.setOnClickListener(onMenuClickListener);
        layRight.addView(view);
        setIdTagForMenu(view);
        return view;
    }

    public FrameLayout createDrawableMenu(Context context, int drawableId,
                                          int width, int height, OnClickListener onMenuClickListener) {
        FrameLayout layF = (FrameLayout) LayoutInflater.from(getContext())
                .inflate(R.layout.view_title_part_drawable_menu, null);
        ImageView imageView = (ImageView) layF.findViewById(R.id.imageView);
        imageView.setBackgroundResource(drawableId);
        LayoutParams btnLP = new LayoutParams(
                ScreenUtils.dip(context, width), ScreenUtils.dip(context, height));
        imageView.setLayoutParams(btnLP);
        btnLP.gravity = Gravity.CENTER_VERTICAL;
        layF.setOnClickListener(onMenuClickListener);
        return layF;
    }

    // public Button createTextMenu(Context context, int textId, int width, int
    // height, int margins,
    // OnClickListener onMenuClickListener) {
    // Button button = new Button(context);
    // button.setText(textId);
    // button.setTextSize(16);
    // button.setGravity(Gravity.CENTER);
    // button.setTextColor(Color.WHITE);
    //
    // LinearLayout.LayoutParams btnLP = new
    // LinearLayout.LayoutParams(ScreenUtils.dip(context, width), ScreenUtils.dip(
    // context, height));
    //
    // margins = ScreenUtils.dip(context, margins);
    // btnLP.setMargins(margins, margins, margins, margins);
    //
    // button.setLayoutParams(btnLP);
    //
    // button.setOnClickListener(onMenuClickListener);
    // return button;
    // }

    public void removeAllMenu() {
        removeAllLeftMenu();
        removeAllRightMenu();
    }

    public void removeAllLeftMenu() {
        layLeft.removeAllViews();
    }

    public void removeAllRightMenu() {
        layRight.removeAllViews();
    }

    private void setIdTagForMenu(View view) {
        view.setId(menuId);
        menuId++;
    }
}
