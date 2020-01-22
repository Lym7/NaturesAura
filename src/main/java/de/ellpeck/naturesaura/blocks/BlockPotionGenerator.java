package de.ellpeck.naturesaura.blocks;

import de.ellpeck.naturesaura.blocks.tiles.ModTileEntities;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityPotionGenerator;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPotionGenerator extends BlockContainerImpl {
    public BlockPotionGenerator() {
        super("potion_generator", TileEntityPotionGenerator::new, ModBlocks.prop(Material.ROCK).hardnessAndResistance(5F).harvestTool(ToolType.PICKAXE).harvestLevel(1));
    }
}
