package com.deivison_programmer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.deivison_programmer.connection.ConnectionFactory;
import com.deivison_programmer.model.Usuario;

public class UsuarioDAO implements UsuarioInterface {
	
	private ResultSet rs =  null;
	private PreparedStatement stmt = null;
	private Connection con =  null;
	private ConnectionFactory cf = new ConnectionFactory();
	private String sql = "";

	@Override
	public void insert(Usuario usuario) {
		if(usuario != null){
			try{
				sql = "insert into usuario (nome, email, senha, nivel, status) values(?,?,?,?,?)";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getSenha());
				stmt.setInt(4, usuario.getNivel());
				stmt.setBoolean(5, false);
				
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
	public void remove(Integer id) {
		if(id != null && id > 0){
			try{
				sql = "delete from usuario where id = ?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
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
	public void update(Integer id, Usuario usuario) {
		if(id != null && id > 0 && usuario != null){
			try{
				sql = "update usuario set nome=?, email=?, senha=? where id =?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getEmail());
				stmt.setString(3, usuario.getSenha());
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
	public Usuario getUsuario(Integer id) {
		if(id  != null && id > 0){
			try{
				sql = "select * from usuario where id=?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setInt(1, id);
				
				rs = stmt.executeQuery();
				
				if(rs != null){
					while(rs.next()){
						Usuario user = new Usuario();

						user.setId(rs.getInt("id"));
						user.setNome(rs.getString("nome"));
						user.setEmail(rs.getString("email"));
						user.setSenha(rs.getString("senha"));
						user.setStatus(rs.getBoolean("status"));
						user.setNivel(rs.getInt("nivel"));

						return user;
					}
				}
				
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
	public List<Usuario> usuarios(Integer id) {
		try{
			if(id != null){
				if(id ==1)
					sql = "select * from usuario where status = 0";
				if(id ==2)
					sql = "select * from usuario where status = 1";
				if(id ==3)
					sql = "select * from usuario order by nome asc";
				if(id ==4)
					sql = "select * from usuario order by nome desc";
				if(id ==5)
					sql = "select * from usuario order by nivel desc";
				if(id == 6)
					sql = "select * from usuario order by nivel asc";			}
			else
				sql = "select * from usuario";
			con = cf.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if(rs  != null){
				List<Usuario> usuarios = new ArrayList<>();
				
				while(rs.next()){
					Usuario user = new Usuario();
					
					user.setId(rs.getInt("id"));
					user.setNome(rs.getString("nome"));
					user.setEmail(rs.getString("email"));
					user.setSenha(rs.getString("senha"));
					user.setNivel(rs.getInt("nivel"));
					user.setStatus(rs.getBoolean("status"));
					
					usuarios.add(user);
				}
				
				rs.close();
				stmt.close();
				con.close();
				
				return usuarios;
			}
		}
		catch(Exception  e){
			e.printStackTrace();
		}
		
		return null;
	}

	public void ativaUsuario(Integer id){
		if(id != null && id > 0){
			try{
				sql = "update usuario set  status=? where id=?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setBoolean(1, true);
				stmt.setInt(2, id);
				
				stmt.execute();
				
				stmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void desativaUsuario(Integer id){
		if(id != null && id > 0){
			try{
				sql = "update usuario set  status=? where id=?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setBoolean(1, false);
				stmt.setInt(2, id);
				
				stmt.execute();
				
				stmt.close();
				con.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public Usuario login(String email, String senha){
		if(senha != null && email != null && !senha.isEmpty() && !email.isEmpty()){
			try{
				sql = "select * from usuario where email =? and senha =?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, email);
				stmt.setString(2, senha);
				
				rs = stmt.executeQuery();
				
				if(rs != null){
					while(rs.next()){
						Usuario user = new Usuario();
	
						user.setId(rs.getInt("id"));
						user.setNome(rs.getString("nome"));
						user.setEmail(rs.getString("email"));
						user.setSenha(rs.getString("senha"));
						user.setStatus(rs.getBoolean("status"));
						user.setNivel(rs.getInt("nivel"));
	
						return user;
					}
				}
			}
			catch(Exception  e){
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public int cadastradoOuStatus(String email){
		if(email != null && !email.isEmpty()){
			try{
				sql = "select * from usuario where email= ?";
				con = cf.getConnection();
				stmt = con.prepareStatement(sql);
				
				stmt.setString(1, email);
				
				rs = stmt.executeQuery();
				
				if(rs != null){
					while(rs.next()){
						if(rs.getString("email").equals(email) && rs.getBoolean("status"))
							return 1;
						else
							return 2;
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

}
