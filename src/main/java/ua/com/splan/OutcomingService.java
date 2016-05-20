package ua.com.splan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OutcomingService {
    @Autowired
    private OutcomingDAO outcomingDAO;

    @Transactional
    public void addOutcoming(Outcoming outcoming) {
        outcomingDAO.add(outcoming);
    }

    @Transactional
    public void deleteOutcoming(long toDelete) {
        outcomingDAO.delete(toDelete);
    }

    @Transactional
    public void updateOutcoming(Outcoming outcoming, long toUpdate) {
        outcomingDAO.update(outcoming, toUpdate);
    }

    @Transactional
    public Outcoming getOutcoming(long id) {
        return outcomingDAO.getOutcoming(id);
    }

    @Transactional
    public OutcomingFile getOutcomingFile(long id) {
        return outcomingDAO.getOutcomingFile(id);
    }

    @Transactional
    public List<OutcomingFile> getFiles(Outcoming outcoming) {
        return outcomingDAO.getFiles(outcoming);
    }

    @Transactional
    public List<OutcomingFile> getAllFiles() {
        return outcomingDAO.getAllFiles();
    }

    @Transactional
    public void deleteOutcomingFile(long toDelete) {
        outcomingDAO.deleteOutcomingFile(toDelete);
    }

    @Transactional(readOnly = true)
    public List<Outcoming> listOutcomings() {
        return outcomingDAO.list();
    }

    @Transactional
    public long getRecordsCount () { return outcomingDAO.getRecordsCount();}


    @Transactional
    public long getFilesCount () { return outcomingDAO.getFilesCount();}
}
