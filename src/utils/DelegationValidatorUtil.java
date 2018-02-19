package utils;

import java.util.List;

import delegations.calculation.Delegation;
import delegations.calculation.model.DelegationPoint;
import exceptions.DelegationsException;

public class DelegationValidatorUtil {
	private DelegationValidatorUtil() {
	}

	public static void checkIfEndpointIsTheSameCountry(List<DelegationPoint> delegationPoints, DelegationPoint endPoint)
			throws DelegationsException {
		if (endPoint != null && delegationPoints.size() > 1 && !delegationPoints.get(delegationPoints.size() - 2)
				.getIntoCountry().equals(endPoint.getIntoCountry())) {
			throw new DelegationsException("before endPoint should be the same country!");
		}
	}

	public static void checkIfDelegationIsEmpty(List<DelegationPoint> allDelegationPoints) throws DelegationsException {
		if (allDelegationPoints.isEmpty()) {
			throw new DelegationsException("Please at least one delegation point");
		}
	}

	public static void validateJSON(Delegation delegation) throws DelegationsException {
		if(delegation == null){
			throw new DelegationsException("Delegation cannot be empty!");
		}
		
		if(delegation.getStartPoint() == null){
			throw new DelegationsException("In each delegation should be startPoint declared!");
		}
		
	}

}
