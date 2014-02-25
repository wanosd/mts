package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import users.Role;


@Repository
@Transactional
@Component("roleDAO")
public class RoleDAO {
	
private static Logger logger = Logger.getLogger(RoleDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session session(){
		logger.info("Session Factory returning current session.....");
		return sessionFactory.getCurrentSession();
	}
	
	public void createRole(Role role){
		session().save(role);
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoles(){
		return session().createQuery("from Role").list();
	}
	
	public boolean exists(String name) {
		Criteria crit = session().createCriteria(Role.class);
		crit.add(Restrictions.eq("name", name)); 
		Role role = (Role) crit.uniqueResult();
		return role != null;
	}

	public void delete(Role role) {
		session().delete(role);
		
	}

	public void update(Role role) {
		session().saveOrUpdate(role);
		
	}
	
	public int noBookings(String name){
		Criteria crit = session().createCriteria(Role.class);
		crit.add(Restrictions.eq("name", name)); 
		Role role = (Role) crit.uniqueResult();
		logger.info("TOTAL ALLOWED BOOKINGS: " + role.getBookings_allowed());
		return role.getBookings_allowed();
		
	}

	public List<String> getRolesNames() {
		List<Role> roles = getRoles();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < roles.size(); i++){
			names.add(roles.get(i).getName());
		}
		return names;
	}

}
