package com.app.adha.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.adha.dao.GiftDAO;
import com.app.adha.entity.Gift;
import com.app.adha.util.UtilMethods;

@Service
public class GiftServiceImpl implements GiftService{
	
	@Autowired
	private GiftDAO giftDAO;
	
	@Autowired
	private UtilMethods utilMethod;
	
	//getting current date and time using Date class
    DateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    Date dateobj = new Date();
    
	@Override
	public Gift getGiftById(int giftId) {
		Gift gift = giftDAO.getGiftById(giftId);
		return gift;
	}	
	
	@Override
	public List<Gift> getAllGifts(){
		return giftDAO.getAllGifts();
	}
	
	@Override
	public void addGift(Gift gift){
		gift.setCreatedDate(df.format(dateobj));
		gift.setStatus(UtilMethods.ACTIVE);
		giftDAO.addGift(gift);
	}
	
	@Override
	public Gift updateGift(Gift gift) {
		Gift gift_details = getGiftById(gift.getGiftId());
		gift_details.setCreatedBy(gift.getCreatedBy());
		gift_details.setCreatedDate(df.format(dateobj));
		gift_details.setAmount(gift.getAmount());
		gift_details.setGiftName(gift.getGiftName());
		gift_details.setStatus(gift.getStatus());
		giftDAO.updateGift(gift_details);
		return gift_details;
	}
	
	@Override
	public void deleteGift(int giftId) {
		giftDAO.deleteGift(giftId);
	}

}
