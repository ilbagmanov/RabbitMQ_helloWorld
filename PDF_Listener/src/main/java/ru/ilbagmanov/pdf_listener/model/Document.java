package ru.ilbagmanov.pdf_listener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ilbagmanov.pdf_listener.model.enums.DocumentStatus;
import ru.ilbagmanov.pdf_listener.model.enums.DocumentType;

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
