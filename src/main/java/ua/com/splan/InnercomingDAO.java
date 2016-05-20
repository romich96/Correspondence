package ua.com.splan;

import java.util.List;

public interface InnercomingDAO {
    void add(Innercoming innercoming);
    void delete(long toDelete);
    void update(Innercoming innercoming, long toUpdate);
    Innercoming getInnercoming(long id);
    InnercomingFile getInnercomingFile(long id);
    List<Innercoming> list();
    List<InnercomingFile> getFiles(Innercoming innercoming);
    void deleteInnercomingFile(long toDelete);
    List<InnercomingFile> getAllFiles();
    Long getRecordsCount();
    Long getFilesCount();
}
