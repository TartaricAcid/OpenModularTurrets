package modularTurrets.blocks.turretbases;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import modularTurrets.ModInfo;
import modularTurrets.ModularTurrets;
import modularTurrets.blocks.BlockNames;
import modularTurrets.misc.ConfigHandler;
import modularTurrets.network.SetTurretOwnerMessage;
import modularTurrets.tileentity.turretBase.TurretBase;
import modularTurrets.tileentity.turretBase.TurretWoodBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.Random;

public class BlockTurretBaseTierWood extends BlockAbstractTurretBase {

    public final int MaxCharge = ConfigHandler.getBaseTierWoodMaxCharge();
    public final int MaxIO = ConfigHandler.getBaseTierWoodMaxIo();

    public BlockTurretBaseTierWood() {
        super();

        this.setBlockName(BlockNames.unlocalisedTurretBaseTierWood);
        this.setBlockTextureName(ModInfo.ID + ":turretBaseTierWood");
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        super.registerBlockIcons(p_149651_1_);

        blockIcon = p_149651_1_.registerIcon(ModInfo.ID.toLowerCase() + ":turretBaseTierWood");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
	    return new TurretWoodBase(this.MaxCharge, this.MaxIO);
    }
}
