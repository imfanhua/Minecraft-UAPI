package me.fanhua.uapi.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import me.fanhua.uapi.event.player.PlayerReceivePacket;
import me.fanhua.uapi.event.player.PlayerSendPacket;
import me.fanhua.uapi.manager.EventManager;
import me.fanhua.uapi.utils.ClassUtils;

import org.bukkit.entity.Player;

public class NetworkInjector {
	
	private static Method methodDecode;
	private static Method methodEncode;
	
	static {
		try {
			NetworkInjector.methodDecode = ClassUtils.getMethod(ClassUtils.getServerClass("PacketDecoder"), "decode", true, ChannelHandlerContext.class, ByteBuf.class, List.class);
			NetworkInjector.methodEncode = ClassUtils.getMethod(ClassUtils.getServerClass("PacketEncoder"), "encode", true, ChannelHandlerContext.class, Object.class, ByteBuf.class);
		} catch (Throwable error) {}
	}
	
	private Player player;
	private Channel proxy;
	
	private ByteToMessageDecoder objectDecoder;
	private MessageToByteEncoder objectEncoder;
	
	public NetworkInjector(Player player, Channel proxy) {
		this.player = player;
		this.proxy = proxy;
	}
	
	public Channel init() {
		ChannelPipeline pipeline = this.proxy.pipeline();
		
		this.objectDecoder = (ByteToMessageDecoder) pipeline.get("decoder");
		this.objectEncoder = (MessageToByteEncoder) pipeline.get("encoder");
		
		pipeline.addBefore("decoder", "_decoder", new ByteToMessageDecoder() {

			@Override
			protected void decode(ChannelHandlerContext context, ByteBuf buffer, List<Object> packets) throws Exception {
				NetworkInjector.methodDecode.invoke(NetworkInjector.this.objectDecoder, context, buffer, packets);
				
				Iterator<?> iterator = packets.iterator();
				while (iterator.hasNext()) {
					Object packet = iterator.next();
					PlayerSendPacket event = new PlayerSendPacket(NetworkInjector.this.player, packet);
					EventManager.call(event);
					if (event.isCancelled()) iterator.remove();
				}
			}
			
		});
		
		pipeline.addAfter("encoder", "_encoder", new MessageToByteEncoder() {

			@Override
			protected void encode(ChannelHandlerContext context, Object packet, ByteBuf buffer) throws Exception {
				if (packet == null) return;
				
				PlayerReceivePacket event = new PlayerReceivePacket(NetworkInjector.this.player, packet);
				EventManager.call(event);
				if (!event.isCancelled()) NetworkInjector.methodEncode.invoke(NetworkInjector.this.objectEncoder, context, packet, buffer);
			}
			
		});
		
		return this.proxy;
	}
	
}
