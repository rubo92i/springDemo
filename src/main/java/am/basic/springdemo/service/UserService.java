package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.commons.model.SearchRequest;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.User;
import am.basic.springdemo.model.excpetion.DuplicateDataException;
import am.basic.springdemo.model.excpetion.ForbiddenException;
import am.basic.springdemo.model.excpetion.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {


    User signIn(String username, String password) throws   ResponseException;

    void signUp(User user) throws   ResponseException;

    void verify(String username, String code) throws NotFoundException, ForbiddenException;

    void resend(String username) throws NotFoundException;

    void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException;

    Page<User> search(SearchRequest searchRequest, Pageable pageable);

}
