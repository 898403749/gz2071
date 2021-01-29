package com.shen.bean;

public class MenuItem {
	String itemName;
	String url;
	String permission;
	
	public MenuItem(String itemName, String url, String permission) {
		super();
		this.itemName = itemName;
		this.url = url;
		this.permission = permission;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	@Override
	public String toString() {
		return "MenuItem [itemName=" + itemName + ", url=" + url + ", permission=" + permission + "]";
	}

}
