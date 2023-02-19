package com.testing.specialization;

import java.util.concurrent.TimeUnit;

public class TestUtil {
	public static void delay(Integer seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
