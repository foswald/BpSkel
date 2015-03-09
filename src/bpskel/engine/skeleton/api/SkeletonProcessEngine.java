package bpskel.engine.skeleton.api;

import bpskel.api.IBPG;
import bpskel.api.IProcessEngine;
import bpskel.bpg.conversion.BlockstructureConverter;
import bpskel.bpg.conversion.pattern.ProxyTask;
import bpskel.engine.skeleton.impl.skandium.SkandiumConnector;

public class SkeletonProcessEngine implements IProcessEngine{

	private ISkeletonAPI skeletonApi;
	private BlockstructureConverter conv;
	
	public SkeletonProcessEngine(){
		try {
			this.skeletonApi = getDefaultExecutor().newInstance();
			this.conv = new BlockstructureConverter(this.skeletonApi);		
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SkeletonProcessEngine(Class<? extends ISkeletonAPI> skeletonApi){
		try {
			this.skeletonApi = skeletonApi.newInstance();
			this.conv = new BlockstructureConverter(this.skeletonApi);		
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Class<? extends ISkeletonAPI> getDefaultExecutor(){
		return SkandiumConnector.class;
	}
	
	@Override
	public int execute(IBPG pro) {

		ProxyTask root = this.conv.convert(pro);
		System.out.println("BPG Conversion finished, starting execution");
		
		// now start the skeleton application
		return this.skeletonApi.execute(root.getSkeletonReference());
			
	}

	@Override
	public void setNumThreads(int numThreads) {
		this.skeletonApi.setNumThreads(numThreads);		
	}

}
