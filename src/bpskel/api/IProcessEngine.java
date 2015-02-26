package bpskel.api;


public interface IProcessEngine {
	boolean initialize();
	void execute(BusinessProcessGraph pro);
}
