package ru.ilbagmanov.pdfcreator.model.enums;

public enum DocumentType {
    FOOD, MONEY, DISMISSAL;

    public static DocumentType stringToEnum(String e) {
        switch (e) {
            case "food":
                return FOOD;
            case "money":
                return MONEY;
            case "dismissal":
                return DISMISSAL;
        }
        return null;
    }
}
