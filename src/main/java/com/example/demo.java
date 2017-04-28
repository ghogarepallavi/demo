package com.example;

public class demo {
	private final String shopName;
    private final String shopAddress;
    
    public demo(String sName,String sAddress) {
        this.shopName= sName;
        this.shopAddress=sAddress;
    }

    public String getName() {
        return shopName;
    }

    public String getAddress() {
        return shopAddress;
    }
}
