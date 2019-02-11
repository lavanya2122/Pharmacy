package com.pharmacy;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import com.pharmacy.Drug;
import com.sun.crypto.provider.HmacMD5;

public class PharmacyProgram 
{
	static String exFile = ".\\input\\itcont.txt";
	//Drug d = new Drug();
	public static HashMap<String, Drug> hm = new HashMap<String, Drug>(); 
	public static HashMap<String, ArrayList> drugPrescribers = new HashMap<String, ArrayList>();
	static void readFile(String fname)
	{
		if(fname == "") {
			fname = exFile;
		}
		try
		{
		
				BufferedReader br = new BufferedReader(new FileReader(fname), 16384);
	
				String st; 
				int recCount = 0;
				while ((st = br.readLine()) != null) { 
					String[] arr  = st.split(",");
					System.out.println(st);
					//Only process records that have 5 elements in input record 
					if(arr.length == 5 && recCount > 0) {
						//Get Input values 
						String id = arr[0];
						String drugName = arr[3]; 
						String firstName = arr[1];
						String lastName = arr[2];
						Double cost = Double.parseDouble(arr[4]);
						
						String thisRecPrescriber = firstName + lastName;
						
						/*
						if(!(hm.containsKey(drugName))){
							Drug d = new Drug(1, drugName, cost, firstName, lastName);
							hm.put(drugName, d);
						}else {
							Drug d = hm.get(drugName);
							Double totalCost = d.getTotalcost();
							String prescriber = d.getPrescriberFullName();
							
							//If same prescriber prescribed this drug earlier increment counter by 1 else consider it as new sale for new prescriber
							//int counter = d.getCount();
							int counter = d.getPrescriberCount();
							System.out.println("prescriber count "+counter);
							if(counter > 1) {
								totalCost = totalCost + cost;
							}
							Drug dr = new Drug(counter, drugName, totalCost, firstName, lastName);
							hm.put(drugName, dr);
						}
						*/
						
						Drug dRecord = new Drug(id, drugName,  firstName, lastName, cost);
						DrugSales ds = new DrugSales(drugPrescribers);
						if(dRecord != null) {
							ds.addDrugRecord(dRecord, hm);
							//System.out.println(ds.getPrescriberCount(drugName, thisRecPrescriber));
						}
						
					}else {
						System.out.println("Invalid Record at line "+recCount);
					}
					recCount++;
				}
				//System.out.println(hm);
				System.out.println("\n");
		
	    }catch(IOException ie) 
			{
	    		ie.printStackTrace();
			}

	}
	 
	static HashMap<String, Drug> sortMap(HashMap<String, Drug> totalsDict) {
		HashMap<String, Drug> sortedMap = new HashMap<String, Drug>();
		 
        // now let's sort the map in decreasing order of value
		sortedMap = totalsDict.entrySet()
                .stream()
                .sorted(Entry.comparingByValue(
                        Comparator.comparing(Drug::getPrescriberFullName)
                                  .thenComparingDouble(Drug::getCost)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
		return sortedMap;
	}
	
	/*static List sortMap(HashMap<String, Drug> totalsDict) {
		List<Map.Entry<String, Drug>> sortedEntries = new ArrayList<>(totalsDict.entrySet());
		Collections.sort(sortedEntries,
		    Map.Entry.comparingByValue(
	        Comparator
	            .comparingDouble(Drug::getCost)
	            .thenComparing(Drug::getPrescriberFullName)));
		
		return sortedEntries;
	}*/
	
	static void writeToFile(HashMap<String, Drug> sortedMap) {
		String FILE_HEADER = "drug_name,num_prescriber,total_cost";
		String NEW_LINE = "\n";
		String COMMA_SEP = ",";
		try {
			System.out.println("Creating File");
			String filePath = ".\\output\\top_cost_drug.txt";
			File file = new File(filePath);
			FileWriter fr = new FileWriter(filePath); 
			fr.append(FILE_HEADER.toString());
			fr.append(NEW_LINE);
			Set set = sortedMap.keySet();
			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()) {
				String thisKey = iterator.next();
				Drug d = sortedMap.get(thisKey);
			
				fr.append(d.getName());
				fr.append(COMMA_SEP);
				fr.append(String.valueOf(d.getCount()));
				fr.append(COMMA_SEP);
				fr.append(d.getCost().toString());
				fr.append(NEW_LINE);
			}
			fr.flush();
			fr.close();
		}catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
	
	public static void main(String args[])
	{
		String str= args[0];
		readFile(str);
		HashMap<String, Drug> sortedMap = sortMap(hm);
		//List sortedMap = sortMap(hm);
		System.out.println("sorted here "+sortedMap);
		Set set = sortedMap.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			String thisKey = iterator.next();
			Drug d = sortedMap.get(thisKey);
			//System.out.println(d.getName() +"=="+d.getCost()+"=="+d.getCount());
		}
		
		writeToFile(sortedMap);
	}
}
	

