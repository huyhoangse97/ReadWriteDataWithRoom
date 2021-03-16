package com.example.readwritedatawithroom;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

public class UserAsyncTask extends AsyncTask<Void, Void, Integer> {

    //Prevent leak
    private WeakReference<Activity> weakActivity;
    private User user;
    UserDao userDao;

    public UserAsyncTask(Activity activity, User user, UserDao userDao) {
        weakActivity = new WeakReference<>(activity);
        this.user = user;
        this.userDao = userDao;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        userDao.insertAll(user);
        List<User> users = userDao.getAll();
        for (int idx = 0; idx < users.size(); idx++){
            String userName = users.get(idx).getName();
            Log.d("UserAsyncTask", userName);
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer agentsCount) {
        Activity activity = weakActivity.get();
        if(activity == null) {
            return;
        }
    }
}
