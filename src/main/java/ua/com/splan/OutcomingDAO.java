package ua.com.splan;

import java.util.List;

public interface OutcomingDAO {
    void add(Outcoming outcoming);
    void delete(long toDelete);
    void update(Outcoming outcoming, long toUpdate);
    Outcoming getOutcoming(long id);
    OutcomingFile getOutcomingFile(long id);
    List<Outcoming> list();
    List<OutcomingFile> getFiles(Outcoming outcoming);
    void deleteOutcomingFile(long toDelete);
    List<OutcomingFile> getAllFiles();
    Long getRecordsCount();
    Long getFilesCount();
}
