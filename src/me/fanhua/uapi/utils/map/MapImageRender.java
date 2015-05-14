package me.fanhua.uapi.utils.map;

import java.awt.Image;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class MapImageRender extends MapRenderer {
	
	private Image image;
	private boolean draw;
	
	private byte[] data;
	private int width;
	private int height;
	
	public MapImageRender(Image image) {
		this.setImage(image);
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		image = MapPalette.resizeImage(image);
		this.data = MapPalette.imageToBytes(image);
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		
		this.redraw();
	}
	
	public void redraw() {
		this.draw = true;
	}
	
	@Override
	public void render(MapView view, MapCanvas canvas, Player player) {
		if (!this.draw) return;
		this.draw = false;
		
		for (int x = 0; x < this.width; x++)  for (int y = 0; y < this.height; y++) canvas.setPixel(x, y, this.data[x + y * this.height]);
	}
	
}
