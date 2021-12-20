package learn.solar.models;

public class Panel {

    private int panelId;
    private String section;
    private int row;
    private int column;
    private Material material;
    private int yearInstalled;
    private boolean isTracking;

    public Panel() {
    }

    public Panel(String section, int row, int column, Material material, int yearInstalled, boolean isTracking) {
        this.panelId = panelId;
        this.section = section;
        this.row = row;
        this.column = column;
        this.material = material;
        this.yearInstalled = yearInstalled;
        this.isTracking = isTracking;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getYearInstalled() {
        return yearInstalled;
    }

    public void setYearInstalled(int yearInstalled) {
        this.yearInstalled = yearInstalled;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public void setTracking(boolean tracking) {
        isTracking = tracking;
    }
}
