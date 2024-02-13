package thread.pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import utils.ThreadUtils;

public class Ex03UsingFutureTask {
    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Thread, Runnable

        // Thread Pool
        // + Blocking Queue
        // + Threads

        // Thread Pool
        // + >> execute(Runnable): void
        // + >> submit(Callable<T>): Future<T>

        // FutureTask implements RunnableFuture<V> extends Runnable, Future<V>
        // + Callable<T> >> task
        // + Future<T> >> return value

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());

        // submit task >> set return value to FutureTask
        executorService.submit(futureTask);

        // return value
        System.out.println(futureTask.get());
        executorService.shutdown();
    }

    private static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            // task random number from 10 to 20
            int rand = 10 + random.nextInt(11);
            ThreadUtils.startThread(rand);
            ThreadUtils.doTask(2, TimeUnit.SECONDS);
            return rand;
        }
    }
}
