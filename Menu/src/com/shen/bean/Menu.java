package com.shen.bean;

import java.util.List;

public class Menu {
	String menuName;
	List<MenuItem> itemSet;
	
	public Menu(String menuName, List<MenuItem> itemSet) {
		super();
		this.menuName = menuName;
		this.itemSet = itemSet;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public List<MenuItem> getItemSet() {
		return itemSet;
	}
	public void setItemSet(List<MenuItem> itemSet) {
		this.itemSet = itemSet;
	}
	@Override
	public String toString() {
		return "Menu [menuName=" + menuName + ", itemSet=" + itemSet + "]";
	}
	
}
