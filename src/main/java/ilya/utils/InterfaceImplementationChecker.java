package ilya.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class InterfaceImplementationChecker {

    public static void check(Set<Class<?>> annotatedClasses, Class<?> interfaceClass) {
        for (Class<?> clazz : annotatedClasses) {
            if (interfaceClass.isAssignableFrom(clazz)) {
                log.info(clazz.getName() + " реализует нужный интерфейс");
            } else {
                String error = "Класс " + clazz.getName() + " не реализует " + interfaceClass.getName();
                log.error(error);
                throw new RuntimeException(error);
            }
        }
    }
}
