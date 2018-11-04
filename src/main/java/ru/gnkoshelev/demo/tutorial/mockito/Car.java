package ru.gnkoshelev.demo.tutorial.mockito;

import java.util.Objects;

/**
 * @author Gregory Koshelev
 */
public abstract class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public Engine underTheHood() {
        return engine;
    }

    public void start() {
        Objects.requireNonNull(engine).start();
    }

    public int power() {
        return Objects.requireNonNull(engine).getPower();
    }
}
