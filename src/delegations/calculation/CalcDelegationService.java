package delegations.calculation;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import currencies.CurrenciesServiceCaller;
import currencies.model.Currencies;
import exceptions.DelegationsException;
import utils.DateUtil;
import utils.DelegationValidatorUtil;

@Path("/calculateDelegation")
@Produces("text/html")
public class CalcDelegationService {
	private static final int DAYS_BEFORE = 5;
	private boolean log4jConfigured = true;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response calculateDelegation(Delegation delegation) {
		try {
			DelegationValidatorUtil.validateJSON(delegation);

			DelegationWrapper delegationWrapper = new DelegationWrapper(delegation);
			Currencies curriencies = getProperNBPCurrencies(delegationWrapper.getDateOfCalculation());

			DelegationCalculator calculator = new DelegationCalculator(delegationWrapper, curriencies);
			BigDecimal amount = calculator.getDelegationSummaryCalculation();
			return Response.status(200).entity(amount + "PLN").build();

		} catch (DelegationsException e) {
			return Response.status(409).entity(e.toString()).build();
		}
	}

	private Currencies getProperNBPCurrencies(Date calculationDate) {
		String endDate = DateUtil.convertToString(calculationDate, "yyyy-MM-dd");
		String startDate = DateUtil.convertToString(DateUtil.dateBefore(calculationDate, DAYS_BEFORE), "yyyy-MM-dd");
		return CurrenciesServiceCaller.getLastCurrenciesBetween(endDate, startDate);
	}
}
