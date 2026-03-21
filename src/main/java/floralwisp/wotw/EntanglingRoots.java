package floralwisp.wotw;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class EntanglingRoots extends WaterloggedTransparentBlock {
    public static final IntegerProperty ROOT_TYPE = IntegerProperty.create("root_type",0,4);

    public EntanglingRoots(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(ROOT_TYPE, 0));
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bl) {
        if (!level.getBlockState(blockPos.below()).isFaceSturdy(level,blockPos.below(),Direction.UP)) {
            level.destroyBlock(blockPos, false);
        }
        super.neighborChanged(blockState, level, blockPos, block, orientation, bl);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        Integer variant = blockPlaceContext.getItemInHand().get(ModComponents.ROOT_VARIANT_COMPONENT);
        BlockState blockBelow = blockPlaceContext.getLevel().getBlockState(blockPlaceContext.getClickedPos().below());
        if (!blockBelow.isFaceSturdy(blockPlaceContext.getLevel(),blockPlaceContext.getClickedPos().below(), Direction.UP)) {
            return null;
        }
        int rootType = 0;
        if (variant != null){
            rootType = variant;
        } else {
            if (blockBelow.is(Blocks.MOSS_BLOCK) ||  blockBelow.is(Blocks.AZALEA) ||  blockBelow.is(Blocks.FLOWERING_AZALEA)) {
                rootType = 1;
            } else if (blockBelow.is(Blocks.MYCELIUM)) {
                rootType = 2;
            } else if (blockBelow.is(Blocks.CRIMSON_NYLIUM) ||  blockBelow.is(Blocks.CRIMSON_HYPHAE) || blockBelow.is(Blocks.CRIMSON_STEM) || blockBelow.is(Blocks.NETHER_WART_BLOCK)) {
                rootType = 3;
            } else if (blockBelow.is(Blocks.WARPED_NYLIUM) ||  blockBelow.is(Blocks.WARPED_HYPHAE) || blockBelow.is(Blocks.WARPED_STEM) || blockBelow.is(Blocks.WARPED_WART_BLOCK)) {
                rootType = 4;
            }
        }
        FluidState fluidState = blockPlaceContext.getLevel().getFluidState(blockPlaceContext.getClickedPos());
        return super.getStateForPlacement(blockPlaceContext)
                .setValue(WATERLOGGED, fluidState.is(Fluids.WATER))
                .setValue(ROOT_TYPE, rootType);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return (new ItemStack(MossThorns.ENTANGLING_ROOTS_ITEM));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROOT_TYPE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl) {
        Vec3 vec3 = new Vec3(0.15, 0.05F, 0.15);
        if (entity instanceof LivingEntity livingEntity && livingEntity.getMainHandItem().is(MossThorns.MOSSTHORN_TAG)) {
            return;
        }
        entity.makeStuckInBlock(blockState, vec3);
    }
}
