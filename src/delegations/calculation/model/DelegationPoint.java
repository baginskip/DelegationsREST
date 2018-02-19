package delegations.calculation.model;

import java.util.Date;

import utils.DateUtil;

public class DelegationPoint {
	private String intoCountry;
	private Date intoTime;
	private boolean lastPoint;

	public String getIntoCountry() {
		return intoCountry;
	}

	public void setIntoCountry(String intoCountry) {
		this.intoCountry = intoCountry;
	}

	public Date getIntoTime() {
		return intoTime;
	}

	public void setIntoTime(String intoTime) {
		this.intoTime = DateUtil.convertToDate(intoTime);
	}

	public boolean isLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(boolean lastPint) {
		this.lastPoint = lastPint;
	}
	
	public boolean isEndedPoint() {
		return endedPoint;
	}

	public void setEndedPoint(boolean endedPoint) {
		this.endedPoint = endedPoint;
	}

	private boolean endedPoint;
	
}