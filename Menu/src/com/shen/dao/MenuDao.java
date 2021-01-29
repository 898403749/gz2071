package com.shen.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shen.bean.Menu;
import com.shen.bean.MenuItem;

public class MenuDao {
	Map<String,Menu> MenuMap=new HashMap<String,Menu>();
	String[] ul1= {"预定在途","成功预定"};
	String[] ul2= {"登记入住","预定入住"};
	String[] ul3= {"已结账宾客","未结账宾客","快速结账"};
	String[] ul4= {"实时房态","房态设置","维修记录","房扫查询"};
	String[] ul5= {"会员列表","会员消费"};

	public MenuDao(){
		MenuMap.put("预定管理",new Menu("预定管理",getli(ul1)));
		MenuMap.put("入住管理",new Menu("入住管理",getli(ul2)));
		MenuMap.put("结账管理",new Menu("结账管理",getli(ul3)));
		MenuMap.put("房态信息查询",new Menu("房态信息查询",getli(ul4)));
		MenuMap.put("会员信息管理",new Menu("会员信息管理",getli(ul5)));
	}
	public Map<String, Menu> getMenuMap() {
		return MenuMap;
	}
	
	public List<MenuItem> getli(String[] ul){
		List<MenuItem> li=new ArrayList<MenuItem>();
		for(int i=0;i<ul.length;i++) {
			li.add(new MenuItem(ul[i],"url","true"));
		}
		return li;
	}
	
}
