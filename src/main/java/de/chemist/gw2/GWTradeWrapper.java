package de.chemist.gw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GWTradeWrapper {

	private static final String BASE_URL = "http://www.guildwarstrade.com/api/public/item?id=";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1003.1 Safari/535.19 Awesomium/1.7.1";

	public static String getItemByID(String id) throws GWTradeException {
		try {
			URL url = buildAPIURL(id);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					getConnection(url).getInputStream()));
			return bf.readLine();
		} catch (IOException ioe) {
			throw new GWTradeException("Could not find item: " + id, ioe);
		}
	}

	private static URL buildAPIURL(String id) throws MalformedURLException {
		StringBuilder bldr = new StringBuilder(BASE_URL);
		bldr.append(id);
		return new URL(bldr.toString());
	}

	private static HttpURLConnection getConnection(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", USER_AGENT);
		return con;
	}
}
