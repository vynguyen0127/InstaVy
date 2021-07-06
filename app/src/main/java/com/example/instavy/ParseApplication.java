package com.example.instavy;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ohAdDnc8RV5dx6qpudkRVJ9ielXOjar3XlYXMSpF")
                .clientKey("QzthLigb2Xu4JFVVItVoBuoVvj1Ca2F3YOCqUG0N")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
