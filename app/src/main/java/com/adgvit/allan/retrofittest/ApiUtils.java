package com.adgvit.allan.retrofittest;

import com.adgvit.allan.retrofittest.data.remote.RetrofitClient;
import com.adgvit.allan.retrofittest.data.remote.Service;

/**
 * Created by Allan on 27-07-2017.
 */

public class ApiUtils {

    public static Service getService() {
        return RetrofitClient.getClient().create(Service.class);
    }
}
