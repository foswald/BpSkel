package bpskel.engine.skeleton.api;

import bpskel.api.BusinessProcessGraph;
import bpskel.api.IProcessEngine;
import bpskel.bpg.conversion.BlockstructureConverter;
import bpskel.bpg.conversion.pattern.ProxyTask;

public class SkeletonProcessEngine implements IProcessEngine{

	private ISkeletonAPI skeletonApi;
	private BlockstructureConverter conv;
	public SkeletonProcessEngine(Class<? extends ISkeletonAPI> skeletonApi){
		try {
			this.skeletonApi = skeletonApi.newInstance();
			this.conv = new BlockstructureConverter(this.skeletonApi);		
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute(BusinessProcessGraph pro) {

		ProxyTask root = this.conv.convert(pro);
		System.out.println("Conversion finished.");
		
		// now start the skeleton application
		this.skeletonApi.execute(root.getSkeletonReference());
			
	}

}
