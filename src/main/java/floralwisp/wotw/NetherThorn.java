package floralwisp.wotw;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class NetherThorn extends Item {
    private final String translationKey;
    public NetherThorn(Item.Properties properties, String translationKey) {
        super(properties);
        this.translationKey = translationKey;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Boolean variant = itemStack.get(ModComponents.WARPED_VARIANT_COMPONENT);
        return Component.translatable(variant != null
                ? translationKey + "_warpedthorn"
                : translationKey + "_crimsonthorn"
        );
    }
}
