package com.cpt.payments.service.impl.statushandler;

import org.springframework.stereotype.Service;

import com.cpt.payments.dao.interfaces.TransactionDao;
import com.cpt.payments.dto.TransactionDTO;
import com.cpt.payments.service.interfaces.TransactionStatusHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InitiatedStatusHandler implements TransactionStatusHandler {

	private TransactionDao transactionDao;
	
	public InitiatedStatusHandler(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	@Override
	public boolean processStatus(TransactionDTO transactionDTO) {
        log.info("Processing status for INITIATED||transaction:{}", transactionDTO);
        
		boolean isTxnSaved = transactionDao.updateTransaction(transactionDTO);

		log.info("Transaction saved successfully||isTxnSaved:{}", isTxnSaved);
		
		//TODO insert transaction log
		
		return isTxnSaved;
	}

}
