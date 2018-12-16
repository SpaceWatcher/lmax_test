package tserr.testtask;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class MathService {

    private MathJsApi mathApi;
    private static MathService instance = new MathService();

    public static MathService getInstance() {
        return instance;
    }

    public MathService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.mathjs.org/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mathApi = retrofit.create(MathJsApi.class);
    }

    public void checkPrime(NumEvent event) throws IOException {
        Call<String> call = mathApi.expression("isPrime(" + event.getValue() + ")");
        String response = call.execute().body();
        boolean isPrime =  Boolean.parseBoolean(response);
        event.setPrime(isPrime);
    }
}
