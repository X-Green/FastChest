package re.domi.fastchest.config;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public String getModId() {
        return "fast_chest";
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return ConfigScreen::new;
    }
}
