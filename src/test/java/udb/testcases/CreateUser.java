package udb.testcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import udb.dataprovider.CsvDataProvider;

public class CreateUser {
	List<HashMap<String, String>> list;

	@Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void verifyCreateUser(HashMap<String, String> hashmap) {
		// Set the Request
		list = this.getTheUserPayload(hashmap);
		
		
		
	}

	

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {
	}
	private List<HashMap<String, String>> getTheUserPayload(HashMap<String, String> hashmap) {
		if (hashmap.get("TestCaseNo").equalsIgnoreCase("TC103")) {
			list = new ArrayList<>();
			String reqObj = hashmap.get("Request");
			System.out.println("The REQUEST IS " + reqObj);
			String[] stArr = reqObj.split(",");
			List<String> listpair = new ArrayList<>();
			for (String s : stArr) {
				listpair.add(s);
				System.out.println(s);
			}

			for (String s1 : listpair) {
				// "name":"Rbs"
				// "gender":"male"
				// "email":"rbs@15ce.com"
				// "status":"active"
				String[] keyValuePairs = s1.replaceAll("\"", "").split(":");
				HashMap<String, String> pair = new HashMap<>();
				pair.put(keyValuePairs[0], keyValuePairs[1]);
				list.add(pair);
			}
			
			
			
			System.out.println("THE GIVEN PAYLOAD IS " + list.toString());
		}
		return list;

	}

}
