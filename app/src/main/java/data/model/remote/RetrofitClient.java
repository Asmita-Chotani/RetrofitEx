package data.model.remote;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Allan on 25-07-2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl().addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
