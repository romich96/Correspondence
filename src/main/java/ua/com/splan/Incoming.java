package ua.com.splan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Incomings")
public class Incoming {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy="incoming", cascade=CascadeType.ALL)
    private List<IncomingFile> files = new ArrayList<IncomingFile>();

    private String inDocRegNo;

    private Date inDocRegDate;

    private String inDocType;

    private String inDocSender;

    private String inDocReciever;

    private String inDocTitle;

    private String inDocResolution;

    private Date inDocRezoDate;

    private Date inDocControlDate;

    private String inDocExecutor;

    private Date inDocFulfilDate;

    private String outDocRegNo;

    private Date outDocRegDate;

    private String inDocAuthor;

    private Date inDocRegistered;

    private String inDocUpdater;

    private Date inDocUpdated;

    public Incoming () {}

    public Incoming(String inDocRegNo, Date inDocRegDate, String inDocType, String inDocSender, String inDocReciever, String inDocTitle, String inDocResolution, Date inDocRezoDate, Date inDocControlDate, String inDocExecutor, Date inDocFulfilDate, String outDocRegNo, Date outDocRegDate) {
        this.inDocRegNo = inDocRegNo;
        this.inDocRegDate = inDocRegDate;
        this.inDocType = inDocType;
        this.inDocSender = inDocSender;
        this.inDocReciever = inDocReciever;
        this.inDocTitle = inDocTitle;
        this.inDocResolution = inDocResolution;
        this.inDocRezoDate = inDocRezoDate;
        this.inDocControlDate = inDocControlDate;
        this.inDocExecutor = inDocExecutor;
        this.inDocFulfilDate = inDocFulfilDate;
        this.outDocRegNo = outDocRegNo;
        this.outDocRegDate = outDocRegDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<IncomingFile> getFiles() {
        return files;
    }

//    public void setFiles(List<IncomingFile> files) {
//        for (IncomingFile file : files) {
//            file.setIncoming(this);
//        }
//        this.files = files;
//    }

    public void addFiles(List<IncomingFile> files) {
        for (IncomingFile file : files) {
            file.setIncoming(this);
            this.files.add(file);
        }
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

    public String getInDocType() {
        return inDocType;
    }

    public void setInDocType(String inDocType) {
        this.inDocType = inDocType;
    }

    public String getInDocSender() {
        return inDocSender;
    }

    public void setInDocSender(String inDocSender) {
        this.inDocSender = inDocSender;
    }

    public String getInDocReciever() {
        return inDocReciever;
    }

    public void setInDocReciever(String inDocReciever) {
        this.inDocReciever = inDocReciever;
    }

    public String getInDocTitle() {
        return inDocTitle;
    }

    public void setInDocTitle(String inDocTitle) {
        this.inDocTitle = inDocTitle;
    }

    public String getInDocResolution() {
        return inDocResolution;
    }

    public void setInDocResolution(String inDocResolution) {
        this.inDocResolution = inDocResolution;
    }

    public Date getInDocRezoDate() {
        return inDocRezoDate;
    }

    public void setInDocRezoDate(Date inDocRezoDate) {
        this.inDocRezoDate = inDocRezoDate;
    }

    public Date getInDocControlDate() {
        return inDocControlDate;
    }

    public void setInDocControlDate(Date inDocControlDate) {
        this.inDocControlDate = inDocControlDate;
    }

    public String getInDocExecutor() {
        return inDocExecutor;
    }

    public void setInDocExecutor(String inDocExecutor) {
        this.inDocExecutor = inDocExecutor;
    }

    public Date getInDocFulfilDate() {
        return inDocFulfilDate;
    }

    public void setInDocFulfilDate(Date inDocFulfilDate) {
        this.inDocFulfilDate = inDocFulfilDate;
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

    public String getInDocAuthor() {
        return inDocAuthor;
    }

    public void setInDocAuthor(String inDocAuthor) {
        this.inDocAuthor = inDocAuthor;
    }

    public Date getInDocRegistered() {
        return inDocRegistered;
    }

    public void setInDocRegistered(Date inDocRegistered) {
        this.inDocRegistered = inDocRegistered;
    }

    public String getInDocUpdater() {
        return inDocUpdater;
    }

    public void setInDocUpdater(String inDocUpdater) {
        this.inDocUpdater = inDocUpdater;
    }

    public Date getInDocUpdated() {
        return inDocUpdated;
    }

    public void setInDocUpdated(Date inDocUpdated) {
        this.inDocUpdated = inDocUpdated;
    }
}
