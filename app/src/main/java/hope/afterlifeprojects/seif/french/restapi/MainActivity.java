package hope.afterlifeprojects.seif.french.restapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final TextView textViewss =findViewById(R.id.post_title_tv);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


         ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<List <Post>> call = apiInterface.getPost("3");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                textViewss.setText(response.body().get(0).getTitle());

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewss.setText(t.getMessage());
            }
        });


             /*   call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                textViewss.setText(response.body().getTitle());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewss.setText(t.getMessage());
                Toast.makeText(MainActivity.this,
                        "Your Message", Toast.LENGTH_LONG).show();
            }
        }); */
    }
}
