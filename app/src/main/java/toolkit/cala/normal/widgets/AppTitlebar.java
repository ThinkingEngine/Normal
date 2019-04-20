package toolkit.cala.normal.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import toolkit.cala.normal.R;

/**
 * package name:toolkit.cala.normal.widgets
 * create:cala
 * date:2019/4/20
 * description:
 */
public class AppTitlebar extends FrameLayout {

    /**
     * 页面标题
     */
    private TextView tvTitle;
    /**
     * 右边可操作图标
     */
    private ImageView ivRightAction;
    /**
     * 右边可操作文字
     */
    private TextView tvRightAction;


    private OnViewClickListener onFinishClickListener;
    private OnViewClickListener onRightImageClickListener;
    private OnViewClickListener onRightTxtClickListener;


    public AppTitlebar(Context context) {
        super(context);
    }

    public AppTitlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AppTitlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        ImageView ivFinishPage = contentView.findViewById(R.id.ivFinishPager);
        tvTitle = contentView.findViewById(R.id.tvPagerTitle);
        ivRightAction = contentView.findViewById(R.id.ivRightImageAction);
        tvRightAction = contentView.findViewById(R.id.tvRightAction);
        View viewDivide = contentView.findViewById(R.id.viewDivide);


        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.titleBar);
        String title = array.getString(R.styleable.titleBar_title);
        int titleSize = array.getDimensionPixelSize(R.styleable.titleBar_title_size, 16);
        int titleColor = array.getResourceId(R.styleable.titleBar_title_color, -1);
        Boolean isShowFinish = array.getBoolean(R.styleable.titleBar_isShowReturn, true);
        Boolean isShowDivide = array.getBoolean(R.styleable.titleBar_isShowDivide, true);
        int rightImgResId = array.getResourceId(R.styleable.titleBar_rightImageRes, -1);
        String rightTxt = array.getString(R.styleable.titleBar_rightTxtString);
        int rightTxtColor = array.getResourceId(R.styleable.titleBar_rightTxtColor, -1);

        array.recycle();

        //设置页面标题
        if (tvTitle != null) {
            tvTitle.setText(title);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);
            if (titleColor != -1) {
                tvTitle.setTextColor(getResources().getColor(titleColor));
            }
        }

        /*
         * 返回按钮相关
         */
        if (ivFinishPage != null) {
            //设置是否显示返回按钮
            ivFinishPage.setVisibility(isShowFinish ? View.VISIBLE : View.INVISIBLE);
            //设置返回事件的点击事件
            ivFinishPage.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFinishClickListener != null) {
                        onFinishClickListener.onClick();
                    }
                }
            });

        }

        /*
         * 右边图标相关
         */
        if (ivRightAction != null) {
            //设置(-1为默认值，不显示图标)
            if (rightImgResId != -1) {
                ivRightAction.setVisibility(View.VISIBLE);
                ivRightAction.setImageResource(rightImgResId);
            }

            ivRightAction.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRightImageClickListener != null) {
                        onRightImageClickListener.onClick();
                    }
                }
            });

        }

        //设置是否显示分割线
        viewDivide.setVisibility(isShowDivide ? View.VISIBLE : View.INVISIBLE);

        /*
         * 右边文字相关
         */
        if (tvRightAction != null) {
            tvRightAction.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRightTxtClickListener != null) {
                        onRightTxtClickListener.onClick();
                    }
                }
            });

            //文字文本
            if (rightTxt != null) {
                tvRightAction.setVisibility(View.VISIBLE);
                tvRightAction.setText(rightTxt);
            }

            //右边文字颜色(颜色一定要抽取到color.xml下)
            if (rightTxtColor != -1) {
                tvRightAction.setVisibility(View.VISIBLE);
                tvRightAction.setTextColor(getResources().getColor(rightTxtColor));
            }
        }
    }

    /**
     * 设置页面标题
     */
    public AppTitlebar setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    /**
     * 设置返回事件
     */
    public AppTitlebar setFinishClickListener(OnViewClickListener clickListener) {
        this.onFinishClickListener = clickListener;
        return this;
    }

    /**
     * 设置右边图标点击事件
     */
    public AppTitlebar setRightImageClickListener(OnViewClickListener clickListener) {
        this.onRightImageClickListener = clickListener;
        return this;
    }

    /**
     * 设置右边文字显示隐藏状态
     */
    public AppTitlebar setRightTxtVisibility(int visibility) {
        if (tvRightAction != null) {
            tvRightAction.setVisibility(visibility);
        }
        return this;
    }

    /**
     * 设置右边文字点击事件
     */
    public AppTitlebar setRightTxtClickListener(OnViewClickListener clickListener) {
        this.onRightTxtClickListener = clickListener;
        return this;
    }
}
