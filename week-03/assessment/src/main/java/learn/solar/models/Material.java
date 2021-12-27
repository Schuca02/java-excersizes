package learn.solar.models;

public enum Material {
    POLY_SI("Polycrystalline Silicon"),
    MONO_SI("Monocrystalline Silicon"),
    CDTE("Cadmium Telluride"),
    CIGS("Copper Indium Gallium Selenide"),
    A_SI("Amorphous Silicon");

    private final String name;

    Material(String name) {this.name = name; }

    public String getName() {
        return name;
    }
}
