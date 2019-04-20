package toolkit.cala.normal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * package name:toolkit.cala.normal.utils
 * create:cala
 * date:2019/4/20
 * description:
 */
public class ActivityUtils {
    /**
     * 跳转到指定Activity。
     */
    public static void startActivity(Context context, Activity activity) {
        context.startActivity(new Intent(context, activity.getClass()));
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
    public static void startActivity(Context context, Activity activity, Bundle bundle) {
        Intent intent = new Intent(context, activity.getClass());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
