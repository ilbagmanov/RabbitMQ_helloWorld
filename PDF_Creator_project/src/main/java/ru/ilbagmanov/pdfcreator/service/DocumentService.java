package ru.ilbagmanov.pdfcreator.service;

import ru.ilbagmanov.pdfcreator.dto.PdfDto;
import ru.ilbagmanov.pdfcreator.model.Document;

import java.util.List;

public interface DocumentService {

    Long savePdfRequest(Long owner, String documentType);

    Document getDocumentById(Long id);

    List<Document> getAllDocuments(Long ownerId);
}
