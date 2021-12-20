package learn.solar.ui;

import learn.solar.domain.PanelResult;
import learn.solar.models.Panel;
import learn.solar.models.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner console = new Scanner(System.in);

    public MenuOptions displayMenuSelect() {
        MenuOptions[] values = MenuOptions.values();
        printHeader("Main Menu");
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s%n", i, values[i].getTitle());
        }
        int index = readInt("Select [0-4]: ", 0, 5);
        return values[index];
    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public Panel makePanel() {
        Panel panel = new Panel();
        panel.setSection(readRequiredString("Section: "));
        panel.setRow(readInt("Row: "));
        panel.setColumn(readInt("Column: "));
        panel.setMaterial(readPanelMaterial());
        panel.setYearInstalled(readInt("Year Installed: "));
        panel.setTracking(readBoolean("Sun Tracking Tech Installed [y/n]: "));

        return panel;
    }

    public void displayPanelsSection(List<Panel> panels) {
        if (panels.size() == 0) {
            System.out.println("No Panels to be Displayed.");
        } else {
            for (Panel p : panels) {
                if (true) {
                    System.out.printf("%s, %s, %s, %s, %s, %s%n",
                            p.getSection(),
                            p.getRow(),
                            p.getColumn(),
                            p.getMaterial(),
                            p.getYearInstalled(),
                            p.isTracking());
                }
            }
        }
    }


    public Panel update(List<Panel> panels) {
        displayPanelsSection(panels);
        Panel t = readPanelKeyNoSection();
        for (Panel p : panels) {
            t.setSection(p.getSection());
            if (p.getSection().equals(t.getSection())
                    && p.getRow() == t.getRow()
                    && p.getColumn() == t.getColumn()) {
                p.setMaterial(readPanelMaterial());
                p.setYearInstalled(readInt("Year Installed: "));
                p.setTracking(readBoolean("Sun Tracking Tech Installed [y/n]: "));
                return p;
            }
        }
        System.out.println("\nCannot be Updated");
        return t;
    }


    public Panel deletePanel() {
        return readPanelKey();
    }


    public void displayResult(PanelResult result) {
        if (result.isSuccess()) {
            printHeader("Success");
        } else {
            printHeader("Err: " + result.getErrorMessages());
        }
    }


    public Material readPanelMaterial() {
        System.out.println("Materials: ");
        Material[] values = Material.values();
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%s. %s%n", i, values[i].getName());
        }
        int index = readInt("Select [0-5]: ", 0, 5);
        return values[index];

    }

    public String readSection() {
        return readString("Enter Section: ");
    }

    private boolean readBoolean(String prompt) {
        String result = readString(prompt);
        return result.equalsIgnoreCase("y");
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt) {
        String result = null;
        do {
            result = readString(prompt).trim();
            if (result.length() == 0) {
                System.out.println("Value is required.");
            }
        } while (result.length() == 0);
        return result;
    }

    private int readInt(String prompt) {
        int result = 0;
        boolean isValid = false;

        do {
            String value = readRequiredString(prompt);
            try {
                result = Integer.parseInt(value);
                isValid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Value must be a number [1-250].");
            }
        } while (!isValid);

        return result;
    }

    private int readInt(String prompt, int min, int max) {
        int result = 0;
        do {
            result = readInt(prompt);
            if (result < min || result > max) {
                System.out.printf("%nValue must be between %s and %s. %n", min, max);
            }
        } while (result < min || result > max);

        return result;
    }


    private Panel readPanelKey() {

        String section = readString("Enter Section: ");
        int row = readInt("Enter Row: ");
        int column = readInt("Enter Column: ");

        return new Panel(section, row, column, null, -1, false);

    }

    private Panel readPanelKeyNoSection() {
        int row = readInt("Enter Row: ");
        int column = readInt("Enter Column: ");

        return new Panel(null, row, column, null, 0, false);
    }

}