package floralwisp.wotw;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.AttackRange;

public class MossThorns {
    public static void initialize() {
        Floralwisp_wotw.LOGGER.info("Registering {} weapon type: 'Mossthorn'", Floralwisp_wotw.MOD_ID);
    }

    public static AttackRange MOSSTHORN_ATTACK_RANGE = new AttackRange(0f,4.0f,0.0f,5.0f,0.3f,1.0f);
    public static Item.Properties MOSSTHORN_ITEM_PROPERTIES = new Item.Properties()
            .component(DataComponents.ATTACK_RANGE, MOSSTHORN_ATTACK_RANGE);

    public static final Item WOODEN_MOSSTHORN = Registerer.register(
            "wooden_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.WOOD,2f,-2f)
    );
    public static final Item STONE_MOSSTHORN = Registerer.register(
            "stone_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.STONE, 2f, -2f)
    );
    public static final Item COPPER_MOSSTHORN = Registerer.register(
            "copper_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.COPPER, 2f, -2f)
    );
    public static final Item IRON_MOSSTHORN = Registerer.register(
            "iron_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.IRON, 2f, -2f)
    );
    public static final Item GOLDEN_MOSSTHORN = Registerer.register(
            "golden_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.GOLD, 2f, -2f)
    );
    public static final Item GOLDEN_NETHERTHORN = Registerer.register(
            "golden_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.golden"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.GOLD, 2f, -2f)
    );
    public static final Item DIAMOND_MOSSTHORN = Registerer.register(
            "diamond_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.DIAMOND, 2f, -2f)
    );
    public static final Item DIAMOND_NETHERTHORN = Registerer.register(
            "diamond_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.diamond"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.DIAMOND, 2f, -2f)
    );
    public static final Item NETHERITE_MOSSTHORN = Registerer.register(
            "netherite_mossthorn",
            Item::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.NETHERITE, 2f, -2f)
    );
    public static final Item NETHERITE_NETHERTHORN = Registerer.register(
            "netherite_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.netherite"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.NETHERITE, 2f, -2f)
    );
}