package com.example.readwritedatawithroom;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class UserExecutor implements UserExecutorInterface {

    final String tag = "UserExecutor";
    ExecutorService executor;

    public UserExecutor(){
        executor = Executors.newSingleThreadExecutor();
    }

    public void startRunnable(Runnable runnable){
        Log.d(tag, "StartRunnable");
        executor.submit(runnable);
        stop();
    }

    public List<User> startCallable(Callable callable) throws ExecutionException, InterruptedException {
        if (executor.submit(callable).get().getClass() == User.class){
            List<User> result = new ArrayList<User>();
            User user = (User) executor.submit(callable).get();
            result.add(user);
            return result;
        }
        else{
            return (List<User>) executor.submit(callable).get();
        }

//        Log.d(tag, "start Callable");
//        Future<User> future = executor.submit(callable);
//
//        System.out.println("Future done? " + future.isDone());
//        Log.d(tag, "Future done? " + future.isDone());
//        if (future.isDone()){
//            System.out.println("Completed");
//            Log.d(tag, "Completed");
//            try {
//                return future.get(5, TimeUnit.SECONDS);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();//ignore/reset
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
//        }
//        stop();
//        return null;
    }

    public void stop(){
        try{
            System.out.println("attempt executor to shutdown");
            Log.d(tag, "attempt executor to shutdown");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (!executor.isTerminated()){
                System.out.println("Cancel Non-finished Task");
                Log.d(tag, "Cancel Non-finished Task");
            }
            executor.shutdownNow();
            System.out.println("Shutdown finished");
            Log.d(tag, "Shutdown finished");
        }
    }
}
