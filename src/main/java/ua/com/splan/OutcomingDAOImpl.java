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
public class OutcomingDAOImpl implements OutcomingDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Outcoming outcoming) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authorName = auth.getName();
        Date registered = Calendar.getInstance().getTime();
        outcoming.setOutDocAuthor(authorName);
        outcoming.setOutDocRegistered(registered);
        entityManager.persist(outcoming);
    }

    @Override
    public void delete(long toDelete) {
        Outcoming outcoming = entityManager.find(Outcoming.class, toDelete);
        if (outcoming != null) {
            entityManager.remove(outcoming);
        }
    }

    @Override
    public void update(Outcoming outcoming, long toUpdate) {
        Outcoming outcomingToUpdate = entityManager.find(Outcoming.class, toUpdate);
        outcomingToUpdate.setOutDocRegNo(outcoming.getOutDocRegNo());
        outcomingToUpdate.setOutDocRegDate(outcoming.getOutDocRegDate());
        outcomingToUpdate.setOutDocType(outcoming.getOutDocType());
        outcomingToUpdate.setOutDocSender(outcoming.getOutDocSender());
        outcomingToUpdate.setOutDocReciever(outcoming.getOutDocReciever());
        outcomingToUpdate.setOutDocTitle(outcoming.getOutDocTitle());
        outcomingToUpdate.setOutDocControlDate(outcoming.getOutDocControlDate());
        outcomingToUpdate.setOutDocExecutor(outcoming.getOutDocExecutor());
        outcomingToUpdate.setOutDocFulfilDate(outcoming.getOutDocFulfilDate());
        outcomingToUpdate.setInDocRegNo(outcoming.getInDocRegNo());
        outcomingToUpdate.setInDocRegDate(outcoming.getInDocRegDate());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String updaterName = auth.getName();
        outcomingToUpdate.setOutDocUpdater(updaterName);
        Date updated = Calendar.getInstance().getTime();
        outcomingToUpdate.setOutDocUpdated(updated);
        outcomingToUpdate.addFiles(outcoming.getFiles());
    }

    @Override
    public Outcoming getOutcoming(long id) {
        Outcoming outcoming = entityManager.find(Outcoming.class, id);
        return outcoming;
    }

    @Override
    public OutcomingFile getOutcomingFile(long id) {
        OutcomingFile outcomingFile = entityManager.find(OutcomingFile.class, id);
        return outcomingFile;
    }

    @Override
    public List<Outcoming> list() {
        Query query = entityManager.createQuery("SELECT c FROM Outcoming c ORDER BY c.outDocRegDate DESC ", Outcoming.class);
        return (List<Outcoming>) query.getResultList();
    }

    @Override
    public List<OutcomingFile> getFiles(Outcoming outcoming) {
        Query query = entityManager.createQuery("SELECT c FROM OutcomingFile c WHERE c.outcoming = :outcoming ", OutcomingFile.class);
        query.setParameter("outcoming", outcoming);
        return (List<OutcomingFile>) query.getResultList();
    }

    @Override
    public List<OutcomingFile> getAllFiles() {
        Query query = entityManager.createQuery("SELECT c FROM OutcomingFile c ", OutcomingFile.class);
        return (List<OutcomingFile>) query.getResultList();
    }

    public void deleteOutcomingFile(long toDelete) {
        OutcomingFile outcomingFile = entityManager.find(OutcomingFile.class, toDelete);
        Outcoming outcoming = entityManager.find(Outcoming.class, toDelete);
        if (outcomingFile != null) {
            entityManager.remove(outcomingFile);
        }
    }

    @Override
    public Long getRecordsCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM Outcoming c");
        long count = (long)query.getSingleResult();
        return count;
    }

    @Override
    public Long getFilesCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM OutcomingFile c");
        long count = (long)query.getSingleResult();
        return count;
    }
}
