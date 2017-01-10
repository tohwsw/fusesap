# Fuse Integration with SAP

This example shows how JBoss Fuse uses the SAP Java Connector (SAP JCo) library to facilitate bidirectional communication with SAP. Fuse will expose the SAP data via a REST invocation.

[[blob\fusesap.png]]

# Pre-requisites

1) There should be an existing SAP server for connection

2) Install the SAP Tool Suite in the JBoss Developer Studio. This creates a SAP Connections view for testing your components.

https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Tooling_User_Guide/RiderSAP.html

3) Set up the Fuse instance through the following steps. Make sure to install camel-sap and camel-jackson

https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.2/html/Apache_Camel_Component_Reference/SAP.html

# Installation of Fuse Bundle

In the Fuse shell, run the following command
install -s mvn:com.redhat/fusesap/0.0.1-SNAPSHOT

# Invocation

Using the SOAPUI, fire a REST POST to http://localhost:9000/flightservice/list.

Sample input:

{"customerName":"Lee Keller","maxRows":"1"}

Sample output:

{
   "CUSTOMER_LIST": [   {
      "CUSTOMERID": "00000352",
      "CUSTNAME": "Lee Keller",
      "FORM": "Mr.",
      "STREET": "107 17th St.",
      "POBOX": "",
      "POSTCODE": "09765",
      "CITY": "Boulder",
      "COUNTR": "US",
      "COUNTR_ISO": "US",
      "REGION": "",
      "PHONE": "516-865-1547",
      "EMAIL": "Lee_Keller@Boulder.net"
   }],
   "CUSTOMER_RANGE": [],
   "EXTENSION_IN": [],
   "EXTENSION_OUT": [],
   "RETURN": [   {
      "TYPE": "S",
      "ID": "BC_IBF",
      "NUMBER": "000",
      "MESSAGE": "Method was executed successfully",
      "LOG_NO": "",
      "LOG_MSG_NO": "000000",
      "MESSAGE_V1": "",
      "MESSAGE_V2": "",
      "MESSAGE_V3": "",
      "MESSAGE_V4": "",
      "PARAMETER": "",
      "ROW": 0,
      "FIELD": "",
      "SYSTEM": "BTS"
   }]
}


