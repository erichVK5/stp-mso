package stpmso;

import java.util.Comparator;

public class BottleneckComparator implements Comparator<MinimalSpanningTree> {

	@Override
	public int compare(MinimalSpanningTree mst0, MinimalSpanningTree mst1) {
		return Double.compare(	mst0.getSqBottleneck(),
								mst1.getSqBottleneck());
	}

}