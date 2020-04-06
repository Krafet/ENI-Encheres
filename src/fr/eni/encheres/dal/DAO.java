package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;

public interface DAO<T> {
	
	T insert(T element) throws BusinessException;
	boolean update (T element) throws BusinessException;
	boolean delete (int id) throws BusinessException;
	List<T> selectAll() throws BusinessException;
	T selectById(int id) throws BusinessException;

}
