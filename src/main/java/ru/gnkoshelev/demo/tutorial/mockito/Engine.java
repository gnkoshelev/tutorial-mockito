package ru.gnkoshelev.demo.tutorial.mockito;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Gregory Koshelev
 */
public abstract class Engine {
    private final String model;
    private final String serialNumber;
    private int power;
    private AtomicBoolean started = new AtomicBoolean(false);

    public Engine(String model, String serialNumber, int power) {
        this.model = model;
        this.serialNumber = serialNumber;
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getPower() {
        return power;
    }

    protected abstract String startInternal();

    void start() {
        if (started.compareAndSet(false, true)) {
            System.out.println(startInternal());
        }
    }
}
