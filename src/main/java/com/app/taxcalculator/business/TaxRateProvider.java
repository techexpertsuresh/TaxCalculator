package com.app.taxcalculator.business;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.app.taxcalculator.exception.TaxException;
import com.app.taxcalculator.util.ErrorConstants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


public class TaxRateProvider {

	private static Map<String, BigDecimal> taxRateMap;
	
	/*
	 * Method to push key value in the map for tax calculation while starting the application
	 * Called only once during the time of execution not required to execute in future since map is ready.
	 */
	public void mapTaxRate(String filePath) throws CsvException {
		try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> r = reader.readAll();
            taxRateMap = r.stream().collect(Collectors.toMap(e -> e[0], e -> new BigDecimal(e[1])));
            System.out.println("taxRateMap = "+taxRateMap);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/*
	 * Method to calculate the net price based on the country tax against the gross price
	 */
	public BigDecimal taxRateProvider(BigDecimal grossPrice, String countryIso) throws TaxException {
		System.out.println("taxRate for country = "+ taxRateMap.get(countryIso));
		if(!taxRateMap.containsKey(countryIso)) {
			throw new TaxException(ErrorConstants.TAX_VALUE_NOT_MAPPED_FOR_COUNTRY + countryIso);
		}
		BigDecimal netPrice = grossPrice.subtract(grossPrice.multiply(taxRateMap.get(countryIso))).setScale(2, RoundingMode.HALF_UP);
		System.out.println("netPrice = "+ netPrice);
		return netPrice;
	}

}
