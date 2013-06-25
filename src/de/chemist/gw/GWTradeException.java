package de.chemist.gw;

@SuppressWarnings("serial")
public class GWTradeException extends Exception {
	
	public GWTradeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GWTradeException(Throwable cause) {
		super(cause);
	}

}
