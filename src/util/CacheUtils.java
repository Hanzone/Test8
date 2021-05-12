package util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Hanzone
 * @date 2021-05-12
 */
@Slf4j
public class CacheUtils {

    public static Cache<Integer, Integer> CACHE = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .recordStats()
            .build();

    public static Integer sss(int i) {
        try {
            return CACHE.get(i, () -> {
                if (i == 1) {
                    throw new RuntimeException("RPC EEEEEEEEEEEEEEE");
                }
                return i * 2;
            });
        } catch (ExecutionException e) {
            log.error("ssss", e);
            return 1000;
        }
    }

}
