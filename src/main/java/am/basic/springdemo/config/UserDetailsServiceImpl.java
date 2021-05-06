package am.basic.springdemo.config;

import am.basic.springdemo.model.Role;
import am.basic.springdemo.model.User;
import am.basic.springdemo.repository.UserRepository;
import am.basic.springdemo.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Primary
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = userRepository.getByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Wrong username or pasword") );


//   same as the  expression below
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Role role : user.getRoles()){
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//            grantedAuthorities.add(grantedAuthority);
//        }

        if (user.getStatus() == Status.UNVERIFIED){
            throw new UsernameNotFoundException("User is unverified");
        }

        List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());


        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),grantedAuthorities);
    }
}
