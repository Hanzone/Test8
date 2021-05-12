package interfaces.impl;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

@Slf4j
public class Mycloseable implements Closeable {

    @Override
    public void close() throws IOException {
        log.info("Mycloseable closed");
    }

}
