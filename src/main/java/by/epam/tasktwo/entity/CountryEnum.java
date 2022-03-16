package by.epam.tasktwo.entity;

public enum CountryEnum {
    England("England"),
    USA("USA"),
    Thailand("Thailand"),
    Japan("Japan");

    private String origin;

    CountryEnum(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
