package by.epam.tasktwo.entity;

public enum BrandEnum {
    B_AND_W("Bowers&Wilkins"),
    BOSE("Bose"),
    PRESONUS("Presonus"),
    HP("HP"),
    CANON("Canon"),
    XEROX("Xerox");

    private String brand;

    BrandEnum(String brand){
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}

