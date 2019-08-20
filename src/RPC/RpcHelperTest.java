package RPC;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import entity.Item;
import entity.Item.ItemBuilder;

public class RpcHelperTest {

	@Test
	public void testGetJSONArrayCornerCases() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		
		List<Item> listItem = new ArrayList<Item>();
		JSONArray jsonArray = new JSONArray();
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);

		Item one = new ItemBuilder().setItemId("one").setRating(5).setCategories(category).build();
		Item two = new ItemBuilder().setItemId("two").setRating(5).setCategories(category).build();
		listItem.add(one);
		listItem.add(two);
		
		jsonArray.put(one.toJSONObject());
		jsonArray.put(two.toJSONObject());	
		
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
		
		Item empty = new ItemBuilder().build();
		jsonArray.put(empty.toJSONObject());
		listItem.add(new ItemBuilder().build());
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}

}
