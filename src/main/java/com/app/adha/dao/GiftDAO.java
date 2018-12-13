package com.app.adha.dao;

import java.util.List;

import com.app.adha.entity.Gift;
import com.app.adha.entity.UserService;

public interface GiftDAO {
	
	List<Gift> getAllGifts();
	Gift getGiftById(int giftId);
    void addGift(Gift gift);
    void updateGift(Gift gift);
    void deleteGift(int giftId);

}
