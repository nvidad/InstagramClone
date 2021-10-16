package com.nvidad.instagramclone;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your Parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SsquoymD6ZB4OAhA0ExIKzahaotTcjPUORFQyjyS")
                .clientKey("TR2Gk7HOSzISKQoJxQrvZuUkbutDFZ056sRD1ldi")
                .server("https://parseapi.back4app.com")
                .build()
        );


    }
}
