package com.deivison_programmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.deivison_programmer.connection.ConnectionFactory;
import com.deivison_programmer.model.Admin;

public class AdminDAO implements AdminInterface {
	
	private ResultSet rs =  null;
	private PreparedStatement stmt = null;
	private Connection con =  null;
	private ConnectionFactory cf = new ConnectionFactory();
	private String sql = "";
	
	public Admin login(String email, String senha){
		if(email != null && senha != null && !email.isEmpty() && !senha.isEmpty()){
			try{
				sql = "select * from admin where email = ? and senha = ?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, email);
				stmt.setString(2, senha);
				
				rs = stmt.executeQuery();
				
				if(rs != null){
					while(rs.next()){
						Admin a = new Admin();
						
						a.setEmail(rs.getString("email"));
						a.setId(rs.getInt("id"));
						a.setNome(rs.getString("nome"));
						a.setSenha(rs.getString("senha"));
						
						return a;
					}
				}
				else
					return null;
				
				rs.close();
				stmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return null;
	}
	

	@Override
	public void insert(Admin admin) {
		if(admin != null){
			try{
				sql = "insert into admin (nome, email, senha) values (?,?,?)";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, admin.getNome());
				stmt.setString(2, admin.getEmail());
				stmt.setString(3, admin.getSenha());
				
				stmt.execute();
				
				stmt.close();
				con.close();
			}
			catch(Exception  e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remove(Integer id) {
		if(id != null && id > 0){
			try{
				sql = "delete from admin where id =?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, id);
				stmt.execute();
				
				stmt.close();
				con.close();
			}
			catch(Exception  e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Integer id, Admin admin) {
		if(id != null && id > 0 && admin != null){
			try{
				sql = "update admin set nome=?, email=?, senha=? where id=?";
				
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, admin.getNome());
				stmt.setString(2, admin.getEmail());
				stmt.setString(3, admin.getSenha());
				stmt.setInt(4, id);
				
				stmt.execute();
				
				stmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public Admin getAdmin(Integer id) {
		if(id != null && id > 0){
			try{
				sql = "select * from admin where id =?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs != null){
					Admin a =  new Admin();
					
					a.setId(rs.getInt("id"));
					a.setEmail(rs.getString("email"));
					a.setNome(rs.getString("nome"));
					a.setSenha(rs.getString("senha"));
					
					return a;
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public List<Admin> list() {
		try{
			sql = "select * from admin";
			con = cf.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs != null){
				List<Admin> admins = new ArrayList<>();
				
				while(rs.next()){
					Admin a = new Admin();
					
					a.setId(rs.getInt("id"));
					a.setEmail(rs.getString("email"));
					a.setNome(rs.getString("nome"));
					a.setSenha(rs.getString("senha"));
					
					admins.add(a);
				}
				
				return admins;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
