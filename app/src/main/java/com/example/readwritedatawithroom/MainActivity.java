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
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private RoundContainer roundContainer;
    private List<Round> rounds;
    private ImageButton ib_simple, ib_try_limited, ib_time_limited, ib_one_try;
    RoundDao roundDao;
    List<User> users2 = new ArrayList<User>();
    List<User> result;

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

        List<String> lastName = new ArrayList<String>(List.of("Nguyen", "Tran", "Le", "Hai"));
        List<String> firstName = new ArrayList<String>(List.of("Huy Hoang", "Thong Tue", "Thai Hoa", "Long Quan"));

        int userCount = 4;
        List<User> users = new ArrayList<User>();
        for (int idx = 0; idx < userCount; idx++){
            users.add(new User(firstName.get(idx), lastName.get(idx)));
        }

        //Will try with API Currency
        //Multiple thread
        //https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
        Runnable insertUsersRunnable = new Runnable() {
            @Override
            public void run() {
                if (users.size() != 0){
                    Log.d("MainActivity", "insert Users");
                    for (int idx = 0; idx < users.size(); idx++){
                        User user = users.get(idx);
                        userDao.insertAll(user);
                        Log.d("MainActivity", user.getName());
                    }
                }
            }
        };

        Thread thread1 = new Thread(insertUsersRunnable);
        thread1.start();

        Runnable getUsersRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("MainActivity", "get User");
                users2 = userDao.getAll();
                for (int idx = 0; idx < users2.size(); idx++){
                    String userName = users2.get(idx).getName();
                    Log.d("MainAcitivity", userName);
                }
            }
        };

        Thread thread2 = new Thread(getUsersRunnable);
        thread2.start();

        Callable<User> getUserByName = () ->{
            User user2 = userDao.findByName(firstName.get(1), lastName.get(1));
            System.out.println(user2.getUid());
            Log.d("MainActivity", Integer.toString(user2.getUid()));
            return user2;
        };

        UserExecutor userExecutor = new UserExecutor();
        try {
            result = userExecutor.startCallable(getUserByName);
            Log.d("MainActivity", "get result from callable");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userExecutor.stop();

        if (result != null && result.size() != 0){
            Log.d("MainActivity", "Result size: " + Integer.toString(result.size()));
            if (result.size() != 0){
                for (int idx = 0; idx < result.size(); idx++){
                    User user = result.get(idx);
                    Log.d("MainActivity", "Index " + Integer.toString(idx) + ": " +
                            user.getName());
                }
            }
        }
        else {
            Log.d("MainActivity", "Result is null");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}