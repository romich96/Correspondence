package ua.com.splan;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserSearch {

    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * A basic search for the entity User. The search is done by exact match per
     * keywords on fields name, city and email.
     *
     * @param text The query text.
     */
    public List search(String text) {

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        @SuppressWarnings("unchecked")
        List<SearchResult> allResults = new ArrayList<>();
        text.trim();
        String lastCharacter = text.substring(text.length()-1);

            // Вхідні файли
            QueryBuilder queryBuilderInbox =
                    fullTextEntityManager.getSearchFactory()
                            .buildQueryBuilder().forEntity(IncomingFile.class).get();
            org.apache.lucene.search.Query queryInbox;
            if (lastCharacter.equals("*"))
            {
                queryInbox =
                        queryBuilderInbox
                                .keyword()
                                .fuzzy()
                                .onField("plainTex")
                                .matching(text)
                                .createQuery();
            } else {
                queryInbox =
                        queryBuilderInbox
                                .phrase()
                                .withSlop(5)
                                .onField("plainTex")
                                .sentence(text)
                                .createQuery();
            }
            org.hibernate.search.jpa.FullTextQuery jpaQueryInbox =
                    fullTextEntityManager.createFullTextQuery(queryInbox, IncomingFile.class);
            List<IncomingFile> inboxResults = jpaQueryInbox.getResultList();
            for (int i = 0; i < inboxResults.size(); i++) {
                SearchResult mySearchResult = new SearchResult();
                mySearchResult.setFileRef("/download/inbox/" + Long.toString(inboxResults.get(i).getId()) + "/" + inboxResults.get(i).getFileHash());
                mySearchResult.setFileType(inboxResults.get(i).getFileType());
                mySearchResult.setFileName(inboxResults.get(i).getFileName());
                mySearchResult.setFileSize((long) inboxResults.get(i).getFileSize());
                mySearchResult.setFileChangedDate(inboxResults.get(i).getFileChangedDate());
                mySearchResult.setDocNumber(inboxResults.get(i).getIncoming().getInDocRegNo());
                mySearchResult.setDocDate(inboxResults.get(i).getIncoming().getInDocRegDate());
                mySearchResult.setDocType(inboxResults.get(i).getIncoming().getInDocType());
                mySearchResult.setDocSender(inboxResults.get(i).getIncoming().getInDocSender());
                mySearchResult.setDocTitle(inboxResults.get(i).getIncoming().getInDocTitle());
                mySearchResult.setDocNapryam("Вхідний документ");
                allResults.add(mySearchResult);
            }

            // Вихідні файли
            QueryBuilder queryBuilderOutbox =
                    fullTextEntityManager.getSearchFactory()
                            .buildQueryBuilder().forEntity(OutcomingFile.class).get();
            org.apache.lucene.search.Query queryOutbox;
            if (lastCharacter.equals("*"))
            {
                queryOutbox =
                        queryBuilderOutbox
                                .keyword()
                                .fuzzy()
                                .onField("plainTex")
                                .matching(text)
                                .createQuery();
            } else {
                queryOutbox =
                        queryBuilderOutbox
                                .phrase()
                                .withSlop(5)
                                .onField("plainTex")
                                .sentence(text)
                                .createQuery();
            }
            org.hibernate.search.jpa.FullTextQuery jpaQueryOut =
                    fullTextEntityManager.createFullTextQuery(queryOutbox, OutcomingFile.class);
            List<OutcomingFile> outboxResults = jpaQueryOut.getResultList();
            for (int i = 0; i < outboxResults.size(); i++) {
                SearchResult mySearchResult = new SearchResult();
                mySearchResult.setFileRef("/download/outbox/" + Long.toString(outboxResults.get(i).getId()) + "/" + outboxResults.get(i).getFileHash());
                mySearchResult.setFileType(outboxResults.get(i).getFileType());
                mySearchResult.setFileName(outboxResults.get(i).getFileName());
                mySearchResult.setFileSize((long) outboxResults.get(i).getFileSize());
                mySearchResult.setFileChangedDate(outboxResults.get(i).getFileChangedDate());
                mySearchResult.setDocNumber(outboxResults.get(i).getOutcoming().getOutDocRegNo());
                mySearchResult.setDocDate(outboxResults.get(i).getOutcoming().getOutDocRegDate());
                mySearchResult.setDocType(outboxResults.get(i).getOutcoming().getOutDocType());
                mySearchResult.setDocSender(outboxResults.get(i).getOutcoming().getOutDocSender());
                mySearchResult.setDocTitle(outboxResults.get(i).getOutcoming().getOutDocTitle());
                mySearchResult.setDocNapryam("Вихідний документ");
                allResults.add(mySearchResult);
            }

            // Внутрішні файли
            QueryBuilder queryBuilderInnerbox =
                    fullTextEntityManager.getSearchFactory()
                            .buildQueryBuilder().forEntity(InnercomingFile.class).get();
            org.apache.lucene.search.Query queryInnerbox;
            if (lastCharacter.equals("*"))
            {
                queryInnerbox =
                        queryBuilderInnerbox
                                .keyword()
                                .fuzzy()
                                .onField("plainTex")
                                .matching(text)
                                .createQuery();
            } else {
                queryInnerbox =
                        queryBuilderInnerbox
                                .phrase()
                                .withSlop(5)
                                .onField("plainTex")
                                .sentence(text)
                                .createQuery();
            }
            org.hibernate.search.jpa.FullTextQuery jpaQueryInner =
                    fullTextEntityManager.createFullTextQuery(queryInnerbox, InnercomingFile.class);
            List<InnercomingFile> innerboxResults = jpaQueryInner.getResultList();
            for (int i = 0; i < innerboxResults.size(); i++) {
                SearchResult mySearchResult = new SearchResult();
                mySearchResult.setFileRef("/download/innerbox/" + Long.toString(innerboxResults.get(i).getId()) + "/" + innerboxResults.get(i).getFileHash());
                mySearchResult.setFileType(innerboxResults.get(i).getFileType());
                mySearchResult.setFileName(innerboxResults.get(i).getFileName());
                mySearchResult.setFileSize((long) innerboxResults.get(i).getFileSize());
                mySearchResult.setFileChangedDate(innerboxResults.get(i).getFileChangedDate());
                mySearchResult.setDocNumber(innerboxResults.get(i).getInnercoming().getInnerDocRegNo());
                mySearchResult.setDocDate(innerboxResults.get(i).getInnercoming().getInnerDocRegDate());
                mySearchResult.setDocType(innerboxResults.get(i).getInnercoming().getInnerDocType());
                mySearchResult.setDocSender(innerboxResults.get(i).getInnercoming().getInnerDocSender());
                mySearchResult.setDocTitle(innerboxResults.get(i).getInnercoming().getInnerDocTitle());
                mySearchResult.setDocNapryam("Внутрішній документ");
                allResults.add(mySearchResult);
            }
//        System.out.println("Результат пошуку: " + allResults);
        return allResults;
    } // method search
} // class UserSearch