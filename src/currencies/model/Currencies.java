package currencies.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currencies {
	private String table;         // "A"
	private String no;            // "014/A/NBP/2018",
	private String effectiveDate; // "2018-01-19",
	private List<Rate> rates;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getNo() {

		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
}
