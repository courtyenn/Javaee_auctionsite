package edu.neumont.csc280.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.neumont.csc280.models.Bid;

@Stateless
@LocalBean
public class AuctionManager {
	@PersistenceContext(unitName = "AuctionService")
	EntityManager em;

	public Bid findBidById(int bidId) {
		return em.find(Bid.class, bidId);
	}

	public void deleteBidById(int bidId) {
		Bid bid = em.find(Bid.class, bidId);
		em.remove(bid);
	}

	// public void
}
