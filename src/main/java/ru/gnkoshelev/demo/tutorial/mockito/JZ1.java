package ru.gnkoshelev.demo.tutorial.mockito;

/**
 * @author Gregory Koshelev
 */
public final class JZ1 extends Engine {
    public JZ1(String sn) {
        super("1JZ", sn, 280);
    }

    @Override
    protected String startInternal() {
        return "Тыр-тыр-врррууум";
    }
}
