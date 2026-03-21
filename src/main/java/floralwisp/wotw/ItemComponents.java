package floralwisp.wotw;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class ItemComponents {
    protected static void initialize() {
        Floralwisp_wotw.LOGGER.info("Registering {} components", Floralwisp_wotw.MOD_ID);
    }

    public static final DataComponentType<Boolean> WARPED_VARIANT_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Floralwisp_wotw.MOD_ID, "warped_variant"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );

    public static final DataComponentType<Integer> ROOT_VARIANT_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(Floralwisp_wotw.MOD_ID, "root_variant"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );
}

