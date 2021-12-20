package learn.solar.data;

import learn.solar.models.Material;
import learn.solar.models.Panel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PanelFileRepository implements PanelRepository {

    private final String filePath;


    public PanelFileRepository(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public List<Panel> findBySection(String section) throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        for (Panel panel : findAll()) {
            if (Objects.equals(panel.getSection(), section)) {
                result.add(panel);
            }
        }
        return result;
    }

    @Override
    public Panel findByKey(String section, int row, int column) throws DataAccessException {
        for (Panel panel : findAll()) {
            if (panel.getSection().equals(section)
                    && panel.getRow() == row
                    && panel.getColumn() == column) {
                return panel;
            }
        }
        return null;
    }

    @Override
    public Panel add(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();

        for (int i = 0; i < all.size(); i++) {
            if (!all.get(i).getSection().equals(panel.getSection())
                    && all.get(i).getRow() != panel.getRow()
                    && all.get(i).getColumn() != panel.getColumn()) {

                all.add(panel);
                writeAll(all);

                return panel;
            }
        }
        return panel;
    }

    @Override
    public boolean update(Panel panel) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSection().equals(panel.getSection())
                    && all.get(i).getRow() == panel.getRow()
                    && all.get(i).getColumn() == panel.getColumn()) {

                all.set(i, panel);
                writeAll(all);

                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String section, int row, int column) throws DataAccessException {
        List<Panel> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getSection().equals(section)
                    && all.get(i).getRow() == row
                    && all.get(i).getColumn() == column) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    public List<Panel> findAll() throws DataAccessException {
        ArrayList<Panel> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); //header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Panel panel = deserialize(line);
                if (panel != null) {
                    result.add(panel);
                }
            }
        } catch (FileNotFoundException ex) {
            //ignore
        } catch (IOException e) {
            throw new DataAccessException("Could not open the file path: " + filePath, e);

        }
        return result;
    }


    private void writeAll(List<Panel> panels) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("Section,row,column,material,year_installed,sun-tracking");
            for (Panel p : panels) {
                writer.println(serialize(p));
            }
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Panel panel) throws DataAccessException {
        //Sec,row,col,mat,year,suntrack
        return String.format("%s,%s,%s,%s,%s,%s",
                panel.getSection(),
                panel.getRow(),
                panel.getColumn(),
                panel.getMaterial(),
                panel.getYearInstalled(),
                panel.isTracking());
    }

    private Panel deserialize(String line) throws DataAccessException {
        String[] fields = line.split(",", -1);
        if (fields.length == 6) {
            Panel panel = new Panel();
            panel.setSection(fields[0]);
            panel.setRow(Integer.parseInt(fields[1]));
            panel.setColumn(Integer.parseInt(fields[2]));
            panel.setMaterial(Material.valueOf(fields[3]));
            panel.setYearInstalled(Integer.parseInt(fields[4]));
            panel.setTracking(Boolean.parseBoolean(fields[5]));
            return panel;
        }

        return null;
    }


}
