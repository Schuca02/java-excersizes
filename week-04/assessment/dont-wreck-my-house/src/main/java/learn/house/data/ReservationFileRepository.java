package learn.house.data;

import learn.house.models.Host;
import learn.house.models.Reservation;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository{

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }


    @Override
    public List<Reservation> findAllByHost(Host host) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(host)))) {

            reader.readLine();

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            //no throw on read
        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException{
        List<Reservation> all = findAllByHost(reservation.getHost());

        int nextId = all.stream()
                .mapToInt(Reservation::getId)
                .max()
                .orElse(0) + 1;

        reservation.setId(nextId);
        writeAll(all, reservation.getHost());

        return reservation;
    }



    @Override
    public boolean update(Reservation reservation) throws DataException {
        List<Reservation> all = findAllByHost(reservation.getHost());
        for(int i = 0; i < all.size(); i++){
            if (all.get(i).getId() == reservation.getId()){
                all.set(i, reservation);
                writeAll(all, reservation.getHost());
                return true;

            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        //filter out anything in past
        return false;
    }

    private void writeAll(List<Reservation> reservations, Host host) throws DataException {
        try(PrintWriter writer = new PrintWriter(getFilePath(host))){

            writer.println(HEADER);

            for (Reservation reservation : reservations) {
                writer.println(serialize(reservation));
            }
        } catch (FileNotFoundException ex){
            throw new DataException(ex);
        }
    }

    private String getFilePath(Host host){return Paths.get(directory, host + ".csv").toString();}

    private Reservation deserialize(String[] fields){
        Reservation result = new Reservation();
        result.setId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));
        result.setGuestId(Integer.parseInt(fields[3]));
        result.setTotal(new BigDecimal(fields[4]));
        return result;
    }

    private String serialize(Reservation reservation){
        return String.format("%s,%s,%s,%s,%s",
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuestId(),
                reservation.getTotal());
    }
}
