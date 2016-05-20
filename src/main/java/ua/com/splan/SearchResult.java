package ua.com.splan;

import java.util.Date;

/**
 * Created by Asus on 25.04.2016.
 */
public class SearchResult {
    private String fileRef;
    private String fileType;
    private String fileName;
    private long fileSize;
    private String fileChangedDate;
    private String docNumber;
    private Date docDate;
    private String docType;
    private String docSender;
    private String docTitle;
    private String docNapryam;

    public SearchResult() {}

    public SearchResult(String fileRef, String fileType, String fileName, long fileSize, String fileChangedDate, String docNumber, Date docDate, String docType, String docSender, String docTitle) {
        this.fileRef = fileRef;
        this.fileType = fileType;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileChangedDate = fileChangedDate;
        this.docNumber = docNumber;
        this.docDate = docDate;
        this.docType = docType;
        this.docSender = docSender;
        this.docTitle = docTitle;
    }

    public String getFileRef() {
        return fileRef;
    }

    public void setFileRef(String fileRef) {
        this.fileRef = fileRef;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileChangedDate() {
        return fileChangedDate;
    }

    public void setFileChangedDate(String fileChangedDate) {
        this.fileChangedDate = fileChangedDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocSender() {
        return docSender;
    }

    public void setDocSender(String docSender) {
        this.docSender = docSender;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocNapryam() {
        return docNapryam;
    }

    public void setDocNapryam(String docNapryam) {
        this.docNapryam = docNapryam;
    }
}
