package toolkit.cala.normal;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends BaseActivity {

    private RadioButton rba;
    private RadioButton rbb;
    private RadioButton rbc;

    private RadioGroup radioGroup;

    private final FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;


    private Fragment cuurentFragment;

    private Fragment titleAFragment;
    private Fragment titleBFragment;
    private Fragment titleCFragment;

    private long exitTime = 0L;

    @Override
    protected int getLayoutId() {
        return R.layout.view_page_layout;
    }

    @Override
    protected void initView() {
        rba = findViewById(R.id.rbA);
        rbb = findViewById(R.id.rbB);
        rbc = findViewById(R.id.rbC);

        radioGroup = findViewById(R.id.radioGroupMain);

        titleAFragment = new TitlePageFragment();
        titleBFragment = new TitlePageFragment();
        titleCFragment = new TitlePageFragment();

        //默认选择首页
        rba.setChecked(true);
        replaceFragment(titleAFragment);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbA:
                        replaceFragment(titleAFragment);
                        break;
                    case R.id.rbB:
                        replaceFragment(titleBFragment);
                        break;
                    case R.id.rbC:
                        replaceFragment(titleCFragment);
                        break;
                    default:
                        replaceFragment(titleAFragment);
                }
            }
        });
    }

    @Override
    public void getData() {

    }

    /**
     * 连按两次后退键退出程序
     */

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showShortToast("再点一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                return super.onKeyDown(keyCode, event);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void replaceFragment(Fragment fragment) {
        fragmentTransaction = manager.beginTransaction();
        if (cuurentFragment == null) {
            fragmentTransaction.add(R.id.layout_main_content, fragment).commit();
        } else {
            if (!fragment.isAdded()) {
                fragmentTransaction.hide(cuurentFragment).add(R.id.layout_main_content, fragment).commit();
            } else {
                fragmentTransaction.hide(cuurentFragment).show(fragment).commit();
            }
        }
        cuurentFragment = fragment;
    }
}
