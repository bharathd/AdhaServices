package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Gift;

public interface GiftDAO {
	
	List<Gift> getAllGifts();
	Gift getGiftById(int giftId);
    void addGift(Gift gift);
    void deleteGift(int giftId);

}
