package com.example.readwritedatawithroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private RoundContainer roundContainer;
    private List<Round> rounds;
    private ImageButton ib_simple, ib_try_limited, ib_time_limited, ib_one_try;
    RoundDao roundDao;
    List<User> users2 = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_simple = findViewById(R.id.ib_simple);
        ib_try_limited = findViewById(R.id.ib_try_limited);
        ib_time_limited = findViewById(R.id.ib_time_limited);
        ib_one_try = findViewById(R.id.ib_one_try);

        InputStream is = null;
        try {
            is = getAssets().open("rounds.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            roundContainer = gson.fromJson(reader, RoundContainer.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        rounds = roundContainer.getRounds();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "project_label").fallbackToDestructiveMigration().build();
        UserDao userDao = db.userDao();

//        User user = new User();
//        new UserAsyncTask(this, user, userDao).execute();

        int userCount = 4;
        List<User> users = new ArrayList<User>();
        for (int idx = 0; idx < userCount; idx++){
            User user = new User();
            users.add(user);
        }

        Runnable insertUsersRunnable = new Runnable() {
            @Override
            public void run() {
                if (users.size() != 0){
                    for (int idx = 0; idx < users.size(); idx++){
                        User user = users.get(idx);
                        userDao.insertAll(user);
                    }
                }
            }
        };

        Executor userExecutor = new UserExecutor();
        userExecutor.execute(insertUsersRunnable);

//        Runnable getUsersRunnable = new Runnable() {
//            @Override
//            public void run() {
//                users2 = userDao.getAll();
//            }
//        };
//
//        userExecutor.execute(getUsersRunnable);
//
//        for (int idx = 0; idx < users2.size(); idx++){
//            User newUser = users2.get(idx);
//            Log.d("MainActivity", Integer.toString(idx) + " : " + newUser.getName());
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}