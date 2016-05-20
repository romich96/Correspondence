package ua.com.splan;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "OutcomingFiles")
public class OutcomingFile {

    @Id
    @GeneratedValue
    private long id;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "outcoming_id")
    private Outcoming outcoming;

    private String fileName;
    private String fileHash;
    private String fileType;
    private String fileChangedDate;
    private String fileLink;
    private long fileSize;

    @Column(columnDefinition="MEDIUMTEXT ")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String plainTex;

    public OutcomingFile() {}

    public OutcomingFile(Outcoming outcoming, String fileName, String fileHash, String fileType, String fileChangedDate, String fileLink, long fileSize, String plainTex) {
        this.outcoming = outcoming;
        this.fileName = fileName;
        this.fileName = fileHash;
        this.fileType = fileType;
        this.fileChangedDate = fileChangedDate;
        this.fileLink = fileLink;
        this.fileSize = fileSize;
        this.plainTex = plainTex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Outcoming getOutcoming() {
        return outcoming;
    }

    public void setOutcoming(Outcoming outcoming) {
        this.outcoming = outcoming;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileChangedDate() {
        return fileChangedDate;
    }

    public void setFileChangedDate(String fileChangedDate) {
        this.fileChangedDate = fileChangedDate;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getPlainTex() {
        return plainTex;
    }

    public void setPlainTex(String plainTex) {
        this.plainTex = plainTex;
    }
}