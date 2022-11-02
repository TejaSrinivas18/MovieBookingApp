package com.moviebooking.service;

import com.moviebooking.bean.User;
import com.moviebooking.exception.MovieBookingException;

public interface IUserRegService {
    void createUser(User user) throws MovieBookingException;

    User getByUserName(String username) throws MovieBookingException;
}