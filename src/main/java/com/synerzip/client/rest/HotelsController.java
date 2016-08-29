package com.synerzip.client.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.synerzip.supplier.amadeus.model.flights.AmadeusRequest;
import com.synerzip.supplier.amadeus.model.hotel.HotelSearchRQ;
import com.synerzip.supplier.amadeus.model.hotel.HotelSearchRS;

@RestController
public class HotelsController {

	@Autowired
	private Environment env;

	@Autowired
	private AmadeusRequest amadeusRequest;
	
	@Autowired
	@Qualifier("basic")
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(HotelsController.class);
	
	@RequestMapping(value="/rest/hotelSearchByAirportCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelSearchRS> searchHotelsByAirportCode(@RequestBody HotelSearchRQ hotelSearchRQ){
		String subUrl = "/hotels/search-airport";
		StringBuilder urlBuilder = new StringBuilder(env.getProperty("amadeus.url")).append(subUrl)
				.append(amadeusRequest.generateQuery(hotelSearchRQ)).append("apikey=").append(env.getProperty("amadeus.api.key"));
		logger.info(urlBuilder.toString());
		
		return new ResponseEntity<HotelSearchRS>(restTemplate.getForObject(urlBuilder.toString(), HotelSearchRS.class), HttpStatus.OK);
	}
}
