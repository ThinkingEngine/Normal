package toolkit.cala.normal.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toolkit.cala.normal.BuildConfig;
import toolkit.cala.normal.net.constant.BaseConstants;

/**
 * package name:toolkit.cala.normal.net
 * create:cala
 * date:2019/4/20
 * description:
 */
public class RetrofitManager {

    //设置超时时间
    private Retrofit mRetrofit;


    public RetrofitManager(String baseUrl) {


        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(BaseConstants.CONNECT_TIME, TimeUnit.SECONDS);//连接超时时间
        builder.readTimeout(BaseConstants.TIME_OUT, TimeUnit.SECONDS);//读操作超时时间

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }


        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }


    /**
     * 获取对应的Service
     *
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
