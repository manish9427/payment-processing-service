package com.cpt.payments.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cpt.payments.constant.TransactionStatusEnum;
import com.cpt.payments.dto.TransactionDTO;
import com.cpt.payments.dto.TransactionResDTO;
import com.cpt.payments.service.factory.TransactionStatusFactory;
import com.cpt.payments.service.interfaces.PaymentStatusService;
import com.cpt.payments.service.interfaces.TransactionStatusHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentStatusServiceImpl implements PaymentStatusService {

	private TransactionStatusFactory statusFactory;

	public PaymentStatusServiceImpl(TransactionStatusFactory statusFactory) {
		this.statusFactory = statusFactory;
	}

	@Override
	public TransactionResDTO createPayment(TransactionDTO transactionDTO) {
		log.info("At service received transaction object for creating payment||"
				+ "transaction:{}", transactionDTO);
		
		//Unique transaction reference generated for each transaction
		transactionDTO.setTxnReference(UUID.randomUUID().toString());
		
		TransactionStatusHandler statusHandler = statusFactory.getStatusHandler(
				TransactionStatusEnum.CREATED);
		boolean isTxnSaved = statusHandler.processStatus(transactionDTO);

		if(!isTxnSaved) {
			log.error("Transaction not saved in DB||transaction:{}", transactionDTO);
			//TODO throw exception 
		}
		
		TransactionResDTO txnResDTO = new TransactionResDTO();
		txnResDTO.setTxnReference(transactionDTO.getTxnReference());
		txnResDTO.setTxnStatus(transactionDTO.getTxnStatus());
		
		log.info("Transaction created successfully||txnResDTO:{}", txnResDTO);
		return txnResDTO;
	}

}
