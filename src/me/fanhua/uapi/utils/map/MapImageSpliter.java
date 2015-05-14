package me.fanhua.uapi.utils.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import me.fanhua.uapi.item.builder.ItemBuilder;
import me.fanhua.uapi.manager.MapManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.map.MapView;

public class MapImageSpliter {
	
	public static final Color DEFAULTS = new Color(188, 152, 98);
	
	private int startId;
	
	private int width;
	private int height;
	
	private World world;
	
	private boolean split;
	
	public MapImageSpliter(int startId, int width, int height) {
		this(startId, width, height, Bukkit.getWorlds().get(0));
	}
	
	public MapImageSpliter(int startId, int width, int height, World world) {
		this.startId = startId;
		
		this.width = width;
		this.height = height;
		
		this.world = world;
	}
	
	public int getStartId() {
		return this.startId;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getUseIdAmount() {
		return this.width * this.height;
	}
	
	public int getMapId(int x, int y) {
		return this.startId + x + this.width * y;
	}
	
	public ItemBuilder getMapItem(int x, int y) {
		return MapManager.getInstance().getMapItem(this.getMapId(x, y));
	}
	
	public MapImageSpliter split(Image data) {
		return this.split(data, null);
	}
	
	public MapImageSpliter split(Image data, Color color) {
		if (this.split) return this;
		this.split = true;
		
        BufferedImage image = this.format(data, color);
        
		MapManager manager = MapManager.getInstance();
		int width = image.getWidth(null) / this.width;
		int height = image.getHeight(null) / this.height;
		
		for (int x = 0; x < this.width; x++) for (int y = 0; y < this.height; y++) {
			MapView map = manager.newMap(this.getMapId(x, y), this.world);
			manager.clearRender(map);
			map.addRenderer(new MapImageRender(image.getSubimage(x * width, y * height, width, height)));
		}
		
		return this;
	}
	
	private BufferedImage format(Image image, Color color) {
		int width = this.width * 128;
		int height = this.height * 128;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		float size = 1;
		
		while (true) {
			size += 0.1;
			if (imageWidth * size >= width) break;
			if (imageHeight * size >= height) break;
		}
		
		size -= 0.1;
		imageWidth *= size;
		imageHeight *= size;
		
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = result.createGraphics();
        if (color != null) {
        	graphics.setColor(color);
        	graphics.fillRect(8, 8, width - 16, height - 16);
        }
        graphics.drawImage(image, (width - imageWidth) / 2, (height - imageHeight) / 2, imageWidth, imageHeight, null);
        graphics.dispose();
        return result;
	}
	
}
