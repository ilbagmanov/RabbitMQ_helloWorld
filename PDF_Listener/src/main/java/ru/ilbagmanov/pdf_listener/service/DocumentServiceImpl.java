package ru.ilbagmanov.pdf_listener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilbagmanov.pdf_listener.model.Document;
import ru.ilbagmanov.pdf_listener.model.enums.DocumentStatus;
import ru.ilbagmanov.pdf_listener.repository.DocumentRepository;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository repository;

    @Override
    public void addPdfToDocument(Long id, byte[] pdf) {
        Document doc = repository.getDocumentById(id);
        doc.setPdf(pdf);
        doc.setStatus(DocumentStatus.DONE);
        repository.save(doc);
    }

    @Override
    public List<Document> getAllDocuments(Long ownerId) {
        return repository.getDocumentsByOwner_Id(ownerId);
    }
}
