package com.example.readwritedatawithroom;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface UserExecutorInterface {

   void startRunnable(Runnable runnable);

   List<User> startCallable(Callable callable) throws ExecutionException, InterruptedException;

   void stop();
}
