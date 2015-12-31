package com.redhat.fusesap;

public class FlightCustomerInfo {
	
	private String CustomerNumber;
	private String CustomerName;
	private int MaxRows;

	public String getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public int getMaxRows() {
		return MaxRows;
	}

	public void setMaxRows(int maxRows) {
		MaxRows = maxRows;
	}

}
