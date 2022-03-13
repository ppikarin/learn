package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Async {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("--- BEGIN");

        var f1 = CompletableFuture
                .supplyAsync(Async::waitWorld)
                .thenApply(s -> s + " thenApply")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " thenCompose"))
                .thenCombine(CompletableFuture.supplyAsync(() -> " thenCombine"), (s1, s2) -> s1 + s2);

        f1.thenAccept(s -> System.out.println(s));

        System.out.println("--- future created");

        wait(3000);

        System.out.println("--- END");

    }

    private static void wait(int l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String waitWorld() {
        wait(2000);
        return "Wait World";
    }

}
