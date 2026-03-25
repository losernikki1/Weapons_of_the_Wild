package floralwisp.wotw.items;

import floralwisp.wotw.LushContent;
import floralwisp.wotw.blocks.EntanglingRoots;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class MossThorn extends Item {
    protected Block[] allowed = {Blocks.MOSS_BLOCK, Blocks.AZALEA, Blocks.FLOWERING_AZALEA, Blocks.ROOTED_DIRT,
            Blocks.PODZOL, Blocks.MYCELIUM, Blocks.MUDDY_MANGROVE_ROOTS, Blocks.MANGROVE_ROOTS};

    protected Block[] replaceable = {Blocks.MOSS_CARPET, Blocks.SMALL_DRIPLEAF};

    public MossThorn(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity targetEntity, LivingEntity sourceEntity) {
        super.hurtEnemy(itemStack, targetEntity, sourceEntity);
        Level targetLevel = targetEntity.level();
        BlockPos targetPosition = BlockPos.containing(new Vec3(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ()));
        BlockState targetCurrent = targetLevel.getBlockState(targetPosition);
        BlockState targetBelow = targetLevel.getBlockState(targetPosition.below());
        if (checkIfAllowed(targetBelow, allowed)) {
            if (targetCurrent.is(BlockTags.REPLACEABLE) || checkIfAllowed(targetCurrent, replaceable)) {
                targetEntity.makeStuckInBlock(Blocks.AIR.defaultBlockState(), new Vec3(0.15, 0.05F, 0.15));
                targetLevel.destroyBlock(targetPosition, true);
                BlockPlaceContext rootPlaceContext = new BlockPlaceContext(targetLevel, null, InteractionHand.MAIN_HAND, ItemStack.EMPTY, new BlockHitResult(Vec3.ZERO, Direction.UP, targetPosition, false));
                BlockState rootState = LushContent.ENTANGLING_ROOTS.getStateForPlacement(rootPlaceContext);
                targetLevel.setBlock(
                        targetPosition,
                        rootState != null
                                ? rootState
                                : LushContent.ENTANGLING_ROOTS.defaultBlockState().setValue(EntanglingRoots.ROOT_TYPE, 1),
                        Block.UPDATE_ALL
                );
                ParticleOptions rootSpawnParticles = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState());
                if (rootState != null) {
                    switch (rootState.getValue(EntanglingRoots.ROOT_TYPE)) {
                        case 1:
                            rootSpawnParticles = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MOSS_BLOCK.defaultBlockState());
                            break;
                        case 2:
                            rootSpawnParticles = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.MYCELIUM.defaultBlockState());
                            break;
                        case 3:
                            rootSpawnParticles = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.NETHER_WART_BLOCK.defaultBlockState());
                            break;
                        case 4:
                            rootSpawnParticles = new  BlockParticleOption(ParticleTypes.BLOCK, Blocks.WARPED_WART_BLOCK.defaultBlockState());
                            break;
                    }
                };
                if (targetLevel instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            rootSpawnParticles,
                            targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(),
                            20, 0.5, 0.0, 0.5, 1);
                }
            }
        } else {
            if (((sourceEntity instanceof Player player &&
                    player.getInventory().contains(new ItemStack(Items.BONE_MEAL))) ||
                    entityContains(sourceEntity, Items.BONE_MEAL)
            ) &&
                    targetBelow.is(BlockTags.MOSS_REPLACEABLE) &&
                    (targetCurrent.is(BlockTags.REPLACEABLE) ||
                            targetCurrent.is(Blocks.MOSS_CARPET) ||
                            targetCurrent.is(BlockTags.FLOWERS)) &&
                    targetBelow.isFaceSturdy(targetLevel, targetPosition.below(), Direction.UP)
            ) {
                if (targetEntity.isInWater()) {
                    for (int i = 0; i < Mth.nextInt(targetLevel.getRandom(), 2, 4); i++) {

                        BlockPos tryPosition = new BlockPos(Mth.nextInt(targetLevel.getRandom(), -1, 1), 0, Mth.nextInt(targetLevel.getRandom(), -1, 1));
                        tryPosition = tryPosition.offset(targetPosition);
                        if (targetLevel.getBlockState(tryPosition.below()).is(BlockTags.MOSS_REPLACEABLE) &&
                                targetLevel.getBlockState(tryPosition.below()).isFaceSturdy(targetLevel, tryPosition.below(), Direction.UP) &&
                                targetLevel.getBlockState(tryPosition).is(BlockTags.REPLACEABLE) &&
                                targetLevel.getFluidState(tryPosition).is(FluidTags.WATER) &&
                                targetLevel.getBlockState(tryPosition.above()).is(BlockTags.REPLACEABLE)
                        ) {
                            targetLevel.setBlock(tryPosition.below(), Blocks.MOSS_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
                            targetLevel.setBlock(tryPosition, Blocks.WATER.defaultBlockState(), Block.UPDATE_ALL);
                            targetLevel.setBlock(tryPosition, Blocks.SMALL_DRIPLEAF.defaultBlockState()
                                    .setValue(BlockStateProperties.WATERLOGGED, true)
                                    .setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER), Block.UPDATE_ALL);
                            targetLevel.setBlock(tryPosition.above(), Blocks.SMALL_DRIPLEAF.defaultBlockState()
                                    .setValue(BlockStateProperties.WATERLOGGED, targetLevel.getFluidState(tryPosition.above()).is(FluidTags.WATER))
                                    .setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
                        }
                    }
                }
                if (targetCurrent.is(BlockTags.FLOWERS)) {
                    targetLevel.destroyBlock(targetPosition, true);
                } else {
                    targetLevel.removeBlock(targetPosition, false);
                }
                targetLevel.setBlock(targetPosition.below(), Blocks.MOSS_BLOCK.defaultBlockState(), Block.UPDATE_ALL);
                BoneMealItem.growCrop(new ItemStack(Items.MOSS_BLOCK), targetLevel, targetPosition.below());
                if (targetLevel instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(
                            ParticleTypes.HAPPY_VILLAGER,
                            targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(),
                            40, 1.5, 0.5, 1.5, 5);
                }
                if (sourceEntity instanceof Player player) {
                    if (player.gameMode() != GameType.CREATIVE) {
                        player.getInventory().clearOrCountMatchingItems(p -> p.is(Items.BONE_MEAL), 1, player.inventoryMenu.getCraftSlots());
                    }
                } else {
                    entityErase(sourceEntity, Items.BONE_MEAL);
                }
            }
        }
    }

    public boolean checkIfAllowed(BlockState target, Block[] blockList) {
        for (Block block : blockList) {
            if (target.is(block)) {
                return true;
            }
        }
        return false;
    }

    public boolean entityContains(LivingEntity entity, Item item) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.is(item)) {
                return true;
            }
        }
        return false;
    }

    public void entityErase(LivingEntity entity, Item item) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.is(item)) {
                stack.shrink(1);
            }
        }
    }
}