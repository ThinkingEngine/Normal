package toolkit.cala.normal.widgets;

import android.os.Bundle;
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
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import toolkit.cala.normal.BaseActivity;
import toolkit.cala.normal.R;
import toolkit.cala.normal.utils.NetworkUtils;

/**
 * package name:toolkit.cala.normal.widgets
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BaseRefreshActivity<T> extends BaseActivity {
    private int AUTO_REFRESH_DELAY_TIME = 100;
    protected int DEFAULT_LIMIT = 10;
    protected int FIRST_PAGE = 1;

    protected int currentPage = FIRST_PAGE;

    protected boolean HAS_FOOTER = false;

    protected boolean IS_LOAD = true;


    private SmartRefreshLayout smartRefreshLayout;

    private RecyclerView recyclerView;

    private ClassicsHeader classicsHeader;

    private ClassicsFooter classicsFooter;

    private BaseQuickAdapter<T, BaseViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void getData(int page);

    protected abstract BaseQuickAdapter<T, BaseViewHolder> getAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        initView();
        initViews();

        initRecyclerView();
        initRefresh();

    }

    /**
     * 填充列表数据
     *
     * @param data 列表数据
     */
    protected void fillData(@Nullable List<T> data) {
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

    protected void initView() {
        smartRefreshLayout = findViewById(R.id.smartRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        classicsHeader = findViewById(R.id.classicsHeader);
        classicsFooter = findViewById(R.id.classicsFooter);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(initAdapter());
    }

    private BaseQuickAdapter<T, BaseViewHolder> initAdapter() {
        adapter = getAdapter();
        adapter.setEnableLoadMore(false);
        return adapter;
    }

    private void initRefresh() {
        classicsHeader.setEnableLastTime(false);
        classicsFooter.setEnabled(true);

        smartRefreshLayout.setEnableLoadmore(true);

        if (NetworkUtils.isNetConnected(this)) {
            smartRefreshLayout.autoRefresh(AUTO_REFRESH_DELAY_TIME);
        }


        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                currentPage = FIRST_PAGE;
                getData(FIRST_PAGE);
                refreshlayout.finishRefresh();
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

                if (IS_LOAD) {
                    currentPage++;
                    getData(currentPage);
                }
                smartRefreshLayout.finishLoadmore();
            }
        });

    }

    private void setData(@Nullable List<T> data, String emptyStr) {

        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadmore();

        if (data == null || data.size() == 0) {
            if (currentPage == FIRST_PAGE) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
                if (!HAS_FOOTER) {
                    if (!NetworkUtils.isNetConnected(this)) {
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

    public void autoRefresh() {
        smartRefreshLayout.autoRefresh();
    }

    //关闭下拉加载功能
    public void setCloseLoadMore(boolean b) {
        IS_LOAD = b;
        smartRefreshLayout.setEnableLoadmore(false);
    }

    /**
     * 加载空页面
     *
     * @param emptyStr 空状态提示文字
     */
    private View getEmptyView(String emptyStr) {
        View emptyView = LayoutInflater.from(this).inflate(R.layout.list_empty_layout, null);
        TextView tv = emptyView.findViewById(R.id.empty_contents);
        if (emptyStr.isEmpty()) {
            tv.setText("没有数据");
        } else {
            tv.setText(emptyStr);
        }
        return emptyView;
    }
}
