package com.springdoc.core;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springdoc.beans.shopping.Item;
import com.springdoc.beans.shopping.ShoppingCart;

public class BeanOverview {

	public static void main(String[] args) {
		beanAliasHasPriority();
	}

	public static void beanAliasHasPriority() {
		// Before testing ensure that <alias .. /> tag is uncommented
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "shopping/clothes-cart.xml", "shopping/food-cart.xml" });

		ShoppingCart clothesCart = context.getBean("shoppingCart", ShoppingCart.class);
		System.out.println(clothesCart);

		ShoppingCart foodCart = context.getBean("foodShoppingCart", ShoppingCart.class);
		System.out.println(foodCart);

		((ClassPathXmlApplicationContext) context).close();
	}

	public static void registerBeanOutsideContainer() {
		ApplicationContext context = new ClassPathXmlApplicationContext("shopping/clothes-cart.xml");

		// Existing Bean
		ShoppingCart clothesCart = context.getBean("shoppingCart", ShoppingCart.class);
		System.out.println(clothesCart);

		// Register bean outside the context
		Map<Item, Integer> shoppingItems = new HashMap<>();
		Item ball = new Item("ball", "toys", 10);
		Item toyCar = new Item("toy car", "toys", 8);
		shoppingItems.put(ball, 2);
		shoppingItems.put(toyCar, 1);
		ShoppingCart toyCart = new ShoppingCart("Mira", shoppingItems);

		((ConfigurableApplicationContext) context).getBeanFactory().registerSingleton("toyCart", toyCart);

		// Get the newly registered bean via context
		ShoppingCart toyCartFromBean = context.getBean("toyCart", ShoppingCart.class);
		System.out.println("# Toy cart registered outside the bean container");
		System.out.println(toyCartFromBean);
		((ClassPathXmlApplicationContext) context).close();
	}

}
