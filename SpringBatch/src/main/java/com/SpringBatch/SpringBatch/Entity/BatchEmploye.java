package com.SpringBatch.SpringBatch.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchEmploye{
	@Id
	
	private int EMPLOYEE_ID;
	private String FIRST_NAME;
	private String LAST_NAME;
	private String EMAIL;
	private String PHONE_NUMBER;
	private String HIRE_DATE;
	private String JOB_ID;
	private int SALARY;
	private int MANAGER_ID;
	private int DEPARTMENT_ID;










	

}
