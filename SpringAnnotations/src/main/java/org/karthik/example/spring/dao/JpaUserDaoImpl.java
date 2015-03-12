package org.karthik.example.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.karthik.example.spring.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Karthik Gopalapuram
 *
 */

@Repository
@Transactional
public class JpaUserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;
    
    private final static String LOGIN_Q = "select u from User u where u.email=?1 and u.password=?2";
    private final static String FIND_ALL_Q = "select u from User u";

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
	return em.createQuery(FIND_ALL_Q , User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(int id) {
	return em.find(User.class, id);
    }

    @Override
    public User create(User user) {
	if (user.getId() <= 0) {
	    em.persist(user);
	} else {
	    user = em.merge(user);
	}
	return user;
    }

    @Override
    public User login(String email, String password) {
	TypedQuery<User> query = em.createQuery(LOGIN_Q , User.class);
	query.setParameter(1, email);
	query.setParameter(2, password);
	try {
	    return query.getSingleResult();
	} catch (NonUniqueResultException | NoResultException e) {
	    return null;
	}

    }

}
