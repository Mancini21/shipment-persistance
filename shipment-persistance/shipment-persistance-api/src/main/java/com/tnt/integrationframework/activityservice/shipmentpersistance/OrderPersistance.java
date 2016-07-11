package com.tnt.integrationframework.activityservice.shipmentpersistance;




public class OrderPersistance {
	
public GenerateQuoteHashCodeReply handleRequest(GenerateQuoteHashCodeRequest request) throws Exception {
	GenerateQuoteHashCodeReply reply = new GenerateQuoteHashCodeReply();
	/*	
		QuoteDetails quoteDetails = request.getQuote();
		Orders orders = request.getOrders();
		
		List<OrderDetails> orderDetails = orders.getOrders();
		
		GenerateQuoteHashCodeReply reply = new GenerateQuoteHashCodeReply();
		
		for (OrderDetails orderDetail : orderDetails) {
			
			OrderLineItem orderLine = orderDetail.getOrderLineItem();
			Consignment consignment = orderDetail.getConsignment();
			PriceSummary priceSummary =orderDetail.getPriceSummary();
			
			QuoteWrapper quoteWrapper = new QuoteWrapper();
			quoteWrapper.setQuoteDetails(quoteDetails);
			quoteWrapper.setOrderLineWrapper(new OrderLineWrapper(orderLine));
			quoteWrapper.setConsignment(consignment);
			
			ProductPriceWrapper productPriceWrapper = new ProductPriceWrapper();
			productPriceWrapper.setOrderLine(orderLine);
			productPriceWrapper.setPriceSummary(priceSummary);
			
			String quoteXML = JMSUtil.marshall(quoteWrapper, jaxbContext);
			String priceXML = JMSUtil.marshall(productPriceWrapper, jaxbContext);
			
			String orderHashCode = hashCodeService.generateHashCode(quoteXML);
			String priceHashCode = hashCodeService.generateHashCode(priceXML);
			
			QuoteHash quoteHash = new QuoteHash();
			quoteHash.setOrderHashCode(orderHashCode);
			quoteHash.setPriceHashCode(priceHashCode);
			
			reply.getQuoteHashes().add(quoteHash);
	
	
		}*/
		
		return reply;
	}

}
