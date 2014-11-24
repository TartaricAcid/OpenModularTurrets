package openmodularturrets.blocks.turretbases;

import openmodularturrets.ModInfo;
import openmodularturrets.blocks.BlockNames;
import openmodularturrets.misc.ConfigHandler;
import openmodularturrets.tileentity.turretBase.TurretBaseTierFourTileEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTurretBaseTierFour extends BlockAbstractTurretBase {

    public final int MaxCharge = ConfigHandler.getBaseTierFourMaxCharge();
    public final int MaxIO = ConfigHandler.getBaseTierFourMaxIo();

    public BlockTurretBaseTierFour() {
        super();

        this.setBlockName(BlockNames.unlocalisedTurretBaseTierFour);
        this.setBlockTextureName(ModInfo.ID + ":turretBaseTierFour");
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        super.registerBlockIcons(p_149651_1_);

	    blockIcon = p_149651_1_.registerIcon(ModInfo.ID.toLowerCase() + ":turretBaseTierFour");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int par2) {
	    return new TurretBaseTierFourTileEntity(this.MaxCharge, this.MaxIO);
    }
}