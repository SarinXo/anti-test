package ilya.initializer.menu;

import ilya.annotation.MenuButton;
import ilya.button.TrayButton;
import ilya.utils.InterfaceImplementationChecker;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class PopupMenuButtonInitializer {
    private final static String BASE_PACKAGE = "ilya.button";

    List<MenuItem> initButtons() {
        log.info("Начало создания кнопок для меню трея");
        List<MenuItem> popupItems = new ArrayList<>();
        Set<Class<?>> buttons = getPopupMenuButtons();
        for (var button : buttons) {
            try {
                MenuItem initButton =
                        ((TrayButton) button.getDeclaredConstructor().newInstance())
                                .createButton();
                popupItems.add(initButton);
            } catch (Exception e) {
                log.error("Не удалось создать и зарегистрировать кнопку: " + button.getName(), e);
            }
        }
        return popupItems;
    }

    Set<Class<?>> getPopupMenuButtons() {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(BASE_PACKAGE)
                        .addScanners(Scanners.TypesAnnotated)
        );
        Set<Class<?>> keystrokeHandlers = reflections
                .get(Scanners.TypesAnnotated.with(MenuButton.class).asClass());
        InterfaceImplementationChecker.check(keystrokeHandlers, MenuButton.implementsClazz);
        return keystrokeHandlers;
    }
}
