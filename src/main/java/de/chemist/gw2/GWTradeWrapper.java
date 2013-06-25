/*
 * This file is part of GWTradeWrapper.
 *
 * GWTradeWrapper is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GWTradeWrapper is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with GWTradeWrapper.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.chemist.gw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Maximilian Werling
 * @version 1.0
 */
public class GWTradeWrapper {

	private static final String BASE_URL = "http://www.guildwarstrade.com/api/public/item?id=";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1003.1 Safari/535.19 Awesomium/1.7.1";

	/**
	 * Returns item information for a given ID.
	 * 
	 * @param itemId
	 *            id of the item you want the information for.
	 * @return String in JSON format.
	 * @throws GWTradeException
	 *             if no item was found with the given id.
	 */
	public static String getItemInformation(int itemId) throws GWTradeException {
		try {
			URL url = new URL(BASE_URL + itemId);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent", USER_AGENT);
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String result = bf.readLine();
			bf.close();
			if (result.equals("{}")) {
				throw new GWTradeException("Could not find item: " + itemId);
			}
			return result;
		} catch (IOException ioe) {
			throw new GWTradeException("No connection!", ioe);
		}
	}
}
