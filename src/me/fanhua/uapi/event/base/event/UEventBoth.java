package me.fanhua.uapi.event.base.event;

public abstract class UEventBoth implements UEvent, IEventCancelable, IEventContinuable {
	
	protected boolean cancelled;
	protected boolean isContinue = true;
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	@Override
	public void setCancelle(boolean canceled) {
		this.cancelled = canceled;
	}
	
	@Override
	public boolean isContinue() {
		return this.isContinue;
	}
	
	@Override
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	
}
