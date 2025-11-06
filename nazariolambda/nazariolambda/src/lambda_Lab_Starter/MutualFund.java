package lambda_Lab_Starter;


/**
 * Data describing a mutual fund or ETF
 * 
 * <p>
 * Note that avgHoldingSize is in millions of dollars
 * </p>
 * 
 * @author parks
 *
 */
public class MutualFund {
	
	private String ticker;
	private double avgHoldingSize;
	private double minimumInvestment;
	private double valueMeasure;
	private MARKET domicile;
	
	// An enum is a type that must have all of its possible
	// values listed explicitly. 
	// In this case, a MARKET value can be
	// MARKET.DOMESTIC or MARKET.INTERNATIONAL or MARKET.GLOBAL
	// no other values are possible.
	// You can compare enums with ==
	// So if 
	// MARKET mkt = MARKET.DOMESTIC;
	// mkt == MARKET.DOMESTIC  is true
	// mkt == MARKET.GLOBAL is false
	/**
	 * A mutual fund can invest in US stocks only (DOMESTIC),
	 * non-US stocks only (INTERNATIONAL),
	 * or both US and non-US stocks (GLOBAL)
	 * 
	 * @author parks
	 *
	 */
	public enum MARKET {DOMESTIC, INTERNATIONAL, GLOBAL};
	
	/**
	 * Constructor specifying everything about a mutual fund 
	 * (as far as we go...)
	 * 
	 * @param ticker mutual fund identifier
	 * 
	 * @param averageHoldingSize in millions of dollars, the average
	 * size of all companies held by this mutual fund
	 * 
	 * @param minimumInvestmentRequirement the smallest amount of money
	 * one can invest initially in a mutual fund.  For an ETF, this is the
	 * share price.
	 * 
	 * @param valueMeasure the "HML" value premium from  Fama-French.
	 * This stands for "high minus low."  Just like other things one can buy, 
	 * some brands command a higher price because they are perceived to be higher
	 * quality. One expects to pay more for a Lexus than for a Ford of equivalent
	 * size, for example.  The same thing happens with stocks, and 
	 * that affect can be measured.
	 * 
	 * @param location indicates is this fund buys US stocks only, non-US stocks
	 * only, or both US and non-US stocks.
	 */
	public MutualFund(String ticker, double averageHoldingSize,
			double minimumInvestmentRequirement, double valueMeasure,
			MARKET location) {
		this.ticker = ticker;
		avgHoldingSize = averageHoldingSize;
		minimumInvestment = minimumInvestmentRequirement;
		this.valueMeasure = valueMeasure;
		domicile = location;
	}

	public double getAvgHoldingSize() {
		return avgHoldingSize;
	}

	public void setAvgHoldingSize(double avgHoldingSize) {
		this.avgHoldingSize = avgHoldingSize;
	}

	public double getMinimumInvestment() {
		return minimumInvestment;
	}

	public void setMinimumInvestment(double minimumInvestment) {
		this.minimumInvestment = minimumInvestment;
	}

	public double getValueMeasure() {
		return valueMeasure;
	}

	public void setValueMeasure(double valueMeasure) {
		this.valueMeasure = valueMeasure;
	}

	public String getTicker() {
		return ticker;
	}

	public MARKET getDomicile() {
		return domicile;
	}

	@Override
	public String toString() {
		return "MutualFund [ticker=" + ticker + ", avgHoldingSize=" + avgHoldingSize + ", minimumInvestment="
				+ minimumInvestment + ", valueMeasure=" + valueMeasure + ", domicile=" + domicile + "]";
	}

}
