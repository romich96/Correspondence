package ua.com.splan;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "InnercomingFiles")
public class InnercomingFile {

    @Id
    @GeneratedValue
    private long id;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name = "innercoming_id")
    private Innercoming innercoming;

    private String fileName;
    private String fileHash;
    private String fileType;
    private String fileChangedDate;
    private String fileLink;
    private long fileSize;

    @Column(columnDefinition="MEDIUMTEXT ")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String plainTex;

    public InnercomingFile() {}

    public InnercomingFile(Innercoming innercoming, String fileName, String fileHash, String fileType, String fileChangedDate, String fileLink, long fileSize, String plainTex) {
        this.innercoming = innercoming;
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

    public Innercoming getInnercoming() {
        return innercoming;
    }

    public void setInnercoming(Innercoming innercoming) {
        this.innercoming = innercoming;
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