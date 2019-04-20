package toolkit.cala.normal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import toolkit.cala.normal.utils.ActivityUtils;
import toolkit.cala.normal.utils.ToastUtils;

/**
 * package name:toolkit.cala.normal
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BaseFragment extends Fragment {

    protected Context context = getContext();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(context).inflate(getLayoutId(), null);

        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getData();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    /**
     * 获取当前layoutId
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView(View view);

    /**
     * 获取数据
     */
    public abstract void getData();

    /**
     * 跳转到指定页面
     */
    public void startActivity(BaseActivity activity) {
        ActivityUtils.startActivity(context, activity);
    }

    /**
     * 跳转到指定页面(携带参数)
     * <p>
     * val bundle = Bundle()
     * bundle.putString("id", id)
     * startActivity(bundle, TestActivity())
     * <p>
     * 接收参数：
     * val id: String = intent.getStringExtra("id")
     */
    public void startActivity(BaseActivity activity, Bundle bundle) {
        ActivityUtils.startActivity(context, activity, bundle);
    }

    /**
     * Toast短提示
     */
    public void showShortToast(String content) {
        if (content != null) {
            ToastUtils.showShortToast(context, content);
        }
    }

    /**
     * Toast长提示
     */
    public void showLongToast(String content) {
        if (content != null) {
            ToastUtils.showLongToast(context, content);
        }
    }

}
