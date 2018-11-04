package ru.gnkoshelev.demo.tutorial.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Gregory Koshelev
 */
@ExtendWith(MockitoExtension.class) // 1. @ExtendWith - новый механизм расширений в JUnit 5 Jupiter, MockitoExtension - поддержка Mockito в Jupiter
public class SpyMockingTest {
    @Spy //2. Создание объекта-шпиона (в данном случае для тестирования будет использоваться реальный объект с возможностью изменить поведение части методов)
    private JZ1 engine = new JZ1("12345"); //3. Если не конструировать объект явно, то для создания spy-объекта будет использоваться конструктор по умолчанию

    @InjectMocks //4. Создание объекта и инжектирование в него мок-объектов (в данном случае используется инжектирование через конструктор)
    private MarkII mark;

    @Test //5. В данном тесте демонстрируется работа аннотаций @Spy и @InjectMocks создание мок-объекта и его инжектирование в тестируемый объект
    void shouldInjectMocks() {
        Assertions.assertNotNull(mark);
        Assertions.assertNotNull(engine);
        Assertions.assertSame(engine, mark.underTheHood());//6. Сравнение по ссылке (см. 5.)
    }

    @Test //7. В данном тесте демонстрируется пример работы библиотеки Mockito с применением BDD
    void shouldSpyBehavior() {
        BDDMockito.willReturn("Тыр-тыр-пш").given(engine).startInternal();//8. Использование BDD-нотации для задания поведения мок-объекта при вызове метода Engine.startInternal()
        mark.start();//9. В методе Car.start() делегирован вызов к Engine.start(), где в свою очередь вызывается Engine.startInternal(), но ровно 1 раз (см. 10. и реализацию Engine.start())
        mark.start();//10. Второй вызов Car.start() не приведёт к вызову Engine.startInternal(), что можно будет проверить с использованием Mockito.verify() (см. 11.)
        Mockito.verify(engine, Mockito.times(1)).startInternal();//11. Mockito.verify() позволяет проверить количество вызываемых методов у мок-объекта
    }
}
