package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

public class DateUtil {
	private static final Logger logger = LogManager.getLogger(DateUtil.class);
	private DateUtil() {
	}

	public static Date convertToDate(String sDate) {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		try {
			return format.parse(sDate);
		} catch (ParseException e) {
			logger.error("Convert String to date went wrong!",e);
		}
		return null;
	}

	/*
	 * Returns mills between two dates
	 */
	public static Long secondsBetween(Date prevDate, Date copmpareDate) {
		try {
			Long mills = copmpareDate.getTime() - prevDate.getTime();
			Long diffSeconds = TimeUnit.SECONDS.convert(mills, TimeUnit.MILLISECONDS);
			logger.info("It's: "+ diffSeconds+" seconds " );
			return diffSeconds;
		} catch (Exception e) {
			logger.error("Comparison between dates went wrong!");
		}
		return null;
	}

	public static String convertToString(Date date, String stringFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(stringFormat);
		return formatter.format(date);
	}

	public static Date dateBefore(Date calculationDate, int minusDays) {
		DateTime now = new DateTime(calculationDate);
		DateTime beforeDate = now.minusDays(minusDays);
		return beforeDate.toDate();
	}
	
	public static int calculateHours(long seconds) {
		return (int) (seconds / (60 * 60) % 24);
	}

	public static int calculateCompletedDays(long secondsSpentInCountry) {
		return (int) (secondsSpentInCountry / (24 * 60 * 60));
	}
}
