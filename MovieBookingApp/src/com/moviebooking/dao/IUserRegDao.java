package com.moviebooking.dao;

import com.moviebooking.bean.User;
import com.moviebooking.exception.MovieBookingException;

public interface IUserRegDao {
    void createUser(User user) throws MovieBookingException;

    User getByUserName(String username) throws MovieBookingException;
}
