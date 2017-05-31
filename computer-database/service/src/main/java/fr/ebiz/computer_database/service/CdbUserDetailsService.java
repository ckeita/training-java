package fr.ebiz.computer_database.service;

import fr.ebiz.computer_database.model.User;
import fr.ebiz.computer_database.model.UserPrincipal;
import fr.ebiz.computer_database.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by ebiz on 31/05/17.
 */
@Service
public class CdbUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserPrincipal(user);
	}
}
