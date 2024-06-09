package ilya.initializer;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import ilya.annotation.KeyAction;
import ilya.utils.InterfaceImplementationChecker;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.util.Set;

@Slf4j
public class KeystrokeHandlerInitializer {
    private final static String BASE_PACKAGE = "ilya.listener";

    void init() {
        log.info("Инициализация обработчика клавиш");
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            log.error("Не удалось инициализировать глобальный перехватчик клавиатуры.");
            System.exit(1);
        }
        Set<Class<?>> keystrokeHandlers = getKeystrokeHandlers();
        for (var handler : keystrokeHandlers) {
            try {
                NativeKeyListener handlerInstance = (NativeKeyListener) handler.getDeclaredConstructor().newInstance();
                GlobalScreen.addNativeKeyListener(handlerInstance);
            } catch (Exception e) {
                log.error("Не удалось создать и зарегистрировать обработчик: " + handler.getName(), e);
            }
        }
    }

    Set<Class<?>> getKeystrokeHandlers() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(BASE_PACKAGE)
                        .addScanners(Scanners.TypesAnnotated)
        );
        Set<Class<?>> keystrokeHandlers = reflections
                .get(Scanners.TypesAnnotated.with(KeyAction.class).asClass());
        InterfaceImplementationChecker.check(keystrokeHandlers, KeyAction.implementsClazz);
        return keystrokeHandlers;
    }

}
