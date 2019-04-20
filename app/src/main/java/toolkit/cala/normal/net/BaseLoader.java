package toolkit.cala.normal.net;

import toolkit.cala.normal.net.constant.APIAdress;

/**
 * package name:toolkit.cala.normal.net
 * create:cala
 * date:2019/4/20
 * description:
 */
public class BaseLoader extends RetrofitManager {

    public static String getBaseUrl() {
        return APIAdress.USER_INFO_URL;
    }

    public BaseLoader(){
        this(getBaseUrl());
    }

    public BaseLoader(String baseUrl) {
        super(baseUrl);
    }

    //设置请求基础信息
    public BaseLoader setBaseRequest(BaseRequest request) {

        return this;
    }
}
