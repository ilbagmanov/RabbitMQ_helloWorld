package ru.ilbagmanov.pdf_listener.listener;

import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ilbagmanov.pdf_listener.dto.PdfDto;
import ru.ilbagmanov.pdf_listener.service.DocumentService;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;


@Component
public class FilesMessageListener {

    @Autowired
    private DocumentService service;

    @RabbitListener(queues = "food", containerFactory = "containerFactory")
    public void onMessage(Message message) {
        try {
            Gson gson = new Gson();
            PdfDto dto = gson.fromJson(new String(message.getBody()), PdfDto.class);
            createPdf(dto, "Please give me some food");
            FileInputStream stream = new FileInputStream("food.pdf");
            byte[] array = new byte[stream.available()];
            stream.read(array);
            service.addPdfToDocument(Long.valueOf(dto.getId()), array);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "money", containerFactory = "containerFactory")
    public void worker2(Message message) {
        try {
            Gson gson = new Gson();
            PdfDto dto = gson.fromJson(new String(message.getBody()), PdfDto.class);
            createPdf(dto, "please give me some money");
            BufferedInputStream stream = new BufferedInputStream(new FileInputStream("food.pdf"));
            byte[] array = new byte[stream.available()];
            stream.read(array);
            service.addPdfToDocument(Long.valueOf(dto.getId()), array);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    @RabbitListener(queues = "dismissal", containerFactory = "containerFactory")
    public void worker3(Message message) {
        try {
            Gson gson = new Gson();
            PdfDto dto = gson.fromJson(new String(message.getBody()), PdfDto.class);
            createPdf(dto, "please give me a dismissal");
            FileInputStream stream = new FileInputStream("food.pdf");
            byte[] array = new byte[stream.available()];
            stream.read(array);
            service.addPdfToDocument(Long.valueOf(dto.getId()), array);

        } catch (Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private void createPdf(PdfDto dto, String mainText) throws Exception {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("food.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph paragraph = new Paragraph("Statement\n", font);
        Paragraph paragraph1 = new Paragraph("I, " + dto.getName() + " " + dto.getSurname() + ", " + mainText + "\n", font);
        Paragraph paragraph2 = new Paragraph("Date: " + dto.getDate(), font);
        document.add(paragraph);
        document.add(paragraph1);
        document.add(paragraph2);
        document.close();
    }

}
