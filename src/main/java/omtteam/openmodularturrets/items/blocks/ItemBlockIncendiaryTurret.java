package omtteam.openmodularturrets.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import omtteam.openmodularturrets.handler.ConfigHandler;
import omtteam.openmodularturrets.reference.OMTNames;
import omtteam.openmodularturrets.reference.Reference;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.DecimalFormat;
import java.util.List;

import static omtteam.omlib.util.GeneralUtil.safeLocalize;

@SuppressWarnings("deprecation")
public class ItemBlockIncendiaryTurret extends ItemBlockBaseAddon {
    private static final DecimalFormat df = new DecimalFormat("0.0");

    public ItemBlockIncendiaryTurret(Block block) {
        super(block);
        this.setRegistryName(Reference.MOD_ID, OMTNames.Blocks.incendiaryTurret);
    }

    @Override
    @SuppressWarnings("unchecked")
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        tooltip.add(safeLocalize(OMTNames.Localizations.GUI.TURRET_HEAD_DESCRIPTION));
        tooltip.add("");
        tooltip.add(TextFormatting.GOLD + "--" + safeLocalize("tooltip.info") + "--");
        tooltip.add(safeLocalize("tooltip.tier") + ": " + TextFormatting.WHITE + "2");
        tooltip.add(safeLocalize("tooltip.range") + ": " + TextFormatting.WHITE +
                ConfigHandler.getIncendiaryTurretSettings().getRange());
        tooltip.add(safeLocalize("tooltip.accuracy") + ": " + TextFormatting.WHITE +
                safeLocalize("turret.accuracy.medium"));
        tooltip.add(safeLocalize("tooltip.ammo") + ": " + TextFormatting.WHITE +
                safeLocalize("turret.ammo.7"));
        tooltip.add(safeLocalize("tooltip.tier_required") + ": " + TextFormatting.WHITE +
                safeLocalize("base.tier.2"));
        tooltip.add("");
        tooltip.add(
                TextFormatting.DARK_PURPLE + "--" + safeLocalize("tooltip.damage.label") + "--");
        tooltip.add(safeLocalize("tooltip.damage.stat") + ": " + TextFormatting.WHITE +
                (ConfigHandler.getIncendiaryTurretSettings().getDamage() / 2F) + " " + safeLocalize(
                "tooltip.health"));
        tooltip.add(safeLocalize("tooltip.aoe") + ": " + TextFormatting.WHITE + "5");
        tooltip.add(safeLocalize("tooltip.fire_rate") + ": " + TextFormatting.WHITE + df.format(
                20.0F / ConfigHandler.getIncendiaryTurretSettings().getFireRate()));
        tooltip.add(safeLocalize("tooltip.energy.stat") + ": " + TextFormatting.WHITE +
                ConfigHandler.getIncendiaryTurretSettings().getPowerUsage() + " RF");
        tooltip.add("");
        tooltip.add(TextFormatting.DARK_GRAY + safeLocalize("flavour.turret.7"));
    }
}
