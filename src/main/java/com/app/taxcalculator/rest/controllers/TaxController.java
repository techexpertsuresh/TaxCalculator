package com.app.taxcalculator.rest.controllers;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.taxcalculator.business.TaxRateProvider;
import com.app.taxcalculator.exception.TaxException;

@RestController
public class TaxController {

	@PostMapping("/tax")
	@ResponseBody
	//@ExceptionHandler({ TaxException.class})
	public BigDecimal calculateNetPrice(@RequestParam(name = "code") String countryIso, @RequestParam(name = "price") String grossPrice) {
		try {
			return new TaxRateProvider().taxRateProvider(new BigDecimal(grossPrice), countryIso);
		} catch (TaxException e) {
			throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getLocalizedMessage(), e);
		}
	}

}
