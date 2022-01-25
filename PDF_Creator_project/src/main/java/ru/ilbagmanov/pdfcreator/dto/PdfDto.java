package ru.ilbagmanov.pdfcreator.dto;

import com.google.gson.Gson;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PdfDto {

    String id;
    String type;
    String name;
    String surname;
    String date;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
