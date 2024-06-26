import java.util.ArrayList;

class Warning {
    private static ArrayList<String> warnedVehicles = new ArrayList<>();

    public static void addWarnedVehicle(String vehicleId) {
        warnedVehicles.add(vehicleId);
    }

    public static ArrayList<String> getWarnedVehicles() {
        return warnedVehicles;
    }
}
