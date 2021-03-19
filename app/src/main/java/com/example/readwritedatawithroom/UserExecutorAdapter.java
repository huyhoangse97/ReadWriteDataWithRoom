package com.example.readwritedatawithroom;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserExecutorAdapter {
    ExecutorService userExecutor = Executors.newSingleThreadExecutor();
    UserDao userDao;

    public UserExecutorAdapter(UserDao userDao){
        this.userDao = userDao;
    }

    public void insertUser(User user){
        userExecutor.submit(new Runnable() {
            @Override
            public void run() {
                userDao.insertAll(user);
            }
        });
    };

    public void insertUsers(List<User> users){
        userExecutor.submit(new Runnable() {
            @Override
            public void run() {
                for (int idx = 0; idx < users.size(); idx++){
                    userDao.insertAll(users.get(idx));
                }
            }
        });
    };

    public void resetTable(){
        userExecutor.submit(new Runnable() {
            @Override
            public void run() {
                userDao.reset();
            }
        });
    }

    public User findByName(String firstName,String lastName){
        Callable<User> getUserByName = () ->{
            User user = userDao.findByName(firstName, lastName);
            System.out.println(user.getUid());
            Log.d("MainActivity", Integer.toString(user.getUid()));
            return user;
        };

        try {
            return (User) userExecutor.submit(getUserByName).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getAll(){
        Callable<List<User>> getAll = () ->{
            return userDao.getAll();
        };
        try {
            return userExecutor.submit(getAll).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
