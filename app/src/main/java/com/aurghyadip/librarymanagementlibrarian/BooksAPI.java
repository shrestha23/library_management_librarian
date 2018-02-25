package com.aurghyadip.librarymanagementlibrarian;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BooksAPI {
    private static final String url = "https://www.googleapis.com/";

    public static BooksService booksService = null;

    public static BooksService getBooksService() {
        if (booksService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            booksService = retrofit.create(BooksService.class);
        }
        return booksService;
    }

    public interface BooksService {
        @GET("/books/v1/volumes")
        Call<BookInfo> getBookInfo(@Query(value = "q", encoded = true) String query);
    }
}