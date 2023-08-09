package udb.dataprovider;

import java.util.HashMap;

import org.testng.annotations.Test;

public class DataProviderExecutor {

	@Test(dataProvider = "csvDataProvider", dataProviderClass = CsvDataProvider.class)
	public void testHashMapDataProvider(HashMap<String, String> hashmap) throws InterruptedException {
		System.out.println(hashmap);
		System.out.println(hashmap.get("SNumber"));
		System.out.println(hashmap.get("username"));
		System.out.println(hashmap.get("email"));
		
	}

}
