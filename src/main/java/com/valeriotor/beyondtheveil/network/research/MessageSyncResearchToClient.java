package com.valeriotor.beyondtheveil.network.research;

import com.valeriotor.beyondtheveil.capabilities.ResearchProvider;
import com.valeriotor.beyondtheveil.research.ResearchUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncResearchToClient implements IMessage{
	
public ResearchSyncer sync;
	
	public MessageSyncResearchToClient() {}
	
	public MessageSyncResearchToClient(ResearchSyncer sync) {
		this.sync = sync;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.sync = new ResearchSyncer().readFromNBT(ByteBufUtils.readTag(buf));	
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, this.sync.writeToNBT(new NBTTagCompound()));		
	}
	
	public static class SyncResearchToClientMessageHandler implements IMessageHandler<MessageSyncResearchToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageSyncResearchToClient message, MessageContext ctx) {
			Minecraft mc = Minecraft.getMinecraft();
			mc.addScheduledTask(() -> {
				ResearchSyncer sync = message.sync;
				sync.processClient();
			});
			return null;
		}
		
	}
	
}
