package ilya.button;

import ilya.annotation.MenuButton;

import java.awt.MenuItem;

@MenuButton
public class ExitButton implements TrayButton {
    @Override
    public MenuItem createButton() {
        MenuItem exitItem = new MenuItem("Выход");
        exitItem.addActionListener(e -> System.exit(0));
        return exitItem;
    }
}
