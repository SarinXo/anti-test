package ilya.initializer.menu;

import lombok.extern.slf4j.Slf4j;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;

@Slf4j
public class TrayMenuInitializer {
    private final static String PATH_TO_ICON = "/assets/icons/clown.png";

    public void init() {
        log.info("Начало инициализации трея");
        if (!SystemTray.isSupported()) {
            log.error("Системный трей не поддерживается.");
            return;
        }
        PopupMenu popupMenu = new PopupMenu();
        for (var button : new PopupMenuButtonInitializer().initButtons()) {
            popupMenu.add(button);
        }

        URL imageURL = TrayMenuInitializer.class.getResource(PATH_TO_ICON);
        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        TrayIcon trayIcon = new TrayIcon(image, "Clown.exe", popupMenu);
        trayIcon.setImageAutoSize(true);
        SystemTray systemTray = SystemTray.getSystemTray();
        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            log.error("Не удалось добавить иконку в системный трей.", e);
        }
    }
}
