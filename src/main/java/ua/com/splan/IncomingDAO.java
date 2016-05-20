package ua.com.splan;

import java.util.List;

public interface IncomingDAO {
    void add(Incoming incoming);
    void delete(long toDelete);
    void update (Incoming incoming, long toUpdate);
    Incoming getIncoming(long id);
    IncomingFile getIncomingFile(long id);
    List<Incoming> list();
    List<IncomingFile> getFiles(Incoming incoming);
    void deleteIncomingFile(long toDelete);
    List<IncomingFile> getAllFiles();
    Long getRecordsCount();
    Long getFilesCount();
}
