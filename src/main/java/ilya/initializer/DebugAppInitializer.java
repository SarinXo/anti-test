package ilya.initializer;

import ilya.initializer.menu.TrayMenuInitializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DebugAppInitializer implements AppInitializer {

    @Override
    public void startApp() {
        new BannerPrinter().printBanner();
        new KeystrokeHandlerInitializer().init();
        new TrayMenuInitializer().init();
    }

}
