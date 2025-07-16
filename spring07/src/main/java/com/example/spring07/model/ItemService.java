package com.example.spring07.model;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

/**
 * final field로 ItemDao 선언
 * Constuctor Injection DI
 */
@Service
public class ItemService {

	private final ItemDao itemDao;
	public ItemService(ItemDao itemDao) {
		super();
		this.itemDao = itemDao;
	}
	
	public String findItemById(String id) throws SQLException {
		String itemInfo = itemDao.findItemById(id);
		return itemInfo;
	}
	
}
