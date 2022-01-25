package ru.ilbagmanov.pdf_listener.service;

import ru.ilbagmanov.pdf_listener.model.Document;

import java.util.List;

public interface DocumentService {

    void addPdfToDocument(Long id, byte[] pdf);

    List<Document> getAllDocuments(Long ownerId);
}
