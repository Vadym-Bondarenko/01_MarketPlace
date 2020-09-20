package com.marketplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marketplace.model.UserEnt;
import com.marketplace.repository.UserRepository;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEnt user = userRepository.findFirstByEmail(username);
		
		UserBuilder builder = null;
		
		if(user !=null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(user.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
		else {
			throw new UsernameNotFoundException("User Not Found");
		}
		
		return builder.build();
	}

}
