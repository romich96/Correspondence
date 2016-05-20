package ua.com.splan;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    File dir = new File(System.getProperty("catalina.base"), "my_uploads/");
    String filePath = dir.getPath() + "/";

    @Autowired
    private IncomingService incomingService;

    @Autowired
    private OutcomingService outcomingService;

    @Autowired
    private InnercomingService innercomingService;

    @Autowired
    private ReferenceService referenceService;

    @Autowired
    private UserSearch userSearch;

    @RequestMapping("/")
    public String start(Model model) {
        model.addAttribute("incomingsCount", incomingService.getRecordsCount());
        model.addAttribute("outcomingsCount", outcomingService.getRecordsCount());
        model.addAttribute("innercomingsCount", innercomingService.getRecordsCount());
        model.addAttribute("incomingFilesCount", incomingService.getFilesCount());
        model.addAttribute("outcomingFilesCount", outcomingService.getFilesCount());
        model.addAttribute("innercomingFilesCount", innercomingService.getFilesCount());
        return "index";
    }

    @RequestMapping("/outcoming")
    public String outcoming(Model model) {
        model.addAttribute("outcomings", outcomingService.listOutcomings());
        return "outcoming";
    }

    @RequestMapping("/innercoming")
    public String inner(Model model) {
        model.addAttribute("innercomings", innercomingService.listInnercomings());
        return "innercoming";
    }

    @RequestMapping("/incoming")
    public String incoming(Model model) {
        model.addAttribute("incomings", incomingService.listIncomings());
        System.out.println(incomingService.listIncomings());
        return "incoming";
    }

    @RequestMapping("/incoming_add_page")
    public String incomingAddPage(Model model) {
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "incoming_add_page";
    }

    @RequestMapping("/outcoming_add_page")
    public String outcomingAddPage(Model model) {
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "outcoming_add_page";
    }

    @RequestMapping("/innercoming_add_page")
    public String innercomingAddPage(Model model) {
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "innercoming_add_page";
    }

    @RequestMapping(value = "/incoming/delete", method = RequestMethod.POST)
    public void incomingDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        incomingService.deleteIncoming(toDelete);
        return;
    }

    @RequestMapping(value = "/outcoming/delete", method = RequestMethod.POST)
    public void outcomingDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        outcomingService.deleteOutcoming(toDelete);
        return;
    }

    @RequestMapping(value = "/innercoming/delete", method = RequestMethod.POST)
    public void innercomingDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        innercomingService.deleteInnercoming(toDelete);
        return;
    }

    StringBuilder extractTextFromFile(File dest, String ext) {
        StringBuilder text = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(dest.getAbsolutePath());
            if (ext.equals("doc")) {
                try {
                    HWPFDocument document = new HWPFDocument(fis);
                    WordExtractor extractor = new WordExtractor(document);
                    text.append(extractor.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("docx")) {
                try {
                    XWPFDocument xdoc = new XWPFDocument(fis);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
                    text.append(extractor.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("pptx")) {
                try {
                    XMLSlideShow ppt = new XMLSlideShow(fis);
                    for (XSLFSlide slide : ppt.getSlides()) {
                        for (XSLFShape sh : slide.getShapes()) {
                            if (sh instanceof XSLFTextShape) {
                                XSLFTextShape shape = (XSLFTextShape) sh;
                                text.append(shape.getText());
                                text.append(" ");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("ppt")) {
                try {
                    POIFSFileSystem fs = new POIFSFileSystem(fis);
                    HSLFSlideShow ppt = new HSLFSlideShow(fs);
                    for (HSLFSlide slide : ppt.getSlides()) {
                        for (HSLFShape sh : slide.getShapes()) {
                            if (sh instanceof HSLFTextShape) {
                                HSLFTextShape shape = (HSLFTextShape) sh;
                                text.append(shape.getText());
                                text.append(" ");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("pdf")) {
                try {
                    PDFParser parser = null;
                    PDDocument pdDoc = null;
                    COSDocument cosDoc = null;
                    PDFTextStripper pdfStripper;
                    parser = new PDFParser(fis);
                    parser.parse();
                    cosDoc = parser.getDocument();
                    pdfStripper = new PDFTextStripper();
                    pdDoc = new PDDocument(cosDoc);
                    text.append(pdfStripper.getText(pdDoc));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ext.equals("txt")) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        text.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    private ArrayList<IncomingFile> addFilesToIncoming(MultipartFile[] files) {
        ArrayList<IncomingFile> filesToAdd = new ArrayList<IncomingFile>();
        if (files != null && files.length > 0) {
            if (!files[0].isEmpty()) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        String fileName = files[i].getOriginalFilename();
                        byte[] uploadBytes = files[i].getBytes();
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] digest = md5.digest(uploadBytes);
                        String hashName = new BigInteger(1, digest).toString(16);
                        String newPath = filePath + "inbox/" + hashName;
                        String ext = FilenameUtils.getExtension(fileName);
                        File dest = new File(newPath + "." + ext);
                        if (!dest.exists()) {
                            files[i].transferTo(dest);
                        }
                        IncomingFile fileToAdd = new IncomingFile();
                        fileToAdd.setFileName(fileName);
                        fileToAdd.setFileHash(hashName);
                        fileToAdd.setFileType(ext);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        fileToAdd.setFileChangedDate(sdf.format(dest.lastModified()));
                        fileToAdd.setFileSize(files[i].getSize());
                        fileToAdd.setPlainTex(extractTextFromFile(dest, ext).toString().replaceAll("\n|\r", " "));
                        filesToAdd.add(fileToAdd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filesToAdd;
    }

    private ArrayList<OutcomingFile> addFilesToOutcoming(MultipartFile[] files) {
        ArrayList<OutcomingFile> filesToAdd = new ArrayList<OutcomingFile>();
        if (files != null && files.length > 0) {
            if (!files[0].isEmpty()) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        String fileName = files[i].getOriginalFilename();
                        byte[] uploadBytes = files[i].getBytes();
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] digest = md5.digest(uploadBytes);
                        String hashName = new BigInteger(1, digest).toString(16);
                        String newPath = filePath + "outbox/" + hashName;
                        String ext = FilenameUtils.getExtension(fileName);
                        File dest = new File(newPath + "." + ext);
                        if (!dest.exists()) {
                            files[i].transferTo(dest);
                        }
                        OutcomingFile fileToAdd = new OutcomingFile();
                        fileToAdd.setFileName(fileName);
                        fileToAdd.setFileHash(hashName);
                        fileToAdd.setFileType(ext);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        fileToAdd.setFileChangedDate(sdf.format(dest.lastModified()));
                        fileToAdd.setFileSize(files[i].getSize());
                        fileToAdd.setPlainTex(extractTextFromFile(dest, ext).toString().replaceAll("\n|\r", " "));
                        filesToAdd.add(fileToAdd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filesToAdd;
    }

    private ArrayList<InnercomingFile> addFilesToInnercoming(MultipartFile[] files) {
        ArrayList<InnercomingFile> filesToAdd = new ArrayList<InnercomingFile>();
        if (files != null && files.length > 0) {
            if (!files[0].isEmpty()) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        String fileName = files[i].getOriginalFilename();
                        byte[] uploadBytes = files[i].getBytes();
                        MessageDigest md5 = MessageDigest.getInstance("MD5");
                        byte[] digest = md5.digest(uploadBytes);
                        String hashName = new BigInteger(1, digest).toString(16);
                        String newPath = filePath + "innerbox/" + hashName;
                        String ext = FilenameUtils.getExtension(fileName);
                        File dest = new File(newPath + "." + ext);
                        if (!dest.exists()) {
                            files[i].transferTo(dest);
                        }
                        InnercomingFile fileToAdd = new InnercomingFile();
                        fileToAdd.setFileName(fileName);
                        fileToAdd.setFileHash(hashName);
                        fileToAdd.setFileType(ext);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                        fileToAdd.setFileChangedDate(sdf.format(dest.lastModified()));
                        fileToAdd.setFileSize(files[i].getSize());
                        fileToAdd.setPlainTex(extractTextFromFile(dest, ext).toString().replaceAll("\n|\r", " "));
                        filesToAdd.add(fileToAdd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filesToAdd;
    }

    @RequestMapping(value = "/incoming/add", method = RequestMethod.POST)
    public String incomingAdd(@RequestParam String inDocRegNo,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRegDate,
                              @RequestParam String inDocType,
                              @RequestParam String inDocSender,
                              @RequestParam String inDocReciever,
                              @RequestParam String inDocTitle,
                              @RequestParam String inDocResolution,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRezoDate,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocControlDate,
                              @RequestParam String inDocExecutor,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocFulfilDate,
                              @RequestParam String outDocRegNo,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocRegDate,
                              @RequestParam("upfiles[]") MultipartFile[] files,
                              Model model) {

        Incoming incoming = new Incoming(inDocRegNo, inDocRegDate, inDocType, inDocSender, inDocReciever, inDocTitle, inDocResolution, inDocRezoDate, inDocControlDate, inDocExecutor, inDocFulfilDate, outDocRegNo, outDocRegDate);
        incoming.addFiles(addFilesToIncoming(files));
        incomingService.addIncoming(incoming);
        model.addAttribute("incomings", incomingService.listIncomings());
        return "redirect:/incoming";
    }

    @RequestMapping(value = "/outcoming/add", method = RequestMethod.POST)
    public String outcomingAdd(@RequestParam String outDocRegNo,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocRegDate,
                               @RequestParam String outDocType,
                               @RequestParam String outDocSender,
                               @RequestParam String outDocReciever,
                               @RequestParam String outDocTitle,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocControlDate,
                               @RequestParam String outDocExecutor,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocFulfilDate,
                               @RequestParam String inDocRegNo,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRegDate,
                               @RequestParam("upfiles[]") MultipartFile[] files,
                               Model model) {
        Outcoming outcoming = new Outcoming(outDocRegNo, outDocRegDate, outDocType, outDocSender, outDocReciever, outDocTitle, outDocControlDate, outDocExecutor, outDocFulfilDate, inDocRegNo, inDocRegDate);
        outcoming.addFiles(addFilesToOutcoming(files));
        System.out.println(files.toString());
        outcomingService.addOutcoming(outcoming);
        model.addAttribute("outcomings", outcomingService.listOutcomings());
        return "redirect:/outcoming";
    }

    @RequestMapping(value = "/innercoming/add", method = RequestMethod.POST)
    public String innercomingAdd(@RequestParam String innerDocRegNo,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocRegDate,
                              @RequestParam String innerDocType,
                              @RequestParam String innerDocSender,
                              @RequestParam String innerDocReciever,
                              @RequestParam String innerDocTitle,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocControlDate,
                              @RequestParam String innerDocExecutor,
                              @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocFulfilDate,
                              @RequestParam("upfiles[]") MultipartFile[] files,
                              Model model) {
        Innercoming innercoming = new Innercoming(innerDocRegNo, innerDocRegDate, innerDocType, innerDocSender, innerDocReciever, innerDocTitle, innerDocControlDate, innerDocExecutor, innerDocFulfilDate);
        innercoming.addFiles(addFilesToInnercoming(files));
        innercomingService.addInnercoming(innercoming);
        model.addAttribute("innercomings", innercomingService.listInnercomings());
        return "redirect:/innercoming";
    }


    @RequestMapping(value = "/incoming/update", method = RequestMethod.POST)
    public String incomingUpdate(@RequestParam String id,
                                 @RequestParam String inDocRegNo,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRegDate,
                                 @RequestParam String inDocType,
                                 @RequestParam String inDocSender,
                                 @RequestParam String inDocReciever,
                                 @RequestParam String inDocTitle,
                                 @RequestParam String inDocResolution,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRezoDate,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocControlDate,
                                 @RequestParam String inDocExecutor,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocFulfilDate,
                                 @RequestParam String outDocRegNo,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocRegDate,
                                 @RequestParam("upfiles[]") MultipartFile[] files,
//                                 @RequestParam(value = "toDelete[]", required = false) long[] toDelete,
                                 Model model) {
        long toUpdate = Long.parseLong(id);
        Incoming incoming = new Incoming(inDocRegNo, inDocRegDate, inDocType, inDocSender, inDocReciever, inDocTitle, inDocResolution, inDocRezoDate, inDocControlDate, inDocExecutor, inDocFulfilDate, outDocRegNo, outDocRegDate);
        incoming.addFiles(addFilesToIncoming(files));
        incomingService.updateIncoming(incoming, toUpdate);
        model.addAttribute("incomings", incomingService.listIncomings());
        return "redirect:/incoming";
    }

    @RequestMapping(value = "/outcoming/update", method = RequestMethod.POST)
    public String outcomingUpdate(@RequestParam String id,
                                 @RequestParam String outDocRegNo,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocRegDate,
                                 @RequestParam String outDocType,
                                 @RequestParam String outDocSender,
                                 @RequestParam String outDocReciever,
                                 @RequestParam String outDocTitle,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocControlDate,
                                 @RequestParam String outDocExecutor,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outDocFulfilDate,
                                 @RequestParam String inDocRegNo,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inDocRegDate,
                                 @RequestParam("upfiles[]") MultipartFile[] files,
//                                 @RequestParam(value = "toDelete[]", required = false) long[] toDelete,
                                 Model model) {
        long toUpdate = Long.parseLong(id);
        Outcoming outcoming = new Outcoming(outDocRegNo, outDocRegDate, outDocType, outDocSender, outDocReciever, outDocTitle, outDocControlDate, outDocExecutor, outDocFulfilDate, inDocRegNo, inDocRegDate);
        outcoming.addFiles(addFilesToOutcoming(files));
        outcomingService.updateOutcoming(outcoming, toUpdate);
        model.addAttribute("outcomings", outcomingService.listOutcomings());
        return "redirect:/outcoming";
    }

    @RequestMapping(value = "/innercoming/update", method = RequestMethod.POST)
    public String innercomingUpdate(@RequestParam String id,
                                 @RequestParam String innerDocRegNo,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocRegDate,
                                 @RequestParam String innerDocType,
                                 @RequestParam String innerDocSender,
                                 @RequestParam String innerDocReciever,
                                 @RequestParam String innerDocTitle,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocControlDate,
                                 @RequestParam String innerDocExecutor,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date innerDocFulfilDate,
                                 @RequestParam("upfiles[]") MultipartFile[] files,
//                                 @RequestParam(value = "toDelete[]", required = false) long[] toDelete,
                                 Model model) {
        long toUpdate = Long.parseLong(id);
        Innercoming innercoming = new Innercoming(innerDocRegNo, innerDocRegDate, innerDocType, innerDocSender, innerDocReciever, innerDocTitle, innerDocControlDate, innerDocExecutor, innerDocFulfilDate);
        innercoming.addFiles(addFilesToInnercoming(files));
        innercomingService.updateInnercoming(innercoming, toUpdate);
        model.addAttribute("innercomings", innercomingService.listInnercomings());
        return "redirect:/innercoming";
    }

    @RequestMapping(value = "/incoming_update_page")
    public String incomingGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("incoming", incomingService.getIncoming(toUpdate));
//        Incoming incomingToUpdate = incomingService.getIncoming(toUpdate);
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "incoming_update_page";
    }

    @RequestMapping(value = "/outcoming_update_page")
    public String outcomingGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("outcoming", outcomingService.getOutcoming(toUpdate));
//        Incoming incomingToUpdate = incomingService.getIncoming(toUpdate);
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "outcoming_update_page";
    }

    @RequestMapping(value = "/innercoming_update_page")
    public String innercomingGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("innercoming", innercomingService.getInnercoming(toUpdate));
        model.addAttribute("types", referenceService.listOfTypes());
        model.addAttribute("employees", referenceService.listOfEmployees());
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "innercoming_update_page";
    }

    @RequestMapping(value = "/incoming_files_page")
    public String incomingFiles(@RequestParam("toFiles") long toFiles, Model model) {
        model.addAttribute("id", toFiles);
        model.addAttribute("incoming", incomingService.getIncoming(toFiles));
        Incoming incomingToUpdate = incomingService.getIncoming(toFiles);
        model.addAttribute("filesToDelete", incomingService.getFiles(incomingToUpdate));
        return "incoming_files_page";
    }

    @RequestMapping(value = "/outcoming_files_page")
    public String outcomingFiles(@RequestParam("toFiles") long toFiles, Model model) {
        model.addAttribute("id", toFiles);
        model.addAttribute("outcoming", outcomingService.getOutcoming(toFiles));
        Outcoming outcomingToUpdate = outcomingService.getOutcoming(toFiles);
        model.addAttribute("filesToDelete", outcomingService.getFiles(outcomingToUpdate));
        return "outcoming_files_page";
    }

    @RequestMapping(value = "/innercoming_files_page")
    public String innercomingFiles(@RequestParam("toFiles") long toFiles, Model model) {
        model.addAttribute("id", toFiles);
        model.addAttribute("innercoming", innercomingService.getInnercoming(toFiles));
        Innercoming innercomingToUpdate = innercomingService.getInnercoming(toFiles);
        model.addAttribute("filesToDelete", innercomingService.getFiles(innercomingToUpdate));
        return "innercoming_files_page";
    }

    @RequestMapping("/type")
    public String types(Model model) {
        model.addAttribute("types", referenceService.listOfTypes());
        return "type";
    }

    @RequestMapping("/outorganization")
    public String outorganizations(Model model) {
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        return "outorganization";
    }

    @RequestMapping("/employee")
    public String employees(Model model) {
        model.addAttribute("employees", referenceService.listOfEmployees());
        return "employee";
    }

    @RequestMapping("/executor")
    public String executors(Model model) {
        model.addAttribute("executors", referenceService.listOfExecutors());
        return "executor";
    }

    @RequestMapping("/type_add_page")
    public String typeAddPage(Model model) {
        return "type_add_page";
    }

    @RequestMapping(value = "/type/add", method = RequestMethod.POST)
    public String typeAdd(@RequestParam String name,
                          Model model) {
        Type type = new Type(name);
        referenceService.addType(type);
        model.addAttribute("types", referenceService.listOfTypes());
        return "redirect:/type";
    }

    @RequestMapping(value = "/type/delete", method = RequestMethod.POST)
    public String typeDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        referenceService.deleteType(toDelete);
        return "type";
    }

    @RequestMapping(value = "/type/update", method = RequestMethod.POST)
    public String typeUpdate(@RequestParam String id,
                             @RequestParam String name,
                             Model model) {
        long toUpdate = Long.parseLong(id);
        Type type = new Type(name);
        referenceService.updateType(type, toUpdate);
        model.addAttribute("types", referenceService.listOfTypes());
        return "redirect:/type";
    }

    @RequestMapping(value = "/type_update_page")
    public String typeGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("type", referenceService.getType(toUpdate));
        return "type_update_page";
    }

    @RequestMapping("/outorganization_add_page")
    public String outorganizationAddPage(Model model) {
        return "outorganization_add_page";
    }

    @RequestMapping(value = "/outorganization/add", method = RequestMethod.POST)
    public String outorganizationAdd(@RequestParam String name,
                                     Model model) {
        OutOrganization outorganization = new OutOrganization(name);
        referenceService.addOutOrganization(outorganization);
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        return "redirect:/outorganization";
    }

    @RequestMapping(value = "/outorganization/delete", method = RequestMethod.POST)
    public String outorganizationDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        referenceService.deleteOutOrganization(toDelete);
        return "outorganization";
    }

    @RequestMapping(value = "/outorganization/update", method = RequestMethod.POST)
    public String outorganizationUpdate(@RequestParam String id,
                                        @RequestParam String name,
                                        Model model) {
        long toUpdate = Long.parseLong(id);
        OutOrganization outorganization = new OutOrganization(name);
        referenceService.updateOutOrganization(outorganization, toUpdate);
        model.addAttribute("outorganizations", referenceService.listOfOutOrganizations());
        return "redirect:/outorganization";
    }

    @RequestMapping(value = "/outorganization_update_page")
    public String outorganizationGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("outorganization", referenceService.getOutOrganization(toUpdate));
        return "outorganization_update_page";
    }

    @RequestMapping("/employee_add_page")
    public String employeeAddPage(Model model) {
        return "employee_add_page";
    }

    @RequestMapping(value = "/employee/add", method = RequestMethod.POST)
    public String employeeAdd(@RequestParam String name,
                              Model model) {
        Employee employee = new Employee(name);
        referenceService.addEmployee(employee);
        model.addAttribute("employees", referenceService.listOfEmployees());
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/delete", method = RequestMethod.POST)
    public String employeeDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        referenceService.deleteEmployee(toDelete);
        return "employee";
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.POST)
    public String employeeUpdate(@RequestParam String id,
                                 @RequestParam String name,
                                 Model model) {
        long toUpdate = Long.parseLong(id);
        Employee employee = new Employee(name);
        referenceService.updateEmployee(employee, toUpdate);
        model.addAttribute("employees", referenceService.listOfEmployees());
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee_update_page")
    public String employeeGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("employee", referenceService.getEmployee(toUpdate));
        return "employee_update_page";
    }

    @RequestMapping("/executor_add_page")
    public String executorAddPage(Model model) {
        return "executor_add_page";
    }

    @RequestMapping(value = "/executor/add", method = RequestMethod.POST)
    public String executorAdd(@RequestParam String name,
                              Model model) {
        Executor executor = new Executor(name);
        referenceService.addExecutor(executor);
        model.addAttribute("Executors", referenceService.listOfExecutors());
        return "redirect:/executor";
    }

    @RequestMapping(value = "/executor/delete", method = RequestMethod.POST)
    public String executorDelete(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        referenceService.deleteExecutor(toDelete);
        return "executor";
    }

    @RequestMapping(value = "/executor/update", method = RequestMethod.POST)
    public String executorUpdate(@RequestParam String id,
                                 @RequestParam String name,
                                 Model model) {
        long toUpdate = Long.parseLong(id);
        Executor executor = new Executor(name);
        referenceService.updateExecutor(executor, toUpdate);
        model.addAttribute("Executors", referenceService.listOfExecutors());
        return "redirect:/executor";
    }

    @RequestMapping(value = "/executor_update_page")
    public String executorGetId(@RequestParam("toUpdate") long toUpdate, Model model) {
        model.addAttribute("id", toUpdate);
        model.addAttribute("executor", referenceService.getExecutor(toUpdate));
        return "executor_update_page";
    }

    @RequestMapping("/remember")
    public String remember(Model model) {
        model.addAttribute("thought", "Мысль на русском языке");
        return "remember";
    }

    @RequestMapping(value = "/download/inbox/{id}/{hash}", method = RequestMethod.GET)
    public void handleInboxFileDownload(HttpServletResponse response,
                                        @PathVariable(value = "id") long id,
                                        @PathVariable(value = "hash") String fileHash) {
        IncomingFile incomingFile = incomingService.getIncomingFile(id);
        if (!incomingFile.getFileHash().equals(fileHash))
            return;
        String newPath = filePath + "inbox/" + incomingFile.getFileHash() + "." + incomingFile.getFileType();
        File file = new File(newPath);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/" + incomingFile.getFileType());
        response.setContentLength(new Long(file.length()).intValue());
        String originalFileName = incomingFile.getFileName();
        try {
            byte[] fileNameBytes = originalFileName.getBytes("utf-8");
            String dispositionFileName = "";
            for (byte b : fileNameBytes) {
                dispositionFileName += (char) (b & 0xff);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + dispositionFileName + "\"");
            try {
                FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping(value = "/download/outbox/{id}/{hash}", method = RequestMethod.GET)
    public void handleOutboxFileDownload(HttpServletResponse response,
                                        @PathVariable(value = "id") long id,
                                        @PathVariable(value = "hash") String fileHash) {
        OutcomingFile outcomingFile = outcomingService.getOutcomingFile(id);
        if (!outcomingFile.getFileHash().equals(fileHash))
            return;
        String newPath = filePath + "outbox/" + outcomingFile.getFileHash() + "." + outcomingFile.getFileType();
        File file = new File(newPath);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/" + outcomingFile.getFileType());
        response.setContentLength(new Long(file.length()).intValue());
        String originalFileName = outcomingFile.getFileName();
        try {
            byte[] fileNameBytes = originalFileName.getBytes("utf-8");
            String dispositionFileName = "";
            for (byte b : fileNameBytes) {
                dispositionFileName += (char) (b & 0xff);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + dispositionFileName + "\"");
            try {
                FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping(value = "/download/innerbox/{id}/{hash}", method = RequestMethod.GET)
    public void handleInnerboxFileDownload(HttpServletResponse response,
                                        @PathVariable(value = "id") long id,
                                        @PathVariable(value = "hash") String fileHash) {
        InnercomingFile innercomingFile = innercomingService.getInnercomingFile(id);
        if (!innercomingFile.getFileHash().equals(fileHash))
            return;
        String newPath = filePath + "innerbox/" + innercomingFile.getFileHash() + "." + innercomingFile.getFileType();
        File file = new File(newPath);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/" + innercomingFile.getFileType());
        response.setContentLength(new Long(file.length()).intValue());
        String originalFileName = innercomingFile.getFileName();
        System.out.println(originalFileName);
        try {
            byte[] fileNameBytes = originalFileName.getBytes("utf-8");
            String dispositionFileName = "";
            for (byte b : fileNameBytes) {
                dispositionFileName += (char) (b & 0xff);
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + dispositionFileName + "\"");
            try {
                FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        List <SearchResult> searchResults = null;
        try {
            searchResults = userSearch.search(pattern);
//            System.out.println(pattern);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("pattern", pattern);
        return "search";
    }

    @RequestMapping(value = "/remove/incoming_file", method = RequestMethod.POST)
    public void handleInboxFileRemove(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        incomingService.deleteIncomingFile(toDelete);
        return;
    }

    @RequestMapping(value = "/remove/outcoming_file", method = RequestMethod.POST)
    public void handleOutboxFileRemove(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        outcomingService.deleteOutcomingFile(toDelete);
        return;
    }

    @RequestMapping(value = "/remove/innercoming_file", method = RequestMethod.POST)
    public void handleInnerboxFileRemove(@RequestParam(value = "toDelete", required = false) long toDelete, Model model) {
        innercomingService.deleteInnercomingFile(toDelete);
        return;
    }

    @RequestMapping("/incoming_allfiles")
    public String incoming_allfiles(Model model) {
        model.addAttribute("incoming_allfiles", incomingService.getAllFiles());
        return "incoming_allfiles";
    }

    @RequestMapping("/outcoming_allfiles")
    public String outcoming_allfiles(Model model) {
        model.addAttribute("outcoming_allfiles", outcomingService.getAllFiles());
        return "outcoming_allfiles";
    }

    @RequestMapping("/innercoming_allfiles")
    public String innercoming_allfiles(Model model) {
        model.addAttribute("innercoming_allfiles", innercomingService.getAllFiles());
        return "innercoming_allfiles";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }
}
