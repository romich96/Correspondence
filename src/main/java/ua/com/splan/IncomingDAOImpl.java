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
public class IncomingDAOImpl implements IncomingDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Incoming incoming) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authorName = auth.getName();
        Date registered = Calendar.getInstance().getTime();
        incoming.setInDocAuthor(authorName);
        incoming.setInDocRegistered(registered);
        entityManager.persist(incoming);
    }

    @Override
    public void delete(long toDelete) {
        Incoming incoming =  entityManager.find(Incoming.class, toDelete);
        if (incoming != null) {
            entityManager.remove(incoming);
        }
    }

    @Override
    public void update(Incoming incoming, long toUpdate) {
        Incoming incomingToUpdate =  entityManager.find(Incoming.class, toUpdate);
        incomingToUpdate.setInDocRegNo(incoming.getInDocRegNo());
        incomingToUpdate.setInDocRegDate(incoming.getInDocRegDate());
        incomingToUpdate.setInDocType(incoming.getInDocType());
        incomingToUpdate.setInDocSender(incoming.getInDocSender());
        incomingToUpdate.setInDocReciever(incoming.getInDocReciever());
        incomingToUpdate.setInDocTitle(incoming.getInDocTitle());
        incomingToUpdate.setInDocResolution(incoming.getInDocResolution());
        incomingToUpdate.setInDocRezoDate(incoming.getInDocRezoDate());
        incomingToUpdate.setInDocControlDate(incoming.getInDocControlDate());
        incomingToUpdate.setInDocExecutor(incoming.getInDocExecutor());
        incomingToUpdate.setInDocFulfilDate(incoming.getInDocFulfilDate());
        incomingToUpdate.setOutDocRegNo(incoming.getOutDocRegNo());
        incomingToUpdate.setOutDocRegDate(incoming.getOutDocRegDate());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String updaterName = auth.getName();
        incomingToUpdate.setInDocUpdater(updaterName);
        Date updated = Calendar.getInstance().getTime();
        incomingToUpdate.setInDocUpdated(updated);
        incomingToUpdate.addFiles(incoming.getFiles());
    }

    @Override
    public Incoming getIncoming (long id) {
        Incoming incoming =  entityManager.find(Incoming.class, id);
        return incoming;
    }

    @Override
    public IncomingFile getIncomingFile (long id) {
        IncomingFile incomingFile =  entityManager.find(IncomingFile.class, id);
        return incomingFile;
    }

    @Override
    public List<Incoming> list() {
        Query query = entityManager.createQuery("SELECT c FROM Incoming c ORDER BY c.inDocRegDate DESC ", Incoming.class);
        return (List<Incoming>) query.getResultList();
    }

    @Override
    public List<IncomingFile> getFiles(Incoming incoming) {
        Query query = entityManager.createQuery("SELECT c FROM IncomingFile c WHERE c.incoming = :incoming ", IncomingFile.class);
        query.setParameter("incoming", incoming);
        return (List<IncomingFile>) query.getResultList();
    }

    @Override
    public List<IncomingFile> getAllFiles() {
        Query query = entityManager.createQuery("SELECT c FROM IncomingFile c ", IncomingFile.class);
        return (List<IncomingFile>) query.getResultList();
    }

    public void deleteIncomingFile (long toDelete)
    {
        IncomingFile incomingFile = entityManager.find(IncomingFile.class, toDelete);
        Incoming incoming =  entityManager.find(Incoming.class, toDelete);
        if (incomingFile != null) {
            entityManager.remove(incomingFile);
        }
    }

    @Override
    public Long getRecordsCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM Incoming c");
        long count = (long)query.getSingleResult();
        return count;
    }

    @Override
    public Long getFilesCount()
    {
        Query query = entityManager.createQuery("SELECT count(*) FROM IncomingFile c");
        long count = (long)query.getSingleResult();
        return count;
    }
}
