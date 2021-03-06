package com.BoxingGame.springsecurity.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.BoxingGame.springsecurity.entity.Boxer;

@Repository
public class BoxerDAOImpl implements BoxerDAO {
	
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Boxer> getBoxers() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Boxer> theQuery = 
				currentSession.createQuery("from Boxer order by name",
											Boxer.class);
		
		// execute query and get result list
		List<Boxer> boxers = theQuery.getResultList();
				
		// return the results		
		return boxers;
	}

	@Override
	public void saveBoxer(Boxer theBoxer) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the boxer
		currentSession.saveOrUpdate(theBoxer);

	}

	@Override
	public Boxer getBoxer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Boxer theBoxer = currentSession.get(Boxer.class, theId);
		
		return theBoxer;
	}

	@Override
	public void deleteBoxer(int theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Boxer where id=:boxerId");
		theQuery.setParameter("boxerId", theId);
		
		theQuery.executeUpdate();

	}

}
