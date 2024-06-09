package ilya.listener;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import ilya.annotation.KeyAction;
import lombok.extern.slf4j.Slf4j;

@KeyAction
public class ExitListener implements NativeKeyListener {

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_TAB) {
            System.exit(0);
        }
    }
}