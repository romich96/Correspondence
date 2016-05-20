package ua.com.splan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Innercomings")
public class Innercoming {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "innercoming", cascade = CascadeType.ALL)
    private List<InnercomingFile> files = new ArrayList<InnercomingFile>();

    private String innerDocRegNo;

    private Date innerDocRegDate;

    private String innerDocType;

    private String innerDocSender;

    private String innerDocReciever;

    private String innerDocTitle;

    private Date innerDocControlDate;

    private String innerDocExecutor;

    private Date innerDocFulfilDate;

    private String innerDocAuthor;

    private Date innerDocRegistered;

    private String innerDocUpdater;

    private Date innerDocUpdated;

    public Innercoming() {
    }

    public Innercoming(String innerDocRegNo, Date innerDocRegDate, String innerDocType, String innerDocSender, String innerDocReciever, String innerDocTitle, Date innerDocControlDate, String innerDocExecutor, Date innerDocFulfilDate) {
        this.innerDocRegNo = innerDocRegNo;
        this.innerDocRegDate = innerDocRegDate;
        this.innerDocType = innerDocType;
        this.innerDocSender = innerDocSender;
        this.innerDocReciever = innerDocReciever;
        this.innerDocTitle = innerDocTitle;
        this.innerDocControlDate = innerDocControlDate;
        this.innerDocExecutor = innerDocExecutor;
        this.innerDocFulfilDate = innerDocFulfilDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<InnercomingFile> getFiles() {
        return files;
    }

//    public void setFiles(List<IncomingFile> files) {
//        for (IncomingFile file : files) {
//            file.setIncoming(this);
//        }
//        this.files = files;
//    }

    public void addFiles(List<InnercomingFile> files) {
        for (InnercomingFile file : files) {
            file.setInnercoming(this);
            this.files.add(file);
        }
    }


    public String getInnerDocRegNo() {
        return innerDocRegNo;
    }

    public void setInnerDocRegNo(String innerDocRegNo) {
        this.innerDocRegNo = innerDocRegNo;
    }

    public Date getInnerDocRegDate() {
        return innerDocRegDate;
    }

    public void setInnerDocRegDate(Date innerDocRegDate) {
        this.innerDocRegDate = innerDocRegDate;
    }

    public String getInnerDocType() {
        return innerDocType;
    }

    public void setInnerDocType(String innerDocType) {
        this.innerDocType = innerDocType;
    }

    public String getInnerDocSender() {
        return innerDocSender;
    }

    public void setInnerDocSender(String innerDocSender) {
        this.innerDocSender = innerDocSender;
    }

    public String getInnerDocReciever() {
        return innerDocReciever;
    }

    public void setInnerDocReciever(String innerDocReciever) {
        this.innerDocReciever = innerDocReciever;
    }

    public String getInnerDocTitle() {
        return innerDocTitle;
    }

    public void setInnerDocTitle(String innerDocTitle) {
        this.innerDocTitle = innerDocTitle;
    }

    public Date getInnerDocControlDate() {
        return innerDocControlDate;
    }

    public void setInnerDocControlDate(Date innerDocControlDate) {
        this.innerDocControlDate = innerDocControlDate;
    }

    public String getInnerDocExecutor() {
        return innerDocExecutor;
    }

    public void setInnerDocExecutor(String innerDocExecutor) {
        this.innerDocExecutor = innerDocExecutor;
    }

    public Date getInnerDocFulfilDate() {
        return innerDocFulfilDate;
    }

    public void setInnerDocFulfilDate(Date innerDocFulfilDate) {
        this.innerDocFulfilDate = innerDocFulfilDate;
    }

    public String getInnerDocAuthor() {
        return innerDocAuthor;
    }

    public void setInnerDocAuthor(String innerDocAuthor) {
        this.innerDocAuthor = innerDocAuthor;
    }

    public Date getInnerDocRegistered() {
        return innerDocRegistered;
    }

    public void setInnerDocRegistered(Date innerDocRegistered) {
        this.innerDocRegistered = innerDocRegistered;
    }

    public String getInnerDocUpdater() {
        return innerDocUpdater;
    }

    public void setInnerDocUpdater(String innerDocUpdater) {
        this.innerDocUpdater = innerDocUpdater;
    }

    public Date getInnerDocUpdated() {
        return innerDocUpdated;
    }

    public void setInnerDocUpdated(Date innerDocUpdated) {
        this.innerDocUpdated = innerDocUpdated;
    }
}