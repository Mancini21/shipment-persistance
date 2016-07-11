package com.tnt.integrationframework.activityservice.shipmentpersistance;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.Consignment;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.OrderDetails;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.OrderLineItem;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.PriceSummary;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.quotedetails.v1.Item;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.quotedetails.v1.QuoteDetails;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.GenerateQuoteHashCodeRequest;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.GenerateQuoteHashCodeReply;










import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.Orders;
import com.tnt.entity.Inrqit01;
import com.tnt.entity.Inrqut01;
import com.tnt.integration.service.ServiceException;
import com.tnt.shipmentpersistanceapi.helper.ShipmentPersistanceHelper;




public class Runner {

	private  Transaction transaction;
	SessionFactoryProducer sessionfactoryProducer = new SessionFactoryProducer();
	/*public void  saveData (String args) {
	//	GenerateQuoteHashCodeRequest request = new GenerateQuoteHashCodeRequest();
		Inrqut01 inrequest = new Inrqut01();
		inrequest.setUuid(UUID.randomUUID().toString());
		inrequest.setAccountNumber((long) 12345);
		inrequest.setChannel("channel");
		inrequest.setCollectionDate(20-06-1985);
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionfactoryProducer.getSession();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction = 	session.beginTransaction();
		session.persist(inrequest);
		//request.setName("args");
		//session.save(request);
		transaction.commit();
		session.close();
	}*/
	
	public void saveData(){
		GenerateQuoteHashCodeRequest request = new GenerateQuoteHashCodeRequest();
		QuoteDetails qouteDetails = new 	 QuoteDetails();
		qouteDetails.setChannel("NTB");
		qouteDetails.setTotalGoodsValue(BigDecimal.valueOf(20));
		//qouteDetails.getTntAccountNumber().setAccountNumber("12345");
		qouteDetails.setTotalNumberOfItems(BigInteger.valueOf(10));
		qouteDetails.setTotalVolume(BigDecimal.valueOf(10));
		qouteDetails.setTotalWeight(BigDecimal.valueOf(10));
	
	//	qouteDetails.setCollectionAddress(value);
	//	qouteDetails.setCollectionDate(value);
		//qouteDetails.setDeliveryAddress(value);
		qouteDetails.setLengthUOM("5");
		qouteDetails.setPackageType("pg");
		qouteDetails.setRequesterCountry("NL");
		request.setQuote(qouteDetails);
		Item item = new Item();
		BigDecimal bigdecimal = BigDecimal.valueOf(10);
		item.setHeight(bigdecimal);
		item.setLength(bigdecimal);
		item.setQuantity(BigInteger.valueOf(10));
		item.setVolume(bigdecimal);
		item.setWeight(bigdecimal);
		item.setWidth(bigdecimal);
		item.setProductType("BOX");
		item.setWidth(bigdecimal);
		request.getQuote().getItems().add(item);
		
		
		Orders orders = new Orders();
		OrderDetails orderdetails = new OrderDetails();
		Consignment cConsignment = new Consignment();
		cConsignment.setFromlocationId(Long.valueOf(10));
		cConsignment.setTolocationId(Long.valueOf(20));
		orderdetails.setConsignment(cConsignment);
		OrderLineItem oOrderLineItem = new OrderLineItem();
		oOrderLineItem.setProductId("123");
		oOrderLineItem.setEnhancedLiabilityValue(BigDecimal.valueOf(10));
		orderdetails.setOrderLineItem(oOrderLineItem);
	
		PriceSummary price = new PriceSummary();
		price.setCurrencyCode("INR");
		price.setPrice(BigDecimal.valueOf(100));
		price.setVat(BigDecimal.valueOf(100));
		orderdetails.setPriceSummary(price);
		orders.getOrders().add(orderdetails);
		request.setOrders(orders);
	



Session session = null;
try {
	session = sessionfactoryProducer.getSession();
} catch (ServiceException e) {
	// TODO Auto-generated catch block777777777777777777
	e.printStackTrace();
}

transaction = 	session.beginTransaction();
session.save(ShipmentPersistanceHelper.populateRequest(request));

transaction.commit();
session.close();
		
	}
	public void  GetData () {
		
		// TODO Auto-generated method stub
		Session session = null;
		try {
			session = sessionfactoryProducer.getSession();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block777777777777777777
			e.printStackTrace();
		}
		transaction = 	session.beginTransaction();
		List Inrqut01 = session.createQuery("FROM Inrqut01").list();
		
		
				 for (Iterator iterator = 
						 Inrqut01.iterator(); iterator.hasNext();){
					 Inrqut01 employee = (Inrqut01) iterator.next(); 
					 System.out.println ("+++++++++++++++++++++" +employee.getUuid());
					 @SuppressWarnings("rawtypes")
					Set   subtable =    employee.getInrqit01s();
					
					 System.out.println ("+++++++++++++++++++++" +subtable.size());
					 for (Iterator iterator2 = 
							 subtable.iterator(); iterator2.hasNext();){
						 
						 
						 Inrqit01 certName = (Inrqit01) iterator2.next(); 
		                  System.out.println("Certificate: " + certName.getInrqut01().getUuid()); 
					 }
					

}
		transaction.commit();
		session.close();
	}

	
	
}
