package delegations.calculation.model;

import java.math.BigDecimal;

public class Diet {
	public Diet(String countryCode, String currencyCode, BigDecimal value) {
		this.countryCode = countryCode;
		this.currencyCode = currencyCode;
		this.value = value;
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
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	private String currencyCode;
	private String countryCode;
	private BigDecimal value;

}
