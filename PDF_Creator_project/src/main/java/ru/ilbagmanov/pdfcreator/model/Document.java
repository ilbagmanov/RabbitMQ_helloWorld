package ru.ilbagmanov.pdfcreator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilbagmanov.pdfcreator.model.enums.DocumentStatus;
import ru.ilbagmanov.pdfcreator.model.enums.DocumentType;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private byte[] pdf;

    @Enumerated(value = EnumType.STRING)
    private DocumentStatus status;
}
