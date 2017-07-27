package com.adgvit.allan.retrofittest;

import com.adgvit.allan.retrofittest.data.remote.RetrofitClient;
import com.adgvit.allan.retrofittest.data.remote.Service;

/**
 * Created by Allan on 27-07-2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://young-ravine-50082.herokuapp.com/";

    public static Service getService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}
