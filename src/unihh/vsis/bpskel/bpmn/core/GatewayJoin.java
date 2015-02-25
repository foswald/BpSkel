package unihh.vsis.bpskel.bpmn.core;

public abstract class GatewayJoin extends AbstractFlowObject implements IGateway{
	
	private IFlowObject pred2;

	public IFlowObject getPredecessor2() {
		return pred2;
	}

	public void setPredecessor2(IFlowObject pred2) {
		this.pred2 = pred2;
	}
	
	public void resetPredecessor2() {
		this.pred2 = null;
	}

	@Override
	public boolean isEmpty(){
		return super.isEmpty() && pred2==null;
	}
}
