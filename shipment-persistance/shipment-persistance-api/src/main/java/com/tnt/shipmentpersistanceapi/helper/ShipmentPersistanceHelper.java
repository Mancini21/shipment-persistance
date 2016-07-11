package com.tnt.shipmentpersistanceapi.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tnt.entity.Inrqit01;
import com.tnt.entity.Inrqit01Id;
import com.tnt.entity.Inrqpt01;
import com.tnt.entity.Inrqpt01Id;
import com.tnt.entity.Inrqut01;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.GenerateQuoteHashCodeReply;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.quotedetails.v1.Item;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.quotedetails.v1.QuoteDetails;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.GenerateQuoteHashCodeRequest;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.Orders;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.v1.QuoteHash;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.Consignment;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.OrderDetails;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.OrderLineItem;
import com.tnt.integrationframework.schema.activityservice.generateandvalidatehashcode.orderdetails.v1.PriceSummary;


public  class ShipmentPersistanceHelper {
	
	
	
	public static Inrqut01 populateRequest( GenerateQuoteHashCodeRequest request) {
		Inrqut01 qoute = new Inrqut01();
	//	GenerateQuoteHashCodeReply reply = new GenerateQuoteHashCodeReply();
		QuoteHash quoteUuid = new QuoteHash();
		String uuid = UUID.randomUUID().toString();
		QuoteDetails qouteDetails = request.getQuote();
		qoute.setUuid(uuid);
			qoute.setGdval(qouteDetails.getTotalGoodsValue());
			qoute.setAccNum(Long.valueOf(10));
			//qoute.setChannel(qouteDetails.getChannel()); 
		//
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			Date updatedDate = null;
			try {
				updatedDate = sdf.parse("21/12/2012");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//	qoute.setColTd(qouteDetails.getCollectionDate().toGregorianCalendar().getTime());
			qoute.setColTd(updatedDate);
			//qoute.setCoucd(	qouteDetails.getRequesterCountry());
	
	/*	qoute.setCurrcd(qouteDetails.getRequesterCurrency());
		*/
		//latter
		//qoute.setIsDeleted(isDeleted);
/*		qoute.setLancd(qouteDetails.getRequesterLanguage());
		qoute.setPaytrms(qouteDetails.getTermsOfPayment());
		qoute.setPkgty(qouteDetails.getPackageType());
		qoute.setSrcoucd(qouteDetails.getCollectionAddress().getCountryISOCode());
		qoute.setSrct(qouteDetails.getCollectionAddress().getCityName());
		qoute.setSrpc(qouteDetails.getCollectionAddress().getPostCode());
		qoute.setSrsd(qouteDetails.getCollectionAddress().getCountrySubDivisionName());*/
		//latter
		//qoute.getSysCd()
			qoute.setTlitms(qouteDetails.getTotalNumberOfItems());
			qoute.setTlvol(qouteDetails.getTotalVolume());
			qoute.setTlwgt(qouteDetails.getTotalWeight());
/*		qoute.setTocoucd(qouteDetails.getDeliveryAddress().getCountryISOCode());
		qoute.setToct(qouteDetails.getDeliveryAddress().getCityName());
		qoute.setTopc(qouteDetails.getDeliveryAddress().getPostCode());
		qoute.setTosd(qouteDetails.getDeliveryAddress().getCountrySubDivisionName());*/
			//qoute.setUomln(qouteDetails.getLengthUOM());
		/*qoute.setUomvl(qouteDetails.getVolumeUOM());
		qoute.setUomwt(qouteDetails.getWeightUOM());*/
		
		
		populateItem(qoute ,request,uuid);
	
		quoteUuid.setOrderHashCode(uuid);

	//	reply.getQuoteHashes().add(quoteUuid);
	
		try {
			updatedDate = sdf.parse("21/12/2012");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		qoute.setUpdtTd(updatedDate);
		qoute.setCrtTd(updatedDate);
		return qoute;
		
	}
	
	private static void populateItem(Inrqut01 qoute, GenerateQuoteHashCodeRequest request, String uuid) {	
		for (Item item : request.getQuote().getItems()){
			Inrqit01Id id = new Inrqit01Id();
		id.setItmid(Short.MIN_VALUE);
		id.setUuid(uuid);
			Inrqit01 inrqit = new Inrqit01();
			inrqit.setIthgt(item.getHeight());
			inrqit.setItlen(item.getLength()); 
			inrqit.setItqty(item.getQuantity());
			inrqit.setItvol(item.getVolume());
			inrqit.setItwgt(item.getWeight());
			inrqit.setItwid(item.getWidth());
			inrqit.setPkgty(item.getPackageType());
				inrqit.setPrdty(item.getProductType());
				inrqit.setId(id);
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				Date updatedDate = null;
				try {
					updatedDate = sdf.parse("21/12/2012");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inrqit.setUpdtTd(updatedDate);
				inrqit.setCrtTd(updatedDate);
			//inrqit.setItwid(item.getWidth());
			qoute.getInrqit01s().add(inrqit);
		}
	
		
		Orders orders = request.getOrders();
		List<OrderDetails> orderDetails = orders.getOrders();
		for (OrderDetails orderDetail : orderDetails) {
			
			System.out.println("inside the order ++++++++++++++++");
			OrderLineItem orderLine = orderDetail.getOrderLineItem();
			Consignment consignment = orderDetail.getConsignment();
			PriceSummary priceSummary =orderDetail.getPriceSummary();	
			Inrqpt01 inrqpt01 = new Inrqpt01();
			Inrqpt01Id inrqpt01id = new Inrqpt01Id();
			
				inrqpt01id.setPrdid(orderLine.getProductId());
				inrqpt01id.setUuid(uuid);
				inrqpt01.setId(inrqpt01id);
				inrqpt01.setEnhancedLiabilityValue(	orderLine.getEnhancedLiabilityValue());
			//inrqpt01.setCrtTd(crtTd);
				//inrqpt01.setCurrcd(orderDetail.getPriceSummary().getCurrencyCode());
			/*inrqpt01.setCusdr(orderDetail.getConsignment().isCustomsDeclarationRequired().toString());
		*/
				//inrqpt01.setFrmlocid(orderDetail.getConsignment().getFromlocationId());
			//inrqpt01.setId(inrqpt01id);
			//latter
			//inrqpt01.setIsDeleted(isDeleted);
		/*	inrqpt01.setLob(orderDetail.getOrderLineItem().getLineOfBusinessId());
			inrqpt01.setPdgrp(	orderDetail.getOrderLineItem().getProductGroupId());
			inrqpt01.setPdsgp(orderDetail.getOrderLineItem().getProductSubGroupId());
			inrqpt01.setPrdty(orderDetail.getOrderLineItem().getProductType());*/
				inrqpt01.setPrice(priceSummary.getPrice());
				inrqpt01.setVat(priceSummary.getVat());
			//latter
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				Date updatedDate = null;
				try {
					updatedDate = sdf.parse("21/12/2012");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	inrqpt01.setUpdtTd(updatedDate);
	inrqpt01.setCrtTd(updatedDate);
		
		//	inrqpt01.setVat(orderDetail.getPriceSummary().getVat());
			qoute.getInrqpt01s().add(inrqpt01);
		}
		
	
		
	}

	
}
