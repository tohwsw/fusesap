package com.redhat.fusesap;

import org.apache.camel.Exchange;
import org.fusesource.camel.component.sap.SapSynchronousRfcDestinationEndpoint;
import org.fusesource.camel.component.sap.model.rfc.Structure;
import org.fusesource.camel.component.sap.model.rfc.Table;


/**
 * A bean which we use in the route
 */
public class FlightBean {
	
	private static final String SAP_FUNCTION="BAPI_FLCUST_GETLIST";
	
	public void createRequest(Exchange exchange) throws Exception {

	    // Get SAP Endpoint to be called from context.
		SapSynchronousRfcDestinationEndpoint endpoint = exchange.getContext().getEndpoint("sap-srfc-destination:sapvm:BAPI_FLCUST_GETLIST", SapSynchronousRfcDestinationEndpoint.class);

	    // Retrieve bean from message containing Flight Customer name to look up
	    FlightCustomerInfo bookFlightRequest = exchange.getIn().getBody(FlightCustomerInfo.class);
	    System.out.println("Customer Info = " + bookFlightRequest.getCustomerName());
	    

	    // Create SAP Request object from target endpoint.
	    Structure request = endpoint.createRequest();
	    
	    // Add Customer Name to request if set
	    if (bookFlightRequest.getCustomerName() != null) {
	            request.put("CUSTOMER_NAME", bookFlightRequest.getCustomerName());
	            request.put("MAX_ROWS", bookFlightRequest.getMaxRows());
	        }
	    else {
	        throw new Exception("No Customer Name");
	    }
	    
	    // Put request object into body of exchange message.
	    exchange.getIn().setBody(request);
	    exchange.getIn().setHeader("sapbapi", FlightBean.SAP_FUNCTION);
	}

    public void checkFlightCustomerInfo(Exchange exchange) throws Exception {
        
        // Retrieve SAP response object from body of exchange message.
        Structure flightCustomerGetListResponse =
            exchange.getIn().getBody(Structure.class);
        
        if (flightCustomerGetListResponse == null) {
            throw new Exception("No Flight Customer Get List Response");
        }
        
        // Check BAPI return parameter for errors 
        @SuppressWarnings("unchecked")
        Table<Structure> bapiReturn = flightCustomerGetListResponse.get("RETURN", Table.class);
        Structure bapiReturnEntry = bapiReturn.get(0);
        System.out.println("BAPI call return type" + bapiReturnEntry.get("TYPE", String.class));
        if (!bapiReturnEntry.get("TYPE", String.class).matches("S")){
           String message = bapiReturnEntry.get("MESSAGE", String.class);
           throw new Exception("BAPI call failed: " + message);
        }

        // Get customer list table from response object.
        @SuppressWarnings("unchecked")
        Table<? extends Structure> customerList = flightCustomerGetListResponse.get("CUSTOMER_LIST", Table.class);
        
        if (customerList == null || customerList.size() == 0) {
            throw new Exception("No Customer Info.");
        }
        
        // Get Flight Customer data from first row of table.
        Structure customer = customerList.get(0);
        System.out.println("Customer list size = " + customerList.size());
        
        /*
        // Create bean to hold Flight Customer data.
        FlightCustomerInfo flightCustomerInfo = new FlightCustomerInfo();
        
        // Get customer id from Flight Customer data and add to bean.
        String customerId = customer.get("CUSTOMERID", String.class);
        String customerName = customer.get("CUSTNAME", String.class);
        System.out.println("customer id " + customerId);
        if (customerId != null) {
            flightCustomerInfo.setCustomerNumber(customerId);
            flightCustomerInfo.setCustomerName(customerName);
        }
        
        
        // Put bean into body of exchange message.
        exchange.getIn().setHeader("flightCustomerInfo", flightCustomerInfo);  
        */ 
        
    }
    
    public void mapOutput(Exchange exchange) throws Exception {
    	
	 	exchange.getOut().setBody(exchange.getIn().getBody());
    	
    }
}
