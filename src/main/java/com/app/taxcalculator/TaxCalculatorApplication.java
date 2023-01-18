package com.app.taxcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.taxcalculator.business.TaxRateProvider;
import com.opencsv.exceptions.CsvException;

@SpringBootApplication
public class TaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxCalculatorApplication.class, args);
		try {
			//Please map your file absolute path based on your operating system
			//This file contains the tax percentage mapping for all countries
			String filePath = "/Users/sureshkumariyyappan/Suresh/BE-Workspace/TaxCalculator/src/main/java/com/app/taxcalculator/business/TaxRate.csv";
			new TaxRateProvider().mapTaxRate(filePath);
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
