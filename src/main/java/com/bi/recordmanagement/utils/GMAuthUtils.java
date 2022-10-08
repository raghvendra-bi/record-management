package com.bi.recordmanagement.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GMAuthUtils {
	// FIVE MAX VAL
	private int FIVEDIGITMAX = 99999;

	// FIVE MIN VAL
	private int FIVEDIGITMIN = 10000;

	private static final Logger logger = LoggerFactory.getLogger(GMAuthUtils.class);

	/**
	 * Random Number with related to
	 * 
	 * @param FIVEDIGITMIN and @param FIVEDIGITMAX
	 * @return
	 */
	public int getRandomNumberInRange() {

		if (this.FIVEDIGITMIN >= this.FIVEDIGITMAX) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((this.FIVEDIGITMAX - this.FIVEDIGITMIN) + 1) + this.FIVEDIGITMIN;
	}
}
