package ilya;

import ilya.initializer.AppInitializer;
import ilya.initializer.DebugAppInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AntiTest {
    private static final AppInitializer debugInitializer = new DebugAppInitializer();
    public static void main(String[] args) {
        debugInitializer.startApp();
    }

}

