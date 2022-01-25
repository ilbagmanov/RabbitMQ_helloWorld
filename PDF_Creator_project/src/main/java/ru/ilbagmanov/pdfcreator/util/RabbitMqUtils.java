package ru.ilbagmanov.pdfcreator.util;

public class RabbitMqUtils {

    private final static String FOOD_ROUTING_KEY = "files.pdf.food";
    private final static String MONEY_ROUTING_KEY = "files.pdf.money";
    private final static String DISMISSAL_ROUTING_KEY = "files.pdf.dismissal";

    public static String getKey(String key) {
        switch (key) {
            case "food":
                return FOOD_ROUTING_KEY;
            case "money":
                return MONEY_ROUTING_KEY;
            case "dismissal":
                return DISMISSAL_ROUTING_KEY;
        }
        throw new IllegalArgumentException("Type of document has mistakes");
    }
}
