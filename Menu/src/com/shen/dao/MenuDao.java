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
	String[] ul1= {"Ԥ����;","�ɹ�Ԥ��"};
	String[] ul2= {"�Ǽ���ס","Ԥ����ס"};
	String[] ul3= {"�ѽ��˱���","δ���˱���","���ٽ���"};
	String[] ul4= {"ʵʱ��̬","��̬����","ά�޼�¼","��ɨ��ѯ"};
	String[] ul5= {"��Ա�б�","��Ա����"};

	public MenuDao(){
		MenuMap.put("Ԥ������",new Menu("Ԥ������",getli(ul1)));
		MenuMap.put("��ס����",new Menu("��ס����",getli(ul2)));
		MenuMap.put("���˹���",new Menu("���˹���",getli(ul3)));
		MenuMap.put("��̬��Ϣ��ѯ",new Menu("��̬��Ϣ��ѯ",getli(ul4)));
		MenuMap.put("��Ա��Ϣ����",new Menu("��Ա��Ϣ����",getli(ul5)));
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
