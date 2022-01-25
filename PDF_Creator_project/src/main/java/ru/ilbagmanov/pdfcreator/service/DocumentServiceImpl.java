package ru.ilbagmanov.pdfcreator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ilbagmanov.pdfcreator.dto.PdfDto;
import ru.ilbagmanov.pdfcreator.model.Document;
import ru.ilbagmanov.pdfcreator.model.enums.DocumentStatus;
import ru.ilbagmanov.pdfcreator.model.enums.DocumentType;
import ru.ilbagmanov.pdfcreator.repository.DocumentRepository;
import ru.ilbagmanov.pdfcreator.repository.UsersRepository;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Long savePdfRequest(Long owner, String documentType) {
        Document doc = Document.builder()
                .owner(usersRepository.getById(owner))
                .status(DocumentStatus.PROCESS)
                .type(DocumentType.stringToEnum(documentType))
                .build();
        return documentRepository.save(doc).getId();
    }

    @Override
    public Document getDocumentById(Long id) {
        return documentRepository.getDocumentById(id);
    }

    @Override
    public List<Document> getAllDocuments(Long ownerId) {
        return documentRepository.getDocumentsByOwner_Id(ownerId);
    }
}
