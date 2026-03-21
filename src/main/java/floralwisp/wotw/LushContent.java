package floralwisp.wotw;

import floralwisp.wotw.blocks.EntanglingRoots;
import floralwisp.wotw.items.MossThorn;
import floralwisp.wotw.items.NetherThorn;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LushContent {
    public static void initialize() {
        Floralwisp_wotw.LOGGER.info("Registering {} weapon type: 'Mossthorn'", Floralwisp_wotw.MOD_ID);
        BlockRenderLayerMap.putBlock(ENTANGLING_ROOTS, ChunkSectionLayer.CUTOUT);
        FlammableBlockRegistry.getDefaultInstance().add(ENTANGLING_ROOTS,60,100);
    }
    //TAG
    public static final TagKey<Item> MOSSTHORN_TAG = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Floralwisp_wotw.MOD_ID, "mossthorn"));
    //BLOCKS
    public static final Block ENTANGLING_ROOTS = Registerer.registerBlock(
            "entangling_roots",
            EntanglingRoots::new,
            BlockBehaviour.Properties.of()
                    .isValidSpawn((state, level, pos, entityType) -> false)
                    .destroyTime(0.5f)
                    .ignitedByLava()
                    .explosionResistance(3f)
                    .noOcclusion()
                    .noCollision()
                    .sound(SoundType.ROOTS),
            false
    );
    //BLOCKITEMS
    public static final BlockItem ENTANGLING_ROOTS_ITEM = Registerer.registerBlockItem(
            "entangling_roots",
            ENTANGLING_ROOTS,
            new Item.Properties()
    );
    public static final BlockItem STRIPPED_ENTANGLING_ROOTS = Registerer.registerBlockItem(
            "entangling_roots_stripped",
            ENTANGLING_ROOTS,
            new Item.Properties().component(ItemComponents.ROOT_VARIANT_COMPONENT,0)
    );
    public static final BlockItem MOSSY_ENTANGLING_ROOTS = Registerer.registerBlockItem(
            "entangling_roots_mossy",
            ENTANGLING_ROOTS,
            new Item.Properties().component(ItemComponents.ROOT_VARIANT_COMPONENT,1)
    );
    public static final BlockItem MYCELIUM_ENTANGLING_ROOTS = Registerer.registerBlockItem(
            "entangling_roots_mycelium",
            ENTANGLING_ROOTS,
            new Item.Properties().component(ItemComponents.ROOT_VARIANT_COMPONENT,2)
    );
    public static final BlockItem CRIMSON_ENTANGLING_ROOTS = Registerer.registerBlockItem(
            "entangling_roots_crimson",
            ENTANGLING_ROOTS,
            new Item.Properties().component(ItemComponents.ROOT_VARIANT_COMPONENT,3)
    );
    public static final BlockItem WARPED_ENTANGLING_ROOTS = Registerer.registerBlockItem(
            "entangling_roots_warped",
            ENTANGLING_ROOTS,
            new Item.Properties().component(ItemComponents.ROOT_VARIANT_COMPONENT,4)
    );
    //ITEMS
    public static AttackRange MOSSTHORN_ATTACK_RANGE = new AttackRange(0f,4.0f,0.0f,5.0f,0.3f,1.0f);
    public static Item.Properties MOSSTHORN_ITEM_PROPERTIES = new Item.Properties()
            .component(DataComponents.ATTACK_RANGE, MOSSTHORN_ATTACK_RANGE);

    public static final Consumable LIVING_MOSS_CONSUMABLE_COMPONENT = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.GLOWING, 10 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0), 1.0f))
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 0), 1.0f))
            .build();

    public static final FoodProperties LIVING_MOSS_FOOD_COMPONENT = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0f)
            .alwaysEdible()
            .build();
    //Living Moss
    public static final Item LIVING_MOSS = Registerer.registerItem(
            "living_moss",
            Item::new,
            new Item.Properties().food(LIVING_MOSS_FOOD_COMPONENT, LIVING_MOSS_CONSUMABLE_COMPONENT)
    );
    //Mossthorns
    public static final Item WOODEN_MOSSTHORN = Registerer.registerItem(
            "wooden_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.WOOD,2f,-2f)
    );
    public static final Item STONE_MOSSTHORN = Registerer.registerItem(
            "stone_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.STONE, 2f, -2f)
    );
    public static final Item COPPER_MOSSTHORN = Registerer.registerItem(
            "copper_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.COPPER, 2f, -2f)
    );
    public static final Item IRON_MOSSTHORN = Registerer.registerItem(
            "iron_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.IRON, 2f, -2f)
    );
    public static final Item GOLDEN_MOSSTHORN = Registerer.registerItem(
            "golden_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.GOLD, 2f, -2f)
    );
    public static final Item DIAMOND_MOSSTHORN = Registerer.registerItem(
            "diamond_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.DIAMOND, 2f, -2f)
    );
    public static final Item NETHERITE_MOSSTHORN = Registerer.registerItem(
            "netherite_mossthorn",
            MossThorn::new,
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.NETHERITE, 2f, -2f)
    );
    //Netherthorns
    public static final Item GOLDEN_NETHERTHORN = Registerer.registerItem(
            "golden_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.golden"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.GOLD, 2f, -2f)
    );
    public static final Item DIAMOND_NETHERTHORN = Registerer.registerItem(
            "diamond_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.diamond"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.DIAMOND, 2f, -2f)
    );
    public static final Item NETHERITE_NETHERTHORN = Registerer.registerItem(
            "netherite_netherthorn",
            properties -> new NetherThorn(properties, "item.floralwisp_wotw.netherite"),
            MOSSTHORN_ITEM_PROPERTIES.sword(ToolMaterial.NETHERITE, 2f, -2f)
    );
}