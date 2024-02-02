package com.SpringBatch.SpringBatch.Items;

import org.springframework.batch.item.ItemProcessor;

import com.SpringBatch.SpringBatch.Entity.BatchEmploye;

public class BatchEmployeProcesser implements ItemProcessor<BatchEmploye,BatchEmploye>{

	@Override
	public BatchEmploye process(BatchEmploye batchEmploye) throws Exception {
	    
		batchEmploye.setFIRST_NAME(batchEmploye.getFIRST_NAME().toUpperCase());
		batchEmploye.setLAST_NAME(batchEmploye.getLAST_NAME().toUpperCase());
		
		return batchEmploye;
	}

}
