package com.example.readwritedatawithroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Gson gson = new Gson();
    private RoundContainer roundContainer;
    private List<Round> rounds;
    private ImageButton ib_simple, ib_try_limited, ib_time_limited, ib_one_try;
    RoundDao roundDao;

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
//        roundDao = db.roundDao();

        User user = new User();
        new UserAsyncTask(this, user, userDao).execute();

//        View.OnClickListener modeClickListener = new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                ImageButton imageButton = (ImageButton) v;
//                List<Round> rounds1;
//                rounds1 = roundDao.getRoundsByModeName("simple");
//                for (int idx = 0; idx < rounds1.size(); idx++){
//                    Round currentRound = new Round();
//                    currentRound = rounds1.get(idx);
//                    currentRound.setModeId(imageButton.getId());
//                    roundDao.insert(currentRound);
//                }
//
//                List<Round> rounds2 = roundDao.getRoundsByModeName("simple");
//                for (int idx = 0; idx < rounds2.size(); idx++){
//                    int modeId = ((Round)rounds2.get(idx)).getModeId();
//                    Log.e("MainActivity", Integer.toString(modeId));
//                }
//
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, RoundActivity.class);
//                startActivity(intent);
//            }
//        };
//        ib_simple.setOnClickListener(modeClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}