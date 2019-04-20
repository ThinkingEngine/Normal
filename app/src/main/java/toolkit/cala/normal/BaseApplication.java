package toolkit.cala.normal;

import android.app.Application;
import android.content.Context;

/**
 * package name:toolkit.cala.normal
 * create:cala
 * date:2019/4/20
 * description:
 */
public abstract class BaseApplication extends Application {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = this;
    }
}
