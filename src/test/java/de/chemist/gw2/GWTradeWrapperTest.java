package de.chemist.gw2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author Maximilian Werling
 * @version 1.0
 */
public class GWTradeWrapperTest {

	@Test
	public void getItemInformationTest() {
		int itemId = 1234;
		try {
			String result = GWTradeWrapper.getItemInformation(itemId);
			System.out.println(result);
		} catch (GWTradeException e) {
			System.out.println(e.getMessage());
		}

		itemId = 1;
		try {
			String result = GWTradeWrapper.getItemInformation(itemId);
			System.out.println(result);
		} catch (GWTradeException e) {
			assertEquals(e.getMessage(), "Could not find item: " + itemId);
			System.out.println(e.getMessage());
		}
	}
}
