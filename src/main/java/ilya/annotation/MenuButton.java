package ilya.annotation;

import ilya.button.TrayButton;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Классы помеченные этой аннотацией будут созданы и добавлены в трей меню
 * должны реализовывать @TrayButton.
 * Все кнопки меню должны быть добавлены в пакет button
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface MenuButton {
    Class<TrayButton> implementsClazz = TrayButton.class;
}
