package ru.ilbagmanov.pdfcreator.contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ilbagmanov.pdfcreator.dto.PdfDto;
import ru.ilbagmanov.pdfcreator.model.Document;
import ru.ilbagmanov.pdfcreator.service.DocumentService;
import ru.ilbagmanov.pdfcreator.util.JwtUtils;
import ru.ilbagmanov.pdfcreator.util.RabbitMqUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {



    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private DocumentService service;


    @PostMapping("/create")
    public ResponseEntity<?> createPdf(@RequestBody PdfDto dto, @RequestHeader("Authorization") String token) {

        String key = RabbitMqUtils.getKey(dto.getType());
        dto.setId(service.savePdfRequest(JwtUtils.getId(token), dto.getType()).toString());
        rabbitTemplate.send("files_topic_exchange", key, new Message(dto.toString().getBytes(StandardCharsets.UTF_8)));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public List<Document> getAllDocuments(@RequestHeader("Authorization") String token) {
        return service.getAllDocuments(JwtUtils.getId(token));
    }

    @GetMapping("/get")
    public void getPdf(HttpServletResponse response, @RequestParam Long id) throws Exception{
        Document doc = service.getDocumentById(id);
        response.setHeader("Content-disposition", "attachment;filename=" + "sdf.pdf");
        response.setContentType("application/vnd.ms-pdf");
        response.getOutputStream().write(doc.getPdf());
        response.getOutputStream().flush();
    }
}
