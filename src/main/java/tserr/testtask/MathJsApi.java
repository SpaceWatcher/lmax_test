package tserr.testtask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MathJsApi {

    @GET("v1/")
    Call<String> expression(@Query("expr") String query);
}
