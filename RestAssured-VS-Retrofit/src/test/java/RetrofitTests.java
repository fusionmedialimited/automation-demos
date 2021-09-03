import POJOs.Pet;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RetrofitTests {


    @Test
    public void createPet_test() throws IOException {
        // create the payload for the request
        Pet payload = new Pet();

        payload.setId(2);
        payload.setName("Tom");
        payload.setCategory(new Pet.Category(2,"cat"));
        payload.setStatus("available");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CreatePetService service = retrofit.create(CreatePetService.class);
        Call<okhttp3.ResponseBody> createPet = service.createPet(payload);
        Response<okhttp3.ResponseBody> response = createPet.execute();

        String responseBody = Objects.requireNonNull(response.body().string());

        // get the values from the response
        int id = JsonPath.read(responseBody, "id");
        String name = JsonPath.read(responseBody, "name");
        String status = JsonPath.read(responseBody, "status");

        // assert that the values in the response are matching the values specified in the payload
        assertThat(id, equalTo(2));
        assertThat(name, equalTo("Tom"));
        assertThat(status, equalTo("available"));
    }

    interface CreatePetService {
        @POST("pet")
        Call<okhttp3.ResponseBody> createPet(@Body Pet payload);
    }


    @Test
    public void getCreatedPet_test() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://petstore.swagger.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InfoPetService service = retrofit.create(InfoPetService.class);
        Call<okhttp3.ResponseBody> petInfo = service.getPetInfo();
        Response<okhttp3.ResponseBody> response = petInfo.execute();

        String responseBody = Objects.requireNonNull(response.body().string());

        // get the values from the response
        int id = JsonPath.read(responseBody, "id");
        String name = JsonPath.read(responseBody, "name");
        String status = JsonPath.read(responseBody, "status");

        // assert the values
        assertThat(id, equalTo(2));
        assertThat(name, equalTo("Tom"));
        assertThat(status, equalTo("available"));
    }

    interface InfoPetService {
        @GET("pet/2")
        Call<okhttp3.ResponseBody> getPetInfo();
    }

}
