package am.basic.springdemo.service.impl;

import am.basic.springdemo.model.User;
import am.basic.springdemo.model.excpetion.DuplicateDataException;
import am.basic.springdemo.model.excpetion.ForbiddenException;
import am.basic.springdemo.model.excpetion.NotFoundException;
import am.basic.springdemo.repository.UserRepository;
import am.basic.springdemo.service.UserService;
import am.basic.springdemo.util.CustomMailSender;
import am.basic.springdemo.util.Generator;
import am.basic.springdemo.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


@Service
@Primary
@AllArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;

    private final CustomMailSender customMailSender;

    @Override
    public User signIn(String username, String password) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        NotFoundException.check(!user.getPassword().equals(password), "Wrong username or password");
        ForbiddenException.check(user.getStatus() == Status.UNVERIFIED, "Please verify");
        return user;
    }



    @Override
    public void signUp(User user) throws DuplicateDataException {
        DuplicateDataException.check(
                userRepository.getByUsername(user.getUsername()).isPresent(),
                "User with such username already exists");
        user.setStatus(Status.UNVERIFIED);
        user.setCode(Generator.getRandomDigits(5));
        userRepository.save(user);
        customMailSender.sendMail("Verification", "Your code is " + user.getCode(), user.getUsername());
    }

    @Override
    public void verify(String username, String code) throws NotFoundException, ForbiddenException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        ForbiddenException.check(!code.equals(user.getCode()), "Wrong verification code");
        user.setStatus(Status.ACTIVE);
        user.setCode(null);
        userRepository.save(user);
    }

    @Override
    public void resend(String username) throws NotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        user.setCode(Generator.getRandomDigits(5));
        userRepository.save(user);
        customMailSender.sendMail("Verification", "Your code is " + user.getCode(), user.getUsername());
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) throws ForbiddenException, NotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new NotFoundException("Wrong username or password"));
        ForbiddenException.check(!user.getPassword().equals(oldPassword), "wrong password");
        user.setPassword(newPassword);
        userRepository.save(user);
    }

}
