package ua.com.splan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncomingService {
    @Autowired
    private IncomingDAO incomingDAO;

    @Transactional
    public void addIncoming(Incoming incoming) {
        incomingDAO.add(incoming);
    }

    @Transactional
    public void deleteIncoming(long toDelete) {
        incomingDAO.delete(toDelete);
    }

    @Transactional
    public void updateIncoming(Incoming incoming, long toUpdate) {
        incomingDAO.update(incoming, toUpdate);
    }

    @Transactional
    public Incoming getIncoming(long id) {
        return incomingDAO.getIncoming(id);
    }

    @Transactional
    public IncomingFile getIncomingFile(long id) {
        return incomingDAO.getIncomingFile(id);
    }

    @Transactional
    public List<IncomingFile> getFiles(Incoming incoming) {
        return incomingDAO.getFiles(incoming);
    }

    @Transactional
    public void deleteIncomingFile(long toDelete) {
        incomingDAO.deleteIncomingFile(toDelete);
    }

    @Transactional(readOnly = true)
    public List<Incoming> listIncomings() {
        return incomingDAO.list();
    }

    @Transactional
    public List<IncomingFile> getAllFiles() {
        return incomingDAO.getAllFiles();
    }

    @Transactional
    public long getRecordsCount () { return incomingDAO.getRecordsCount();}

    @Transactional
    public long getFilesCount () { return incomingDAO.getFilesCount();}
}
