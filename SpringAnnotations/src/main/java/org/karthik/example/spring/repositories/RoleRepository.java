package org.karthik.example.spring.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.karthik.example.spring.entities.Role;


/**
 * 
 * @author Karthik Gopalapuram
 *
 */
public interface RoleRepository extends JpaRepository<Role, Serializable> {
	
}