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
        int index = readInt("Select [0-5]: ", 0, 5);
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
                if (true
//                p.getSection().equalsIgnoreCase(section)
                ) {
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


//    public Panel updatePanel() {
//        Panel panel = readPanelKey(panels);
//        if(panel != null){
//            update(panel);
//        }
//
//        return panel;
//    }

    public Panel update(List<Panel> panels) {
        if (panels.size() == 0) {
            System.out.println("No Panels To Update.");
        }
        readPanelKey();
        for (Panel p : panels) {
            if (p.getSection().equals(readPanelKey().getSection())
                    && p.getRow() == readPanelKey().getRow()
                    && p.getColumn() == readPanelKey().getColumn()) {
                p.setMaterial(readPanelMaterial());
                p.setYearInstalled(readInt("Year Installed: "));
                p.setTracking(readBoolean("Sun Tracking Tech Installed [y/n]: "));
                return p;
            }
        } return null;
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

}