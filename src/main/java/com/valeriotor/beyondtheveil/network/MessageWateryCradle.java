package com.valeriotor.beyondtheveil.network;

import java.util.List;

import com.valeriotor.beyondtheveil.blocks.BlockRegistry;
import com.valeriotor.beyondtheveil.events.special.CrawlerWorshipEvents;
import com.valeriotor.beyondtheveil.items.ItemRegistry;
import com.valeriotor.beyondtheveil.lib.BTVSounds;
import com.valeriotor.beyondtheveil.research.ResearchUtil;
import com.valeriotor.beyondtheveil.tileEntities.TileWateryCradle;
import com.valeriotor.beyondtheveil.tileEntities.TileWateryCradle.PatientStatus;
import com.valeriotor.beyondtheveil.tileEntities.TileWateryCradle.PatientTypes;
import com.valeriotor.beyondtheveil.util.SyncUtil;
import com.valeriotor.beyondtheveil.worship.CrawlerWorship;
import com.valeriotor.beyondtheveil.worship.CrawlerWorship.WorshipType;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.items.ItemHandlerHelper;

public class MessageWateryCradle implements IMessage{
	
	public byte option = 0;
	public int x = 0;
	public int y = 0;
	public int z = 0;
	
	public MessageWateryCradle() {}
	
	public MessageWateryCradle(byte option, int x, int y, int z) {
		this.option = option;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.option = buf.readByte();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(option);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class WateryCradleMessageHandler implements IMessageHandler<MessageWateryCradle, IMessage>{

		@Override
		public IMessage onMessage(MessageWateryCradle message, MessageContext ctx) {
			
			BlockPos pos = new BlockPos(message.x, message.y, message.z);
			EntityPlayerMP p = ctx.getServerHandler().player;
			p.getServerWorld().addScheduledTask(() -> {
				World w = p.world;
				TileWateryCradle te = (TileWateryCradle) w.getTileEntity(pos);
				if(te != null) {
					PatientStatus status = te.getPatientStatus();
					SoundEvent sound = null;
					switch(message.option) {
					case 0: status = status.withSpineless(true);
							ItemHandlerHelper.giveItemToPlayer(p, new ItemStack(ItemRegistry.spine));
							sound = BTVSounds.spineRip;
							if(ResearchUtil.getResearchStage(p, "SPINES") < 1)
								SyncUtil.addStringDataOnServer(p, false, "extractedspine");
							break;
					case 1: status = status.withPatient(PatientTypes.WEEPER);
							if(ResearchUtil.getResearchStage(p, "WEEPERS") < 1)
								SyncUtil.addStringDataOnServer(p, false, "filledtears");
							break;
					case 3: status = status.withHeartless(true);
							int amount = 1;
							CrawlerWorship cw = CrawlerWorshipEvents.getWorship(p);
							if(cw != null && cw.getWorshipType() == WorshipType.SACRIFICE) {
								if(p.world.rand.nextInt(Math.max(1, 5-cw.getStrength())) == 0)
									amount = 2;
							}
							ItemHandlerHelper.giveItemToPlayer(p, new ItemStack(BlockRegistry.BlockHeart, amount));
							sound = BTVSounds.heartRip;
							if(ResearchUtil.getResearchStage(p, "HEARTS") < 1)
								SyncUtil.addStringDataOnServer(p, false, "tornheart");
							break;
					}
					
					w.playSound(pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, 1, 1, false);
					te.setPatient(status);
					List<EntityPlayerMP> players = w.getPlayers(EntityPlayerMP.class, player -> player.getDistanceSq(pos) < 100);
					if(sound != null)
						for(EntityPlayerMP player : players) 
							BTVPacketHandler.INSTANCE.sendTo(new MessagePlaySound(BTVSounds.getIdBySound(sound), pos.toLong()), player);
				}
			});
			return null;
		}
		
	}

}
	
