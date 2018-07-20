package com.alexjolong.sandbox.boogle;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.converter.gson.GsonConverterFactory;

public class DictionaryApi {
    public static final String API_URL = "https://od-api.oxforddictionaries.com/api/v1";

    public interface DictionaryApiInterface {
        @GET("/inflections/{source_lang}/{word_id}")
        Call<Lemmatron> getWord(
            @Path("source_lang") String lang,
            @Path("word_id") String word );
    }


    public static void main(String... args) throws IOException {
        // Create a REST adapter which points the dictionary.com API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DictionaryApiInterface dictApiInt = retrofit.create(DictionaryApiInterface.class);

        Lemmatron responseLemmatron = dictApiInt.getWord("swimming", "en").execute().body();

        // print out the response to verify
        Result lemmaRes = responseLemmatron.getResults().get(0);
        System.out.println(lemmaRes.getWord());
    }

}
