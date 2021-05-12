package concurrent;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadPoolFactory {

    public static Pair<ThreadPoolExecutor, Runnable> creatExecutorWithStopper(String poolName) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                20,
                1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1000),
                new NamedThreadFactory(poolName),
                (runnable, executor) -> {
                    throw new RejectedExecutionException("Task rejected from " + executor.toString());
                });
        Runnable stopper = () -> ThreadPoolFactory.shutdownAndWait(threadPoolExecutor);
        return Pair.of(threadPoolExecutor, stopper);
    }

    public static void shutdownAndWait(ExecutorService executorService) {
        executorService.shutdown();

        try {
            while (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                log.info("Waiting for all tasks complete execution");
            }
            log.info("Executor is shut down");
        } catch (InterruptedException e) {
            log.error("Executor shutdown failed");
            Thread.currentThread().interrupt();
        }
    }

    private static final class NamedThreadFactory implements ThreadFactory {
        private final String name;
        private final AtomicInteger threadIndex = new AtomicInteger(0);

        private NamedThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable target) {
            int index = threadIndex.getAndIncrement();
            Thread thread = new Thread(target, this.name + "-" + index);
            thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        }
    }

}
