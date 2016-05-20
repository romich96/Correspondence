package ua.com.splan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Outcomings")
public class Outcoming {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="outcoming", cascade=CascadeType.ALL)
    private List<OutcomingFile> files = new ArrayList<OutcomingFile>();

    private String outDocRegNo;

    private Date outDocRegDate;

    private String outDocType;

    private String outDocSender;

    private String outDocReciever;

    private String outDocTitle;

    private Date outDocControlDate;

    private String outDocExecutor;

    private Date outDocFulfilDate;

    private String inDocRegNo;

    private Date inDocRegDate;

    private String outDocAuthor;

    private Date outDocRegistered;

    private String outDocUpdater;

    private Date outDocUpdated;

    public Outcoming() {}

    public Outcoming(String outDocRegNo, Date outDocRegDate, String outDocType, String outDocSender, String outDocReciever, String outDocTitle, Date outDocControlDate, String outDocExecutor, Date outDocFulfilDate, String inDocRegNo, Date inDocRegDate) {
        this.outDocRegNo = outDocRegNo;
        this.outDocRegDate = outDocRegDate;
        this.outDocType = outDocType;
        this.outDocSender = outDocSender;
        this.outDocReciever = outDocReciever;
        this.outDocTitle = outDocTitle;
        this.outDocControlDate = outDocControlDate;
        this.outDocExecutor = outDocExecutor;
        this.outDocFulfilDate = outDocFulfilDate;
        this.inDocRegNo = inDocRegNo;
        this.inDocRegDate = inDocRegDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OutcomingFile> getFiles() {
        return files;
    }

//    public void setFiles(List<OutcomingFile> files) {
//        this.files = files;
//    }

    public void addFiles(List<OutcomingFile> files) {
        for (OutcomingFile file : files) {
            file.setOutcoming(this);
            this.files.add(file);
        }
    }

    public String getOutDocRegNo() {
        return outDocRegNo;
    }

    public void setOutDocRegNo(String outDocRegNo) {
        this.outDocRegNo = outDocRegNo;
    }

    public Date getOutDocRegDate() {
        return outDocRegDate;
    }

    public void setOutDocRegDate(Date outDocRegDate) {
        this.outDocRegDate = outDocRegDate;
    }

    public String getOutDocType() {
        return outDocType;
    }

    public void setOutDocType(String outDocType) {
        this.outDocType = outDocType;
    }

    public String getOutDocSender() {
        return outDocSender;
    }

    public void setOutDocSender(String outDocSender) {
        this.outDocSender = outDocSender;
    }

    public String getOutDocReciever() {
        return outDocReciever;
    }

    public void setOutDocReciever(String outDocReciever) {
        this.outDocReciever = outDocReciever;
    }

    public String getOutDocTitle() {
        return outDocTitle;
    }

    public void setOutDocTitle(String outDocTitle) {
        this.outDocTitle = outDocTitle;
    }

    public Date getOutDocControlDate() {
        return outDocControlDate;
    }

    public void setOutDocControlDate(Date outDocControlDate) {
        this.outDocControlDate = outDocControlDate;
    }

    public String getOutDocExecutor() {
        return outDocExecutor;
    }

    public void setOutDocExecutor(String outDocExecutor) {
        this.outDocExecutor = outDocExecutor;
    }

    public Date getOutDocFulfilDate() {
        return outDocFulfilDate;
    }

    public void setOutDocFulfilDate(Date outDocFulfilDate) {
        this.outDocFulfilDate = outDocFulfilDate;
    }

    public String getInDocRegNo() {
        return inDocRegNo;
    }

    public void setInDocRegNo(String inDocRegNo) {
        this.inDocRegNo = inDocRegNo;
    }

    public Date getInDocRegDate() {
        return inDocRegDate;
    }

    public void setInDocRegDate(Date inDocRegDate) {
        this.inDocRegDate = inDocRegDate;
    }

    public String getOutDocAuthor() {
        return outDocAuthor;
    }

    public void setOutDocAuthor(String outDocAuthor) {
        this.outDocAuthor = outDocAuthor;
    }

    public Date getOutDocRegistered() {
        return outDocRegistered;
    }

    public void setOutDocRegistered(Date outDocRegistered) {
        this.outDocRegistered = outDocRegistered;
    }

    public String getOutDocUpdater() {
        return outDocUpdater;
    }

    public void setOutDocUpdater(String outDocUpdater) {
        this.outDocUpdater = outDocUpdater;
    }

    public Date getOutDocUpdated() {
        return outDocUpdated;
    }

    public void setOutDocUpdated(Date outDocUpdated) {
        this.outDocUpdated = outDocUpdated;
    }
}
