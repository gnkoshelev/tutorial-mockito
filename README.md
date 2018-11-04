# Пример использования библиотеки Mockito в Unit-тестировании совместно с JUnit 5 Jupiter

Небольшой пример использования Mock-объектов в Unit-тестах на примере библиотек Mockito и JUnit 5 Jupiter.

## Конфигурация Maven-проекта

### Зависимости

`org.junit.jupiter:junit-jupiter-engine:5.3.1` - JUnit 5 Jupiter  
`org.mockito:mockito-core:2.23.0` - Mockito
`org.mockito:mockito-junit-jupiter:2.23.0` - Mockito & Jupiter integration

### Интеграция с build-циклом Maven

```xml
<project>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.1</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Mock для final-классов

Класс объявлен с модификатором `final`, поэтому в тестах *по умолчанию* не получится создать для него мок-объекты.  
Для того, чтобы иметь возможность создания мок-объектов для таких классов используется `org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker`. Для этого достаточно добавить файл `src/test/resources/mockito-extensions/org.mockito.plugins.MockMaker` с единственной строчкой внутри:
```text
mock-maker-inline
```

## Mockito

В тестах `SimpleMockingTest` и `SpyMockingTest` приведены примеры использования библиотеки Mockito (см. комментарии к тестам).
