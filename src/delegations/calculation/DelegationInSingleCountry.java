package delegations.calculation;

import java.util.Date;

import delegations.calculation.model.DelegationPoint;
import utils.CurrencyUtil;
import utils.DateUtil;

public class DelegationInSingleCountry {
	private String currencyCode;
	private String countryCode;
	private Long timeSpent = 0L;

	public DelegationInSingleCountry(String countryCode) {
		this.countryCode = countryCode;
		this.setCurrencyCode(CurrencyUtil.findCrrencyCode(countryCode));
	}

	public DelegationInSingleCountry(DelegationPoint dp, DelegationPoint dpNext) {
		this(dp.getIntoCountry());
		if (dpNext != null) {
			this.addTimeSpent(DateUtil.secondsBetween(dp.getIntoTime(), dpNext.getIntoTime()));
		}else if(!dp.isEndedPoint()){
			this.addTimeSpent(DateUtil.secondsBetween(dp.getIntoTime(), new Date()));
		}
			
	}

	public void addTimeSpent(Long timeSpent) {
		this.timeSpent += timeSpent;
	}

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void addTimeSpent(DelegationInSingleCountry dci) {
		this.timeSpent += dci.timeSpent;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
