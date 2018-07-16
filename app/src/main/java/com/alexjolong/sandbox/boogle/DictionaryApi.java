package com.alexjolong.sandbox.boogle;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.converter.gson.GsonConverterFactory;


public class DictionaryApi {
    public static final String API_URL = "https://od-api.oxforddictionaries.com/api/v1";

    public static class Word {
        public final String word;
        public final String lang;

        public Word(String word, String lang) {
            this.word = word;
            this.lang = lang;
        }
    }

    public interface GetWord {
        @GET("/inflections/{source_lang}/{word_id}")
        Call<List<Word>> word(
            @Path("source_lang") String lang,
            @Path("word_id") String word);
    }


    public static void main(String... args) throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        DictionaryApi dictapi = retrofit.create(DictionaryApi.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Word>> call = dictapi.word("swimming", "en");

        // Fetch and print a list of the contributors to the library.
        List<Word> wordResponse = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }

}
