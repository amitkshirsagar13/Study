package com.mp3editor.util;

/**
 * <p>
 * <b>Overview:</b>
 * <p>
 * 
 * 
 * <pre>
 * Creation date: May 9, 2013
 * @author Amit Kshirsagar
 * @version 1.0
 * @since
 * 
 * <p><b>Modification History:</b><p>
 * 
 * 
 * </pre>
 */
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class SearchResultParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JSONParser jsonParser = new JSONParser();
			File jsonFile = new File(
					"D:\\workspace\\MP3MetadataEditor\\data\\searchResults.json");
			FileInputStream fstream = new FileInputStream(jsonFile);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strLine = null;
			StringBuffer jsonString = new StringBuffer();
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
				jsonString.append(strLine);
			}
			// Close the input stream

			in.close();

			String[] url = getImageUrls(jsonString.toString());
			for (int i = 0; i < url.length; i++) {
				String string = url[i];
				System.out.println(i + "] - " + url[i]);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String[] getImageUrls(String jsonString) {
		Object objectJson = JSONValue.parse(jsonString);

		JSONObject object = (JSONObject) objectJson;

		// System.out.println("======Start JSON======");
		// System.out.println(object);
		// System.out.println(object.get("responseData"));
		// System.out.println(((JSONObject) object.get("responseData"))
		// .get("results"));

		JSONArray unEscaperUrlArray = (JSONArray) ((JSONObject) object
				.get("responseData")).get("results");
		// System.out.println();
		String[] url = new String[unEscaperUrlArray.size()];
		for (int i = 0; i < unEscaperUrlArray.size(); i++) {
			// System.out.println(((JSONObject) unEscaperUrlArray.get(i))
			// .get("url"));
			url[i] = (String) ((JSONObject) unEscaperUrlArray.get(i))
					.get("url");
		}
		// System.out.println("======END JSON======");
		return url;

	}
}
