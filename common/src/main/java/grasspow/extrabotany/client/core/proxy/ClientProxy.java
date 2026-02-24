package grasspow.extrabotany.client.core.proxy;

import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.UnknownNullability;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class ClientProxy {
    @UnknownNullability
    public static KeyMapping BUDDHIST_RELICS_MORPH;

    public static void initKeybindings(Consumer<KeyMapping> consumer) {
        BUDDHIST_RELICS_MORPH = new KeyMapping("key.buddhist_relics_morph", GLFW.GLFW_KEY_LEFT_CONTROL, "key.categories.extrabotany");
        consumer.accept(BUDDHIST_RELICS_MORPH);
    }
}
