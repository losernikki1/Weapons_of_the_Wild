package floralwisp.wotw.items;

import floralwisp.wotw.ItemComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class NetherThorn extends MossThorn {
    Block[] newAllowed = {Blocks.CRIMSON_NYLIUM, Blocks.NETHER_WART_BLOCK, Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE,
            Blocks.WARPED_NYLIUM, Blocks.WARPED_WART_BLOCK, Blocks.WARPED_STEM,  Blocks.WARPED_HYPHAE};

    Block[] newReplaceable = {Blocks.CRIMSON_FUNGUS, Blocks.WEEPING_VINES,
            Blocks.WARPED_FUNGUS, Blocks.TWISTING_VINES};

    private final String translationKey;
    public NetherThorn(Item.Properties properties, String translationKey) {
        super(properties);
        allowed = addToArray(allowed, newAllowed);
        replaceable = addToArray(replaceable, newReplaceable);
        this.translationKey = translationKey;
    }

    private Block[] addToArray(Block[] original, Block[] toAdd) {
        Block[] combined = new Block[original.length + toAdd.length];
        System.arraycopy(original, 0, combined, 0, original.length);
        System.arraycopy(toAdd, 0, combined, original.length, toAdd.length);
        return(combined);
    }

    @Override
    public Component getName(ItemStack itemStack) {
        Boolean variant = itemStack.get(ItemComponents.WARPED_VARIANT_COMPONENT);
        return Component.translatable(variant != null
                ? translationKey + "_warpedthorn"
                : translationKey + "_crimsonthorn"
        );
    }
}
