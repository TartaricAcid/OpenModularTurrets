package openmodularturrets.network.messages;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import openmodularturrets.tileentity.turretbase.TurretBase;

public class MessageSetBaseTargetingType implements IMessage {
    private int x, y, z;

    public MessageSetBaseTargetingType() {
    }

    public static class MessageHandlerSetBaseTargetingType implements IMessageHandler<MessageSetBaseTargetingType, IMessage> {
        @Override
        public IMessage onMessage(MessageSetBaseTargetingType message, MessageContext ctx) {
            World world = ctx.getServerHandler().playerEntity.worldObj;
            TurretBase turretbase = (TurretBase) world.getTileEntity(message.getX(), message.getY(), message.getZ());
            turretbase.setMultiTargeting(!turretbase.isMultiTargeting());
            return null;
        }
    }

    public MessageSetBaseTargetingType(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    private int getZ() {
        return z;
    }
}