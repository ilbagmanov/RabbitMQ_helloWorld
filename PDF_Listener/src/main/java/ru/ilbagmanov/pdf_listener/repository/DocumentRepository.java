package ru.ilbagmanov.pdf_listener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilbagmanov.pdf_listener.model.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document getDocumentById(Long id);

    List<Document> getDocumentsByOwner_Id(Long id);
}
