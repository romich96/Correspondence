package ua.com.splan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InnercomingService {
    @Autowired
    private InnercomingDAO innercomingDAO;

    @Transactional
    public void addInnercoming(Innercoming innercoming) {
        innercomingDAO.add(innercoming);
    }

    @Transactional
    public void deleteInnercoming(long toDelete) {
        innercomingDAO.delete(toDelete);
    }

    @Transactional
    public void updateInnercoming(Innercoming innercoming, long toUpdate) {
        innercomingDAO.update(innercoming, toUpdate);
    }

    @Transactional
    public Innercoming getInnercoming(long id) {
        return innercomingDAO.getInnercoming(id);
    }

    @Transactional
    public InnercomingFile getInnercomingFile(long id) {
        return innercomingDAO.getInnercomingFile(id);
    }

    @Transactional
    public List<InnercomingFile> getFiles(Innercoming innercoming) {
        return innercomingDAO.getFiles(innercoming);
    }

    @Transactional
    public void deleteInnercomingFile(long toDelete) {
        innercomingDAO.deleteInnercomingFile(toDelete);
    }

    @Transactional(readOnly = true)
    public List<Innercoming> listInnercomings() {
        return innercomingDAO.list();
    }

    @Transactional
    public List<InnercomingFile> getAllFiles() {
        return innercomingDAO.getAllFiles();
    }

    @Transactional
    public long getRecordsCount () { return innercomingDAO.getRecordsCount();}

    @Transactional
    public long getFilesCount () { return innercomingDAO.getFilesCount();}
}
