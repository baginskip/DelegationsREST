package currencies.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
	private String currency; //"dolar ameryka�ski",
	private String code; //"USD",
	private BigDecimal mid; //: 3.3994
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getMid() {
		return mid;
	}
	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}
}
