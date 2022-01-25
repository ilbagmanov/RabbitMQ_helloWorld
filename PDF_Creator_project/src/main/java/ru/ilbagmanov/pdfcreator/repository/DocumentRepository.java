package ru.ilbagmanov.pdfcreator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilbagmanov.pdfcreator.model.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document getDocumentById(Long id);

    List<Document> getDocumentsByOwner_Id(long ownerId);
}
