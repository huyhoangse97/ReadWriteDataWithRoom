package com.example.readwritedatawithroom;

import  java.util.concurrent.Executor;

public class UserExecutor implements Executor {
    public void execute(Runnable r){
        r.run();
    }
}
