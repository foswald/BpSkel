package bpskel.bpg.api;


public interface IProcessEngine {
	boolean initialize();
	void execute(BusinessProcess pro);
}
