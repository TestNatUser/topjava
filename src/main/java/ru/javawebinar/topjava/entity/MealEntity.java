package ru.javawebinar.topjava.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class MealEntity {
    private final AtomicInteger c = new AtomicInteger(0);
    public int increment()
    {
        return c.incrementAndGet();
    }

    public int value()
    {
        return c.get();
    }
}
