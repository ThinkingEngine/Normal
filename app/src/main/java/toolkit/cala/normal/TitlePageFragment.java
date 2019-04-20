package toolkit.cala.normal;

import android.view.View;
import android.widget.TextView;


/**
 * package name:s.cala.androidcompent.model.fragments
 * create:cala
 * date:2019/1/14
 * commits:title fragment
 */
public class TitlePageFragment extends BaseFragment {


    private TextView tv;

    @Override
    public int getLayoutId() {
        return R.layout.title_page_layout;
    }

    @Override
    public void initView(View view) {
        tv = view.findViewById(R.id.tv_text);

        tv.setText("test");
    }

    @Override
    public void getData() {

    }
}
