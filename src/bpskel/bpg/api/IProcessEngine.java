package bpskel.bpg.api;


public interface IProcessEngine {
	boolean initialize();
	void execute(BusinessProcessGraph pro);
}
