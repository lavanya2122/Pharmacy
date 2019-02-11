package com.pharmacy;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * This class prepares HashMap with drugname as key and drug object as value 
 * 
 * */
public class DrugSales {
	public HashMap<String, ArrayList> drugPrescribers = new HashMap<String, ArrayList>();
	public static HashMap<String, Drug> hm = new HashMap<String, Drug>(); 
	Drug d = new Drug();
	
	public DrugSales(HashMap drugPres) {
		this.drugPrescribers = drugPres;
	}
	
	public void addDrugRecord(Drug d, HashMap<String, Drug> hm) {
		String drugname = d.getName();
		String prescriber = d.getPrescriberFullName();
		Double cost = d.getCost(); //current record cost
		int count = getPrescriberCount(drugname, prescriber);
		
		if(hm.containsKey(drugname)){
			Drug dr = (Drug) hm.get(drugname);
			Double totalCost = dr.getCost();
			
			//If drug already existing in map update total
			cost = totalCost + cost;
		}
		Drug updatedRecord = new Drug(drugname, count, cost);
		hm.put(drugname, updatedRecord);
	}
	
	//Gets unique prescribers count for each drug
	public int getPrescriberCount(String drugName, String prescriber) {
		int count = 0;
		ArrayList<String> prescriberAL = new ArrayList<String>();
		if(drugPrescribers.containsKey(drugName)) {
			prescriberAL = drugPrescribers.get(drugName);
		}
		
		if(!(prescriberAL.contains(prescriber))) {
			prescriberAL.add(prescriber);
		}
		count = prescriberAL.size();
		drugPrescribers.put(drugName, prescriberAL);
		return count;
	}
}

