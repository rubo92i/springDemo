package am.basic.springdemo.service;

import am.basic.springdemo.model.User;
import am.basic.springdemo.model.excpetion.DuplicateDataException;
import am.basic.springdemo.model.excpetion.ForbiddenException;
import am.basic.springdemo.model.excpetion.NotFoundException;

public interface UserService {


    User signIn(String username, String password) throws NotFoundException, ForbiddenException;

    void signUp(User user) throws  DuplicateDataException;

    void verify(String username, String code) throws NotFoundException, ForbiddenException;

    void resend(String username) throws NotFoundException;

    void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException;

}
