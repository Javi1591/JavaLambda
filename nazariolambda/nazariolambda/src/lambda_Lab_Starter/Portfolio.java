package lambda_Lab_Starter;


import java.util.ArrayList;
import java.util.Arrays;

public class Portfolio extends ArrayList<MutualFund> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Portfolio() {
		addAll(Arrays.asList(FundsDef.INITIAL_FUNDS));
	}
	
	public void refresh() {
		clear();
		addAll(Arrays.asList(FundsDef.INITIAL_FUNDS));
	}

}
