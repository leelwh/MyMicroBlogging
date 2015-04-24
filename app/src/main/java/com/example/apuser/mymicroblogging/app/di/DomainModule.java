package com.example.apuser.mymicroblogging.app.di;

import android.app.Application;
import android.util.Log;

import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusRepository;
import com.example.apuser.mymicroblogging.domain.repository.api.retrofit.RetrofitStatusService;
import com.mobprofs.retrofit.converters.SimpleXmlConverter;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by apuser on 4/23/15.
 */
@Module(
        complete = false,
        library = true
)
public class DomainModule {
    static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static final String PRODUCTION_API_URL = "http://yamba.marakana.com/api/";
    class MyErrorHandler implements ErrorHandler {
        @Override public Throwable handleError(RetrofitError cause) {
            retrofit.client.Response r = cause.getResponse();
            if (r != null && r.getStatus() == 401) {
                return new Exception(cause);
            }
            return cause;
        }
    }
    static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();

        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(app.getCacheDir(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (IOException e) {
        }
        client.setAuthenticator(new Authenticator() {
            @Override
            public Request authenticate(Proxy proxy, Response response) throws IOException {
                String credential = Credentials.basic("student", "password");
                return response.request().newBuilder()
                        .header("Authorization", credential)
                        .build();
            }

            @Override
            public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                return null;
            }
        });
        return client;
    }

    @Provides @Singleton ErrorHandler provideErrorHandler() {
        return new MyErrorHandler();
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app);
    }

    @Provides @Singleton
    Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides @Singleton Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint(PRODUCTION_API_URL);
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, ErrorHandler errorHandler) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setErrorHandler(errorHandler)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new SimpleXmlConverter())
                .setEndpoint(endpoint)
                .build();
    }

    @Provides @Singleton
    RetrofitStatusService provideRetrofitStatusService(RestAdapter restAdapter) {
        return restAdapter.create(RetrofitStatusService.class);
    }

    @Provides
    RetrofitStatusRepository provideRetrofitStatusRepository(RetrofitStatusService service) {
        return new RetrofitStatusRepository(service);
    }
}
