package com.lyp.app.SlidingView;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.lyp.app.R;

/**
 * Created by lyp on 2015/4/12.
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWrapper = null;
    private ViewGroup mMenu = null;   //菜单
    private ViewGroup mContent = null; //内容

    private int mRightMargin = 50;
    private int mMenuWidth ;//菜单的宽度

    private int mScreenWidth ;//屏幕宽度

    private boolean mearsureflag = false;
    private boolean isOpen = false;


    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mRightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyStlyeSliding,defStyle,0);
        mRightMargin = (int)a.getDimensionPixelSize(R.styleable.MyStlyeSliding_mRightMargin, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
        a.recycle();

        //获取屏幕的宽度
        WindowManager mag = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Display dis = mag.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics() ;
        dis.getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:

                int scrollX = getScrollX();
                if(!isOpen) {
                    if (scrollX >= mMenuWidth / 2) {
                        this.smoothScrollTo(mMenuWidth, 0);
                        isOpen = false;
                    } else {
                        this.smoothScrollTo(0, 0);
                        isOpen = true;
                    }
                }else{
                    if (scrollX >= mMenuWidth ) {
                        this.smoothScrollTo(mMenuWidth, 0);
                        isOpen = false;
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(isOpen){
                    isOpen =false;
                }


        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(!mearsureflag){
            mWrapper = (LinearLayout)this.getChildAt(0);
            mMenu =(ViewGroup)mWrapper.getChildAt(0);
            mContent =(ViewGroup)mWrapper.getChildAt(1);
            mMenuWidth =  mMenu.getLayoutParams().width = mScreenWidth - mRightMargin;
            mContent.getLayoutParams().width = mScreenWidth ;
            mearsureflag = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(changed){
            this.scrollTo(mMenuWidth,0);  //初始化布局至内容起始点
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //设置滑动显示特效
        double scale = l*1.0/mMenuWidth;//0~1

        double contentScale = 0.8+0.2*scale;

        double menuScale = 0.7+0.3*(1-scale);
        double menuAlpha = 0.3+0.7*(1-scale);

        mMenu.setAlpha((float)menuAlpha);
        mMenu.setScaleX((float)menuScale);
        mMenu.setScaleY((float)menuScale);
        mMenu.setTranslationX((float)(1.5*mMenuWidth*scale));
        mContent.setScaleY((float)contentScale);
    }
}
