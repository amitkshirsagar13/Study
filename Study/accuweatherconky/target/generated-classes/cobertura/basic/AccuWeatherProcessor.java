package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.w3c.dom.Document;

import processor.ConnectionProcessor;
import processor.DayDetailsNodeProcessor;
import processor.DayNodeProcessor;
import processor.MoonPhaseNodeProcessor;
import processor.ProcessorHelper;
import store.Constants;
import store.Details;
import store.MoonPhases;

public class AccuWeatherProcessor implements Constants {

	private static String logFileLocation = null;

	public static String getLogFileLocation() {
		return logFileLocation;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * http://www.accuweather.com/en/us/colorado-springs-co/80903/current-
		 * weather/327351
		 * http://www.accuweather.com/en/us/colorado-springs-co/80903
		 * /daily-weather-forecast/327351?day=1&unit=c
		 * 
		 */
		String conkyLocation = null;
		if (args[0] == null) {
			System.out
					.println("Please provide the location to .Conky folder...");
			System.exit(1);
		} else {
			conkyLocation = args[0];
		}
		conkyLocation = conkyLocation
				+ "/.Conky/images/accuImage/config/accuWeather.properties";

		System.out
				.println("Using accuWeather.properties from:" + conkyLocation);

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(conkyLocation));
		} catch (IOException e) {
			System.out.println("FileNotFound...");
		}

		String dataFileLocation = properties.getProperty(DATA_FILE_LOCATION);

		logFileLocation = properties.getProperty(LOG_FILE_LOCATION);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE, d MMM yyyy HH:mm:ss a");

		Date toDayDate = new Date();

		WriteToFile.printToFile(
				dateFormat.format(toDayDate) + NL + NL + SPACER,
				logFileLocation, FALSE);
		String unit = properties.getProperty(UNIT);
		String todayUrl = properties.getProperty(TODAY_URL);

		String finalUrl = todayUrl + CONNECTOR + unit;

		Document doc = ConnectionProcessor.getDayDocument(finalUrl);

		DayNodeProcessor dayNodeProcessor = new DayNodeProcessor(doc);
		Details dayDetails = dayNodeProcessor.processNode();

		dayDetails.set_day("Today");

		WriteToFile.printToFile(dayDetails.toString(), dataFileLocation
				+ "toDay.txt", FALSE);

		WriteToFile.printToFile(dayDetails.toString(), logFileLocation, TRUE);
		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);
		for (int day = 2; day < 6; day++) {
			String newfinalUrl = finalUrl + "&day=" + day;
			doc = ConnectionProcessor.getDayDocument(newfinalUrl);

			dayNodeProcessor = new DayNodeProcessor(doc);
			dayDetails = dayNodeProcessor.processNode();
			dayDetails.set_day(ProcessorHelper.getDay(day - 1));

			WriteToFile.printToFile(dayDetails.toString(), dataFileLocation
					+ day + ".txt", FALSE);
			WriteToFile.printToFile(dayDetails.toString(), logFileLocation,
					TRUE);
			WriteToFile.printToFile(SPACER, logFileLocation, TRUE);

		}

		finalUrl = properties.getProperty(DAYDETAILS_URL) + CONNECTOR + unit;
		doc = ConnectionProcessor.getDayDetailsDocument(finalUrl);
		DayDetailsNodeProcessor dayDetailsNodeProcessor = new DayDetailsNodeProcessor(
				doc);
		dayDetails = dayDetailsNodeProcessor.processNode();

		dayDetails.set_day("DayDetails");
		WriteToFile.printToFile(dayDetails.toStringDayDetails(),
				dataFileLocation + "DayDetails.txt", FALSE);

		WriteToFile.printToFile(dayDetails.toStringDayDetails(),
				logFileLocation, TRUE);
		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);
		finalUrl = properties.getProperty(ASTRO);
		doc = ConnectionProcessor.getMoonDayDocument(finalUrl);
		MoonPhaseNodeProcessor moonProcessor = new MoonPhaseNodeProcessor(doc);
		MoonPhases moonPhases = moonProcessor.processNode();

		WriteToFile.printToFile(moonPhases.toString(), dataFileLocation
				+ "MoonPhase.txt", FALSE);

		WriteToFile.printToFile(moonPhases.toString(), logFileLocation, TRUE);
		WriteToFile.printToFile(SPACER, logFileLocation, TRUE);

	}
}
