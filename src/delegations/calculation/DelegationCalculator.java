package delegations.calculation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import currencies.model.Currencies;
import currencies.model.Rate;
import delegations.calculation.model.Diet;
import utils.CurrencyUtil;
import utils.DateUtil;

public class DelegationCalculator {
	private static final Logger logger = LogManager.getLogger(DelegationCalculator.class);
	private DelegationWrapper delegationWrapper;
	private Currencies calculationCurriencies;
	private List<Diet> dietCache = CurrencyUtil.createDietCache();

	public DelegationCalculator(DelegationWrapper delegationInstances, Currencies calculationCurriencies) {
		this.delegationWrapper = delegationInstances;
		this.calculationCurriencies = calculationCurriencies;
	}

	public BigDecimal getDelegationSummaryCalculation() {
		BigDecimal summary = BigDecimal.ZERO;
		for (DelegationInSingleCountry item : delegationWrapper.getDelegationPerCountry()) {
			summary = summary.add(calcDelegatnionCountryAmount(item));
		}
		return summary;
	}

	public BigDecimal calcDelegatnionCountryAmount(DelegationInSingleCountry delegationCountry) {
		BigDecimal countryAmountSummary = BigDecimal.ZERO;
		long secondsSpentInCountry = delegationCountry.getTimeSpent();
		if (delegationCountry.getTimeSpent() > 0L) {
			int diffHours = DateUtil.calculateHours(secondsSpentInCountry);
			int allDays = DateUtil.calculateCompletedDays(secondsSpentInCountry);
			BigDecimal dietBasePLN = calculateDietBaseInPLN(delegationCountry);
			if (allDays > 0) {
				countryAmountSummary = countryAmountSummary.add(dietBasePLN.multiply(new BigDecimal(allDays)));
			}
			if (diffHours > 0) {
				countryAmountSummary = countryAmountSummary.add(calculateRemainingAmount(diffHours, dietBasePLN));
			}

			logger.info("DlegationSummary for " + delegationCountry.getCountryCode() + " is " + countryAmountSummary);
		}
		return countryAmountSummary;
	}

	private BigDecimal calculateDietBaseInPLN(DelegationInSingleCountry dc) {
		Diet diet = getDietForCountry(dc);
		BigDecimal selRate = getCurrencyRate(diet);
		return diet != null ? selRate.multiply(diet.getValue()) : BigDecimal.ZERO;
	}

	private BigDecimal getCurrencyRate(Diet diet) {
		Optional<Rate> selectedRate = calculationCurriencies.getRates().stream()
				.filter(r -> diet.getCurrencyCode().equals(r.getCode())).findFirst();
		return selectedRate.isPresent() ? selectedRate.get().getMid() : BigDecimal.ONE;
	}

	private Diet getDietForCountry(DelegationInSingleCountry dc) {
		Optional<Diet> diets = dietCache.stream().filter(d -> dc.getCountryCode().equals(d.getCountryCode()))
				.findFirst();
		return diets.isPresent() ? diets.get() : null;
	}

	private BigDecimal calculateRemainingAmount(int diffHours, BigDecimal stdPrice) {
		if (diffHours >= 8 && diffHours < 12) {
			return stdPrice.divide(new BigDecimal(2));
		}
		if (diffHours >= 12) {
			return stdPrice;
		}
		return BigDecimal.ZERO;
	}
}
