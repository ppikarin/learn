package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Concurrent {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("--- BEGIN");

        var status = new DownloadStatus();

        List<Thread> threads =  new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
        }

        for (var thread : threads) {
            thread.join();
        }

        System.out.println(status.getTotalBytes());


        System.out.println("--- END");

    }
}
