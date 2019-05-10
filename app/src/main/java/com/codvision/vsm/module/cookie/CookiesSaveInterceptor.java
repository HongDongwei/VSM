package com.codvision.vsm.module.cookie;

import com.codvision.vsm.App;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class CookiesSaveInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            String header =originalResponse.header("Set-Cookie");
            SharedPreferenceUtils.setString(App.app,"cookiess",header);
        }
        return originalResponse;
    }

}
