package ilya.annotation;

import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Классы помеченные этой аннотацией должны реализовывать @NativeKeyListener
 * и при старте приложения будут добавлены в отслеживающиеся клавиши.
 * Все ключи должны быть добавлены в пакет listener
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface KeyAction {
    Class<NativeKeyListener> implementsClazz = NativeKeyListener.class;
}
