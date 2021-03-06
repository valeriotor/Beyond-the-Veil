package com.valeriotor.beyondtheveil.shoggoth;

import com.valeriotor.beyondtheveil.gui.GuiCityMapper;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class FlatBuilding {
	
	public static FlatBuilding getFromNBT(NBTTagCompound nbt) {
		int index = nbt.getInteger("index");
		if(BuildingRegistry.templates[index].longBuilding) return new FlatLongBuilding(nbt);
		return new FlatBaseBuilding(nbt);
	}
	
	public static FlatBuilding getFromIndex(int index) {
		if(BuildingRegistry.templates[index].longBuilding) return new FlatLongBuilding(index);
		return new FlatBaseBuilding(index);
	}
	
	public static FlatBuilding getFromTemplate(BuildingTemplate template) {
		if(template.longBuilding) return new FlatLongBuilding(template);
		return new FlatBaseBuilding(template);
	}
	
	protected final BuildingTemplate building;
	public int centerX = 0;
	public int centerY = 0;
	public int rotation = 0; // 0 = North, 1 = East, 2 = South, 3 = West. Defines door of the building
	
	public FlatBuilding(int index) {
		this.building = BuildingRegistry.templates[index];
	}
	
	public FlatBuilding(BuildingTemplate template) {
		this.building = template;
	}
	
	public FlatBuilding(NBTTagCompound nbt) {
		int index = nbt.getInteger("index");
		//if(index >= 0) 	// for later use with custom buildings, where this.building = player.getcapability.sumthin' (capability will only instantiate 
							// the custombuildings when asked to, and will keep them stored
		this.building = BuildingRegistry.templates[index];
		this.setCenter(nbt.getInteger("centerX"), nbt.getInteger("centerY"));
		this.rotation = nbt.getInteger("rot");
	}
	
	public int getWidth() {
		return (rotation & 1) == 0 ? building.width : building.height;
	}
	
	public int getHeight() {
		return (rotation & 1) == 1 ? building.width : building.height;
	}

	public int getIndex() {
		return this.building.index;
	}
	
	public int top() {return this.centerY - this.getHeight()/2;}
	public int left() {return this.centerX - this.getWidth()/2;}
	public int bottom() {return this.centerY + this.getHeight()/2;}
	public int right() {return this.centerX + this.getWidth()/2;}
	
	public void setCenter(int x, int y) {
		this.centerX = x;
		this.centerY = y;
	}
	
	public boolean intersects(FlatBuilding hover, int centerX, int centerY) {
		if(hover == this) return false; // A building can intersect itself
		int hwidth = hover.getWidth();
		int hheight = hover.getHeight();
		int hTop = centerY - hheight/2;
		int hLeft = centerX - hwidth/2;
		int hBottom = centerY + hheight/2;
		int hRight = centerX + hwidth/2;
		
		return intersects(hover, hTop, hLeft, hBottom, hRight);
	}
	
	public boolean intersects(FlatBuilding hover, int hTop, int hLeft, int hBottom, int hRight) {
		if(hover == this) return false; // A building can intersect itself
		int top = top(), left = left(), bottom = bottom(), right = right();
		if (hLeft > hRight)
        {
            int i = hLeft;
            hLeft = hRight;
            hRight = i;
        }

        if (hTop > hBottom)
        {
            int j = hTop;
            hTop = hBottom;
            hBottom = j;
        }
		if((hTop < top && hBottom >= top || hBottom > bottom && hTop <= bottom || hBottom < bottom && hTop > top)
		&& (hLeft < left && hRight >= left || hRight > right && hLeft <= right || hRight < right && hLeft > left)) {
			return true;
		}
		return false;
	}
	
	public boolean containsPoint(int x, int y) {
		if(x >= left() && x <= right() && y >= top() && y <= bottom()) return true;
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public String getLocalizedName() {
		return this.building.getLocalizedName();
	}

	@SideOnly(Side.CLIENT)
	public void render(GuiCityMapper gui) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((gui.width/2 - 115) + centerX, (gui.height/2 - 100) + centerY, 0);
		GlStateManager.rotate(this.rotation * 90, 0, 0, 1);
		GlStateManager.translate(-16, -16, 0);
		building.drawScaledTexture(gui, 0, 0, 1);
		GlStateManager.popMatrix();
	}
	
	@SideOnly(Side.CLIENT)
	public void highlight(GuiCityMapper gui) {
		GlStateManager.pushMatrix();
		gui.translate();
		int top = top(), left = left(), bottom = bottom(), right = right();
		gui.drawVerticalLine(left, top, bottom, 0xFFFFFFFF);
		gui.drawVerticalLine(right, top, bottom, 0xFFFFFFFF);
		gui.drawHorizontalLine(left, right, top, 0xFFFFFFFF);
		gui.drawHorizontalLine(left, right, bottom, 0xFFFFFFFF);
		GlStateManager.popMatrix();
	}
	
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("index", this.building.index);
		nbt.setInteger("centerX", this.centerX);
		nbt.setInteger("centerY", this.centerY);
		nbt.setInteger("rot", this.rotation);
		return nbt;
	}
	
	public NBTTagCompound writeToNBTCorrected(NBTTagCompound nbt, BlockPos pos) {
		nbt.setInteger("index", this.building.index);
		nbt.setInteger("centerX", this.centerX + pos.getX() - 100);
		nbt.setInteger("centerZ", this.centerY + pos.getZ() - 100);
		nbt.setInteger("rot", this.rotation);
		return nbt;
	}
	
}
