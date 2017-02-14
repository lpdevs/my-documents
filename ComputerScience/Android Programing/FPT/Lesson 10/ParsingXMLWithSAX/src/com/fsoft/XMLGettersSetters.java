package com.fsoft;

import java.util.ArrayList;

import android.util.Log;

public class XMLGettersSetters {
	private ArrayList<String> company = new ArrayList<String>();
	
    public ArrayList<String> getCompany() {
        return company;
    }
    public void setCompany(String company) {
    	this.company.add(company);
        Log.i("This is the company:", company);
    }
    public void setTitle(String title) {
      
        Log.i("This is the title:", title);
    }
    public void setArtist(String artist) {
    
        Log.i("This is the artist:", artist);
    }
    public void setCountry(String Country) {
   
        Log.i("This is the Country:", Country);
    }
    public void setPrice(String Price) {
    	   
        Log.i("This is the Price:", Price);
    }
    
    public void setYear(String Year) {
 	   
        Log.i("This is the Year:", Year);
    }
    
}
