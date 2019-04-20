package toolkit.cala.normal.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import toolkit.cala.normal.BuildConfig;

/**
 * package name:toolkit.cala.normal.net
 * create:cala
 * date:2019/4/20
 * description:
 */
public class RetrofitManager {

    //设置超时时间
    private int mConnectTimeout = 45;
    private int mReadTimeout = 45;
    private Retrofit mRetrofit;


    public RetrofitManager(String baseUrl) {


        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(mConnectTimeout, TimeUnit.SECONDS);//连接超时时间
        builder.readTimeout(mReadTimeout, TimeUnit.SECONDS);//读操作超时时间

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

    public void setConnectTimeout(int connectTimeout) {
        mConnectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        mReadTimeout = readTimeout;
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
