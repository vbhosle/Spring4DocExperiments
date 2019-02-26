package com.springdoc.beans.shopping;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

	private String username;
	private Map<Item, Integer> cartItems = Collections.emptyMap();

	public ShoppingCart(String username) {
		this.username = username;
	}

	public ShoppingCart(String username, Map<Item, Integer> cartItems) {
		this.username = username;
		if (cartItems != null) {
			this.cartItems = cartItems;
		}
	}

	public void addItem(Item item, int quantity) {
		if(quantity <= 0) {
			throw new IllegalArgumentException("Only positive quantity can be added to cart");
		}
		
		this.cartItems.compute(item, (itm, oldQty) -> oldQty == null ? quantity: (oldQty+quantity));
	}

	public void removeItem(Item item, int quantity) {
		this.cartItems.computeIfPresent(item, (itm, oldQty) -> oldQty<=quantity ? null: oldQty - quantity );
	}

	public void setItems(List<Item> items) {
		if(items!=null) {
			items.forEach(item -> this.cartItems.put(item, 1));
		}
	};

	public Map<Item, Integer> getItems() {
		return Collections.unmodifiableMap(this.cartItems);
	};

	@Override
	public String toString() {
		return "username = " + this.username + ", items = [" + this.cartItems + " ]";
	};
}
