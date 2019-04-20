package toolkit.cala.normal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import toolkit.cala.normal.STATUS;

/**
 * package name:toolkit.cala.normal.utils
 * create:cala
 * date:2019/4/20
 * description:
 */
public class NetworkUtils {
    public static int getNetworkState(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return STATUS.NETWORK_WIFI;
            } else if (networkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return STATUS.NETWORK_MOBILE;
            }
        } else {
            return STATUS.NETWORK_NONE;
        }

        return STATUS.NETWORK_NONE;
    }

    /**
     * 网络是否连接
     */
    public static boolean isNetConnected(Context context) {
        return getNetworkState(context) != STATUS.NETWORK_NONE;
    }
}
