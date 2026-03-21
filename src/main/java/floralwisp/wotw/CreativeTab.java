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
            .icon(() -> new ItemStack(LushContent.STONE_MOSSTHORN))
            .title(Component.literal("Weapons of the Wild"))
            .displayItems((params, output) -> {
                //BLOCKS
                output.accept(LushContent.ENTANGLING_ROOTS_ITEM);
                output.accept(LushContent.STRIPPED_ENTANGLING_ROOTS);
                output.accept(LushContent.MOSSY_ENTANGLING_ROOTS);
                output.accept(LushContent.MYCELIUM_ENTANGLING_ROOTS);
                output.accept(LushContent.CRIMSON_ENTANGLING_ROOTS);
                output.accept(LushContent.WARPED_ENTANGLING_ROOTS);
                //ITEMS
                output.accept(LushContent.LIVING_MOSS);
                output.accept(LushContent.WOODEN_MOSSTHORN);
                output.accept(LushContent.STONE_MOSSTHORN);
                output.accept(LushContent.COPPER_MOSSTHORN);
                output.accept(LushContent.IRON_MOSSTHORN);
                output.accept(LushContent.GOLDEN_MOSSTHORN);
                output.accept(LushContent.DIAMOND_MOSSTHORN);
                output.accept(LushContent.NETHERITE_MOSSTHORN);
                output.accept(LushContent.GOLDEN_NETHERTHORN);
                output.accept(LushContent.DIAMOND_NETHERTHORN);
                output.accept(LushContent.NETHERITE_NETHERTHORN);
                ItemStack GOLDEN_WARPEDTHORN = new ItemStack(LushContent.GOLDEN_NETHERTHORN);
                GOLDEN_WARPEDTHORN.set(ItemComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(GOLDEN_WARPEDTHORN);
                ItemStack DIAMOND_WARPEDTHORN = new ItemStack(LushContent.DIAMOND_NETHERTHORN);
                DIAMOND_WARPEDTHORN.set(ItemComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(DIAMOND_WARPEDTHORN);
                ItemStack NETHERITE_WARPEDTHORN = new ItemStack(LushContent.NETHERITE_NETHERTHORN);
                NETHERITE_WARPEDTHORN.set(ItemComponents.WARPED_VARIANT_COMPONENT,true);
                output.accept(NETHERITE_WARPEDTHORN);
            })
            .build();
}
