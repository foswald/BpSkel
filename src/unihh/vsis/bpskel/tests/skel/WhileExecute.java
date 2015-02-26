package unihh.vsis.bpskel.tests.skel;

import bpskel.bpg.api.IDataContainer;
import bpskel.bpg.impl.core.AbstractTask;
import cl.niclabs.skandium.muscles.Execute;

public class WhileExecute<P extends IDataContainer, R extends IDataContainer> extends AbstractTask implements Execute<P, R> {

	IDataContainer integerData;
	
	public WhileExecute(IDataContainer in){
		integerData = in;
	}
	
	@Override
	public R execute(P p) throws Exception {
		Integer intP = (Integer) p.getData();
		intP++;
		System.out.println(intP);
		p.setData(intP);
		return ((R)p);
	}

	@Override
	public void setInputData(IDataContainer in) {
		this.integerData = in;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		try {
			this.execute((P)integerData.getData());
		} catch (Exception e) {
			System.out.println("Cast failed");
			e.printStackTrace();
		}
		
	}

	@Override
	public IDataContainer getResultData() {
		// TODO Auto-generated method stub
		return integerData;
	}
}