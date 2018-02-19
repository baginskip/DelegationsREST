package delegations.calculation;

import java.util.ArrayList;
import java.util.List;

import delegations.calculation.model.DelegationPoint;
import exceptions.DelegationsException;
import utils.DelegationValidatorUtil;

public class Delegation {
	private DelegationPoint startPoint;
	private List<DelegationPoint> borderPoints;
	private DelegationPoint endPoint;
	
	private List<DelegationPoint> sumDelegationPoints = new ArrayList<>();
	
	public List<DelegationPoint> prepareDelegationRoad() throws DelegationsException {
		sumDelegationPoints = new ArrayList<>();
		if (startPoint != null) {
			sumDelegationPoints.add(startPoint);
		}
		if (borderPoints != null && !borderPoints.isEmpty()) {
			sumDelegationPoints.addAll(borderPoints);
		}
		if (endPoint != null) {
			endPoint.setEndedPoint(true);
			sumDelegationPoints.add(endPoint);
		}
		markLastDelegationPoint(sumDelegationPoints);
		validateExtraInput();
		return sumDelegationPoints;
	}

	private void markLastDelegationPoint(List<DelegationPoint> sumDelegationPoints) {
		sumDelegationPoints.get(sumDelegationPoints.size() - 1).setLastPoint(true);
	}

	private void validateExtraInput() throws DelegationsException {
		DelegationValidatorUtil.checkIfDelegationIsEmpty(sumDelegationPoints);
		DelegationValidatorUtil.checkIfEndpointIsTheSameCountry(sumDelegationPoints, endPoint);
	}
	
	public DelegationPoint getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(DelegationPoint startPoint) {
		this.startPoint = startPoint;
	}

	public List<DelegationPoint> getBorderPoints() {
		return borderPoints;
	}

	public void setBorderPoints(List<DelegationPoint> borderPoints) {
		this.borderPoints = borderPoints;
	}

	public DelegationPoint getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(DelegationPoint endPoint) {
		this.endPoint = endPoint;
	}
}