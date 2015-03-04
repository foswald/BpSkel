package bpskel.api;


public interface IProcessEngine {
	int execute(IBPG pro);
	
	void setNumThreads(int numThreads);
}
