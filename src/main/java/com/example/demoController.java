package com.example;

import java.sql.SQLException;

//import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController


public class demoController {
	
	private String[] latlong=null;
	private String shopAddress,shopName;
	private double sLat,sLong;
	
    @RequestMapping("/greeting")
    public demo greeting(@RequestParam(value="shopName", defaultValue="Null") String shopName,@RequestParam(value="shopAddress", defaultValue="Null") String shopAddress) {
            	
    	this.shopName=shopName;
    	this.shopAddress=shopAddress;
    	AddressConverter address=new AddressConverter();
            	try {
					 latlong= address.getLatLongPositions(shopAddress);
					sLat=Double.parseDouble(latlong[0]);
					sLong=Double.parseDouble(latlong[1]);
					//System.out.println(sLat);
					String status=DBconnect.insertWithStatement(shopName,shopAddress,latlong[0],latlong[1]);
        		} catch (SQLException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
            	return new demo(shopName,shopAddress );
}
}