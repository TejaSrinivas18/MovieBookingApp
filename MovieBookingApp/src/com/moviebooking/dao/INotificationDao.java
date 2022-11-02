package com.moviebooking.dao;

import com.moviebooking.bean.Notification;
import com.moviebooking.exception.MovieBookingException;

public interface INotificationDao {

	void createNotification(Notification tobeSendNotification) throws MovieBookingException;

}
