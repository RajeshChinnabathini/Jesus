package com.SpringBatch.SpringBatch.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBatch.SpringBatch.Entity.BatchEmploye;

@Repository
public interface BatchEmployeRepository  extends JpaRepository<BatchEmploye,Integer>{

}
