package ru.gnkoshelev.demo.tutorial.mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Gregory Koshelev
 */
@ExtendWith(MockitoExtension.class) // 1. @ExtendWith - новый механизм расширений в JUnit 5 Jupiter, MockitoExtension - поддержка Mockito в Jupiter
public class SimpleMockingTest {
    @Mock //2. Создание объекта-заглушки
    private static JZ1 engine;

    @InjectMocks //3. Создание объекта и инжектирование в него мок-объектов (в данном случае используется инжектирование через конструктор)
    private MarkII mark;

    @Test //4. В данном тесте демонстрируется работа аннотаций @Mock и @InjectMocks создание мок-объекта и его инжектирование в тестируемый объект
    void shouldInjectMocks() {
        Assertions.assertNotNull(mark);
        Assertions.assertNotNull(engine);
        Assertions.assertSame(engine, mark.underTheHood());//5. Сравнение по ссылке (см. 4.)
    }

    @Test //6. В данном тесте демонстрируется простейший пример работы библиотеки Mockito
    void shouldMockBehavior() {
        Mockito.when(engine.getPower()).thenReturn(100);//7. Для мок-объекта задаётся поведение на вызов метода Engine.getPower() (см. 9.)
        Mockito
                .lenient()//8. Метод getSerialNumber() в тесте не используется, поэтому без Mockito.lenient() тест упал бы с ошибкой o.m.e.m.UnnecessaryStubbingException
                .when(engine.getSerialNumber()).thenReturn("54321");
        Assertions.assertEquals(100, mark.power(), "detailed message");//9. Вызов метода Car.power() делегирован Engine.getPower() (см. 7.)
        mark.start();//10. В методе Car.start() делегирован вызов к Engine.start() (см. 11.)
        Mockito.verify(engine).start();//11. Mockito.verify() позволяет проверить количество вызываемых методов у мок-объекта (verify  одним аргументом - вызов метода ровно 1 раз (см. 10.)
    }
}
