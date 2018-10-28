package com.app.adha.service;

import java.util.List;

import com.app.adha.entity.Gift;

public interface GiftService {
	
	List<Gift> getAllGifts();
	Gift getGiftById(int giftId);
    void addGift(Gift gift);
    void deleteGift(int giftId);

}
