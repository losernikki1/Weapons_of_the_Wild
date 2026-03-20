package floralwisp.wotw;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CreativeTab {
    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, WOTW_CREATIVE_TAB_KEY, WOTW_CREATIVE_TAB);
    }
    public static final ResourceKey<CreativeModeTab> WOTW_CREATIVE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(Floralwisp_wotw.MOD_ID, "creative_tab"));
    public static final CreativeModeTab WOTW_CREATIVE_TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.COPPER_BLOCK))
            .title(Component.literal("Weapons of the Wild"))
            .displayItems((params, output) -> {
                output.accept(MossThorns.LIVING_MOSS);
                output.accept(MossThorns.WOODEN_MOSSTHORN);
                output.accept(MossThorns.STONE_MOSSTHORN);
                output.accept(MossThorns.COPPER_MOSSTHORN);
                output.accept(MossThorns.IRON_MOSSTHORN);
                output.accept(MossThorns.GOLDEN_MOSSTHORN);
                output.accept(MossThorns.DIAMOND_MOSSTHORN);
                output.accept(MossThorns.NETHERITE_MOSSTHORN);
                output.accept(MossThorns.GOLDEN_NETHERTHORN);
                output.accept(MossThorns.DIAMOND_NETHERTHORN);
                output.accept(MossThorns.NETHERITE_NETHERTHORN);
                ItemStack GOLDEN_WARPEDTHORN = new ItemStack(MossThorns.GOLDEN_NETHERTHORN);
                GOLDEN_WARPEDTHORN.set(ModComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(GOLDEN_WARPEDTHORN);
                ItemStack DIAMOND_WARPEDTHORN = new ItemStack(MossThorns.DIAMOND_NETHERTHORN);
                DIAMOND_WARPEDTHORN.set(ModComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(DIAMOND_WARPEDTHORN);
                ItemStack NETHERITE_WARPEDTHORN = new ItemStack(MossThorns.NETHERITE_NETHERTHORN);
                NETHERITE_WARPEDTHORN.set(ModComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(NETHERITE_WARPEDTHORN);
            })
            .build();
}
