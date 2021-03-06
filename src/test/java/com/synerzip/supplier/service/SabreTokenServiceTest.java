package com.synerzip.supplier.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.synerzip.TravelTimeApplication;

/**
 * @author synerzip
 *
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TravelTimeApplication.class })
@TestPropertySource(locations = { "classpath:supplier.properties", "classpath:application.properties"})
public class SabreTokenServiceTest {
	/**
	 * @author synerzip
	 *
	 */
	@Autowired
	private transient SabreTokenService sabreTokenService;
	
	private Logger logger = LoggerFactory.getLogger(SabreTokenServiceTest.class);
	
	/**
	 * @author synerzip
	 *
	 */
	@Test
	public void testGetTokenString() {
		final String token = sabreTokenService.getTokenString();
		Assert.assertNotNull(token, "Token is not null");
	}
}
