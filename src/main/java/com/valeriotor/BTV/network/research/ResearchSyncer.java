package com.valeriotor.BTV.network.research;

import com.valeriotor.BTV.research.ResearchRegistry;
import com.valeriotor.BTV.research.ResearchStatus;

import net.minecraft.nbt.NBTTagCompound;

public class ResearchSyncer {
	
	public String key;
	public boolean learn = false;
	public boolean progress = false;
	public ResearchStatus status;
	
	public ResearchSyncer() {}
	
	public ResearchSyncer(String key) {
		this.key = key;
	}
	
	public ResearchSyncer setLearn(boolean set) {
		this.learn = set;
		return this;
	}
	
	public ResearchSyncer setProgress(boolean set) {
		this.progress = set;
		return this;
	}
	
	public ResearchSyncer setStatus(ResearchStatus status) {
		this.status = status;
		return this;
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setString("key", key);
		nbt.setBoolean("learn", learn);
		nbt.setBoolean("progress", progress);
		if(status != null) {
			nbt.setTag("res", this.status.writeToNBT(new NBTTagCompound()));
		}
		return nbt;
	}
	
	public ResearchSyncer readFromNBT(NBTTagCompound nbt) {
		this.key = nbt.getString("key");
		this.learn = nbt.getBoolean("learn");
		this.progress = nbt.getBoolean("progress");
		if(nbt.hasKey("res")) {
			this.status = new ResearchStatus(ResearchRegistry.researches.get(this.key)).readFromNBT((NBTTagCompound) nbt.getTag("res"));
		}
		return this;
	}
	
}