import java.util.*;

public class Problem8_ParkingLot {

    static final int SIZE = 10;
    static final String DELETED = "DELETED";
    String[] spots = new String[SIZE];
    long[] entryTimes = new long[SIZE];

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % SIZE;
    }

    public void parkVehicle(String plate) {
        int preferred = hash(plate);
        int probes = 0;
        for (int i = 0; i < SIZE; i++) {
            int spot = (preferred + i) % SIZE;
            if (spots[spot] == null || spots[spot].equals(DELETED)) {
                spots[spot] = plate;
                entryTimes[spot] = System.currentTimeMillis();
                System.out.println("parkVehicle(\"" + plate + "\") -> Spot #"
                        + spot + " (" + probes + " probes)");
                return;
            }
            probes++;
        }
        System.out.println("Parking lot is FULL!");
    }

    public void exitVehicle(String plate) {
        int preferred = hash(plate);
        for (int i = 0; i < SIZE; i++) {
            int spot = (preferred + i) % SIZE;
            if (plate.equals(spots[spot])) {
                long duration = System.currentTimeMillis() - entryTimes[spot];
                long minutes = duration / 60000;
                double fee = (minutes / 60.0) * 5.0;
                spots[spot] = DELETED;
                System.out.printf("exitVehicle(\"%s\") -> Spot #%d freed, Duration: %d min, Fee: $%.2f%n",
                        plate, spot, minutes, fee);
                return;
            }
        }
        System.out.println("Vehicle not found: " + plate);
    }

    public static void main(String[] args) {
        Problem8_ParkingLot lot = new Problem8_ParkingLot();
        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");
        lot.exitVehicle("ABC-1234");
        lot.parkVehicle("NEW-0001");
    }
}