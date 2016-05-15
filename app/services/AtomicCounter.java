package services;

import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
public class AtomicCounter implements Counter {

    private final AtomicLong atomicCounter = new AtomicLong(0);

    @Override
    public long nextCount() {
       return atomicCounter.getAndIncrement();
    }
}
