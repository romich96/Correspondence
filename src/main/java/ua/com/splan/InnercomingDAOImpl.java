package ua.com.splan;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public class InnercomingDAOImpl implements InnercomingDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Innercoming innercoming) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authorName = auth.getName();
        Date registered = Calendar.getInstance().getTime();
        innercoming.setInnerDocAuthor(authorName);
        innercoming.setInnerDocRegistered(registered);
        entityManager.persist(innercoming);
    }

    @Override
    public void delete(long toDelete) {
        Innercoming innercoming =  entityManager.find(Innercoming.class, toDelete);
        if (innercoming != null) {
            entityManager.remove(innercoming);
        }
    }

    @Override
    public void update(Innercoming innercoming, long toUpdate) {
        Innercoming innercomingToUpdate =  entityManager.find(Innercoming.class, toUpdate);
        innercomingToUpdate.setInnerDocRegNo(innercoming.getInnerDocRegNo());
        innercomingToUpdate.setInnerDocRegDate(innercoming.getInnerDocRegDate());
        innercomingToUpdate.setInnerDocType(innercoming.getInnerDocType());
        innercomingToUpdate.setInnerDocSender(innercoming.getInnerDocSender());
        innercomingToUpdate.setInnerDocReciever(innercoming.getInnerDocReciever());
        innercomingToUpdate.setInnerDocTitle(innercoming.getInnerDocTitle());
        innercomingToUpdate.setInnerDocControlDate(innercoming.getInnerDocControlDate());
        innercomingToUpdate.setInnerDocExecutor(innercoming.getInnerDocExecutor());
        innercomingToUpdate.setInnerDocFulfilDate(innercoming.getInnerDocFulfilDate());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String updaterName = auth.getName();
        innercomingToUpdate.setInnerDocUpdater(updaterName);
        Date updated = Calendar.getInstance().getTime();
        innercomingToUpdate.setInnerDocUpdated(updated);
        innercomingToUpdate.addFiles(innercoming.getFiles());
    }

    @Override
    public Innercoming getInnercoming (long id) {
        Innercoming innercoming =  entityManager.find(Innercoming.class, id);
        return innercoming;
    }

    @Override
    public InnercomingFile getInnercomingFile (long id) {
        InnercomingFile innercomingFile =  entityManager.find(InnercomingFile.class, id);
        return innercomingFile;
    }

    @Override
    public List<Innercoming> list() {
        Query query = entityManager.createQuery("SELECT c FROM Innercoming c ORDER BY c.innerDocRegDate DESC ", Innercoming.class);
        return (List<Innercoming>) query.getResultList();
    }

    @Override
    public List<InnercomingFile> getFiles(Innercoming innercoming) {
        Query query = entityManager.createQuery("SELECT c FROM InnercomingFile c WHERE c.innercoming = :innercoming ", InnercomingFile.class);
        query.setParameter("innercoming", innercoming);
        return (List<InnercomingFile>) query.getResultList();
    }

    @Override
    public List<InnercomingFile> getAllFiles() {
        Query query = entityManager.createQuery("SELECT c FROM InnercomingFile c ", InnercomingFile.class);
        return (List<InnercomingFile>) query.getResultList();
    }

    public void deleteInnercomingFile (long toDelete)
    {
        InnercomingFile innercomingFile = entityManager.find(InnercomingFile.class, toDelete);
        Innercoming innercoming =  entityManager.find(Innercoming.class, toDelete);
        if (innercomingFile != null) {
            entityManager.remove(innercomingFile);
        }
    }

    @Override
    public Long getRecordsCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM Innercoming c");
        long count = (long)query.getSingleResult();
        return count;
    }

    @Override
    public Long getFilesCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM InnercomingFile c");
        long count = (long)query.getSingleResult();
        return count;
    }
}
