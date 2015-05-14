package me.fanhua.uapi.event.base.event;

public abstract class UEventContinuable implements UEvent, IEventContinuable {
	
	protected boolean isContinue = true;
	
	@Override
	public boolean isContinue() {
		return this.isContinue;
	}
	
	@Override
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	
}
