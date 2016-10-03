package com.deivison_programmer.dao;

import java.util.List;

import com.deivison_programmer.model.Admin;

public interface AdminInterface {
	
	public void insert(Admin admin);
	public void remove(Integer id);
	public void update(Integer id, Admin admin);
	public Admin getAdmin (Integer id);
	public List<Admin> list();
	
}
