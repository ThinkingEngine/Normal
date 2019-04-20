package toolkit.cala.normal.widgets;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import toolkit.cala.normal.BaseFragment;
import toolkit.cala.normal.R;
import toolkit.cala.normal.utils.NetworkUtils;

/**
 * package name:toolkit.cala.normal.widgets
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BaseRefreshFragment<T> extends BaseFragment {

    private int AUTO_REFRESH_DELAY_TIME = 100;
    protected int DEFAULT_LIMIT = 15;
    protected int FIRST_PAGE = 1;
    protected int currentPage = FIRST_PAGE;

    protected boolean HAS_FOOTER = false;

    protected SmartRefreshLayout smartRefreshLayout;
    protected RecyclerView recyclerView;
    protected ClassicsHeader classicsHeader;

    protected BaseQuickAdapter<T, BaseViewHolder> adapter = null;


    protected abstract void initViews(View view);

    protected abstract void getDatas(int page);

    protected abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

    @Override
    public void initView(View view) {

        smartRefreshLayout = view.findViewById(R.id.smartRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        classicsHeader = view.findViewById(R.id.classicsHeader);

        initViews(view);
        initRecyclerview();
        initRefresh();
    }

    @Override
    public void getData() {

    }

    /**
     * 填充列表数据
     *
     * @param data 列表数据
     */
    public void fillData(@Nullable List<T> data) {
        setData(data, "");
    }

    /**
     * 填充列表数据
     *
     * @param data 列表数据
     * @param
     */
    protected void fillData(@Nullable List<T> data, String emtpyStr) {
        setData(data, emtpyStr);
    }

    private void initRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        BaseQuickAdapter<T, BaseViewHolder> mAdapter = initAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private BaseQuickAdapter<T, BaseViewHolder> initAdapter() {
        adapter = getAdapter();
        return adapter;
    }

    private void initRefresh() {
        classicsHeader.setEnableLastTime(false);

        if (NetworkUtils.isNetConnected(getContext())) {
            smartRefreshLayout.autoRefresh(AUTO_REFRESH_DELAY_TIME);
        }

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currentPage = FIRST_PAGE;
                getDatas(FIRST_PAGE);
                refreshlayout.finishRefresh();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getDatas(currentPage);
                refreshlayout.finishLoadmore();
            }
        });
    }

    private void setData(@Nullable List<T> data, String emptyStr) {

        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();

        if (data == null || data.isEmpty()) {
            if (currentPage == FIRST_PAGE) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                if (!HAS_FOOTER) {
                    if (!NetworkUtils.isNetConnected(getContext())) {
                        adapter.setEmptyView(getEmptyView(""));
                    } else {
                        adapter.setEmptyView(getEmptyView(""));
                    }
                }

            } else {
                smartRefreshLayout.setLoadmoreFinished(true);
            }
            return;
        }

        if (currentPage == FIRST_PAGE) {
            adapter.setNewData(data);
        } else {
            adapter.addData(data);
        }

        if (data.size() < DEFAULT_LIMIT) {
            smartRefreshLayout.setLoadmoreFinished(true);
        }
    }

    /**
     * 加载空页面
     *
     * @param emptyStr 空状态提示文字
     */
    private View getEmptyView(String emptyStr) {
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.list_empty_layout, null);
        TextView tv = emptyView.findViewById(R.id.empty_contents);
        if (emptyStr.isEmpty()) {
            tv.setText("没有数据");
        } else {
            tv.setText(emptyStr);
        }
        return emptyView;
    }
}
