package com.deivison_programmer.dao;

import java.util.List;

import com.deivison_programmer.model.Usuario;

public interface UsuarioInterface {
	
	public void insert(Usuario usuario);
	public void remove(Integer id);
	public void update(Integer id, Usuario usuario);
	public Usuario getUsuario(Integer id);
	public List<Usuario> usuarios(Integer id);
}
