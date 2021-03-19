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
    final String TAG = "MainActivity";
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

        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "UserDatabase").fallbackToDestructiveMigration().build();
        UserDao userDao = db.userDao();
        UserExecutorAdapter userExecutorAdapter = new UserExecutorAdapter(userDao);

        userExecutorAdapter.resetTable();

        List<String> lastName = new ArrayList<String>(List.of("Nguyen", "Tran", "Le", "Hai"));
        List<String> firstName = new ArrayList<String>(List.of("Huy Hoang", "Thong Tue", "Thai Hoa", "Long Quan"));

        int userCount = 4;
        List<User> users = new ArrayList<User>();
        for (int idx = 0; idx < userCount; idx++){
            users.add(new User(firstName.get(idx), lastName.get(idx)));
        }
        userExecutorAdapter.insertUsers(users);
//        userExecutorAdapter.insertUsers(users);

        User result = userExecutorAdapter.findByName(firstName.get(1), lastName.get(1));
        if (result != null){
            userExecutorAdapter.insertUser(result);
            User newUser = result;
            newUser.setUid(12432);
            newUser.setFirstName("Minh Tri");
            Log.d(TAG, "Result: user name: " + result.getName());
        }

        List<User> resultArr = userExecutorAdapter.getAll();
        if (resultArr != null && resultArr.size() > 0){
            Log.d(TAG, "getAll result: ");
            for (int idx = 0; idx < resultArr.size(); idx++){
                Log.d(TAG, "index of " + Integer.toString(idx)
                    + " : " + ((User)resultArr.get(idx)).getName());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}