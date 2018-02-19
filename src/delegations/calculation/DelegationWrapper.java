package delegations.calculation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import delegations.calculation.model.DelegationPoint;
import exceptions.DelegationsException;

public class DelegationWrapper {
	private Date dateOfCalculation;
	private boolean endedDelegation = false;
	private List<DelegationPoint> allDelegationsPoints;
	private List<DelegationInSingleCountry> delegationPerCountry = new ArrayList<>();

	public DelegationWrapper(Delegation delegation) throws DelegationsException {
		allDelegationsPoints = delegation.prepareDelegationRoad();
		endedDelegation = delegation.getEndPoint() != null;
		prepareDelegationsPerCountry();
		dateOfCalculation = calculateDelegationDate();
	}

	private void prepareDelegationsPerCountry() {
		DelegationPoint prevDp = null;
		for (DelegationPoint dp : allDelegationsPoints) {
			if(prevDp!= null){
				DelegationInSingleCountry dci = new DelegationInSingleCountry(prevDp, dp);
				addToInstances(dci);
			}
			prevDp = dp;
		}
	}

	private void addToInstances(DelegationInSingleCountry dci) {
		for (DelegationInSingleCountry existingDci : delegationPerCountry) {
			if (dci.getCountryCode().equals(existingDci.getCountryCode())) {
				existingDci.addTimeSpent(dci);
				return;
			}
		}
		delegationPerCountry.add(dci);
	}

	private Date calculateDelegationDate() {
		return endedDelegation ? allDelegationsPoints.get(allDelegationsPoints.size() - 1).getIntoTime() : new Date();
	}

	public boolean isEndedDelegation() {
		return endedDelegation;
	}

	public void setEndedDelegation(boolean endedDelegation) {
		this.endedDelegation = endedDelegation;
	}

	public Date getDateOfCalculation() {
		return dateOfCalculation;
	}

	public List<DelegationPoint> getAllDelegationsPoints() {
		return allDelegationsPoints;
	}

	public List<DelegationInSingleCountry> getDelegationPerCountry() {
		return delegationPerCountry;
	}
}
