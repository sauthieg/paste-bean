package org.ow2.jonas.azure.pastebean.service.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.ow2.jonas.azure.pastebean.service.HashService;
import org.ow2.jonas.azure.pastebean.service.PasteService;

/**
 * Session Bean implementation class PasteServiceBean
 */
@Stateless
public class PasteServiceBean implements PasteService {

	private EntityManager entityManager;
	
	private HashService hashService;

    private TimerService timerService;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @EJB
    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    @Resource
    public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
    }

    public Paste createPaste(String title, String author, String desc, String content) {
		Paste paste = new Paste();
        paste.setTitle(title);
		paste.setAuthor(author);
		paste.setDescription(desc);
		paste.setContent(content);
		paste.setTimestamp(System.currentTimeMillis());

        paste.setHash(hashService.createHash(paste));
		
		entityManager.persist(paste);

        // 10 minutes
        Timer t = timerService.createTimer(10 * 60 * 1000, paste);

		return paste;
	}

    @Timeout
    private void endOfLife(Timer timer) {
        deletePaste((Paste) timer.getInfo());
    }

	public void deletePaste(Paste paste) {
        Paste p = entityManager.merge(paste);
		entityManager.remove(p);
	}

	@SuppressWarnings("unchecked")
	public List<Paste> findPastes() {
		return (List<Paste>) entityManager.createNamedQuery("all").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Paste> findMatchingPastes(String hashFragment) {
		return (List<Paste>) entityManager.createNamedQuery("from_hash")
				.setParameter("pattern", hashFragment + "%")
				.getResultList();
	}

    @SuppressWarnings("unchecked")
    public List<Paste> findLatestPastes(int max) {
        return (List<Paste>) entityManager.createNamedQuery("all")
                .setFirstResult(0)
                .setMaxResults(max)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Paste> searchPastes(String token) {
        return (List<Paste>) entityManager.createNamedQuery("search")
                .setParameter("token", "%" + token + "%")
                .getResultList();
    }

}
