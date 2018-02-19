package currencies;

import java.nio.charset.Charset;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import currencies.model.Currencies;

public class CurrenciesServiceCaller {
	
	private CurrenciesServiceCaller(){}

	public static Currencies getLastCurrenciesBetween(String endDate, String startDate) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		String url = "http://api.nbp.pl/api/exchangerates/tables/A/" + startDate + "/" + endDate + "/";
		Currencies[] currencies = restTemplate.getForObject(url, Currencies[].class);
		return currencies[currencies.length - 1];
	}

}
