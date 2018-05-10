package funInterfaces;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Hanzone
 * @date 2018年05月09日
 */
@FunctionalInterface
public interface BufferedReaderProcessor<T> {

    T process(BufferedReader bf);
}
