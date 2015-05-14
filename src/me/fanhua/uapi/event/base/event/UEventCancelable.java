package me.fanhua.uapi.event.base.event;

public abstract class UEventCancelable implements UEvent, IEventCancelable {
	
	protected boolean cancelled;
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	@Override
	public void setCancelle(boolean canceled) {
		this.cancelled = canceled;
	}
	
}
