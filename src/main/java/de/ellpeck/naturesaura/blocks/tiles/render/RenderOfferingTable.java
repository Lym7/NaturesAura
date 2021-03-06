package de.ellpeck.naturesaura.blocks.tiles.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.ellpeck.naturesaura.blocks.tiles.TileEntityOfferingTable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class RenderOfferingTable extends TileEntityRenderer<TileEntityOfferingTable> {

    private final Random rand = new Random();

    public RenderOfferingTable(TileEntityRendererDispatcher disp) {
        super(disp);
    }

    @Override
    public void render(TileEntityOfferingTable tileEntityOfferingTable, float v, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        ItemStack stack = tileEntityOfferingTable.items.getStackInSlot(0);
        if (!stack.isEmpty()) {
            this.rand.setSeed(Item.getIdFromItem(stack.getItem()) + stack.getDamage());

            int amount = MathHelper.ceil(stack.getCount() / 2F);
            for (int i = 0; i < amount; i++) {
                matrixStack.push();
                Item item = stack.getItem();

                float scale;
                float yOff;
                if (item instanceof BlockItem) {
                    scale = 0.5F;
                    yOff = 0.08F;
                } else {
                    scale = 0.35F;
                    yOff = 0F;
                }

                matrixStack.translate(
                        0.35F + this.rand.nextFloat() * 0.3F,
                        0.9F + yOff + i * 0.001F,
                        0.35F + this.rand.nextFloat() * 0.3F);
                matrixStack.rotate(Vector3f.YP.rotationDegrees(this.rand.nextFloat() * 360));
                matrixStack.rotate(Vector3f.XP.rotationDegrees(90F));
                matrixStack.scale(scale, scale, scale);

                Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.GROUND, combinedLightIn, combinedOverlayIn, matrixStack, iRenderTypeBuffer);
                matrixStack.pop();
            }
        }
    }
}
