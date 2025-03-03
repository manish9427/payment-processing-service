package com.cpt.payments.dto;

import lombok.Data;

@Data
public class TransactionDTO {

	private int id;
	private String userId;
	private String paymentMethod;
	private String provider;
	private String paymentType;

	private double amount;
	private String currency;

	private String txnStatus;

	private String merchantTransactionReference;
	private String txnReference;

	private String providerCode;
	private String providerMessage;

	private String providerReference;
	private int retryCount;

}
