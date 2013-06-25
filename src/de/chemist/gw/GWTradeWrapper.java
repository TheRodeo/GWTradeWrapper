package de.chemist.gw;

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
		} catch (IOException e) {
			throw new GWTradeException("Item not found: " + id, e);
		}
	}

	private static URL buildAPIURL(String id) throws GWTradeException {
		StringBuilder bldr = new StringBuilder(BASE_URL);
		bldr.append(id);
		try {
			return new URL(bldr.toString());
		} catch (MalformedURLException e) {
			throw new GWTradeException("Bad URL: " + bldr.toString(), e);
		}
	}

	private static HttpURLConnection getConnection(URL url)
			throws GWTradeException {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			return con;
		} catch (IOException e) {
			throw new GWTradeException(e);
		}
	}
}
