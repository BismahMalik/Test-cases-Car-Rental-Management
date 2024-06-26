/**
 * This class has the type of vehicle whether it is a car, van, truck, or SUV.
 * It has several methods for checking whether the vehicle could be rented or not.
 */
class VehicleType {

    private int carSeats;
    private int vanSeats = 15;
    private int truckCargoCapacity;
    private int suvSeats;
    private DateTime LastMaintenance;
    private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    // Constructor for Car type
    VehicleType(int seats) {
        this.carSeats = seats;
    }

    // Constructor for Van type
    VehicleType(int seats, DateTime LastMaintenance) {
        this.vanSeats = seats;
        this.LastMaintenance = LastMaintenance;
    }

    // Constructor for Truck type
    VehicleType(int cargoCapacity, boolean isTruck, DateTime LastMaintenance) {
        this.truckCargoCapacity = cargoCapacity;
        this.LastMaintenance = LastMaintenance;
    }

    // Constructor for SUV type
    VehicleType(int seats, boolean isSUV) {
        this.suvSeats = seats;
    }

    public VehicleType() {
		// TODO Auto-generated constructor stub
	}

	/**
     * Method to get seats or capacity of the vehicle by accepting the type of vehicle.
     */
    public int getSeats(String type) {
        if (type.equals("car")) {
            return this.carSeats;
        } else if (type.equals("van")) {
            return this.vanSeats;
        } else if (type.equals("suv")) {
            return this.suvSeats;
        } else {
            return 0;
        }
    }

    /**
     * Method to get cargo capacity of a truck.
     */
    public int getTruckCargoCapacity() {
        return this.truckCargoCapacity;
    }
    
    public int getSUVSeats() {
        return this.suvSeats;
    }

    /**
     * Method to get seats of a vehicle by type.
     */
    public int getCarSeats() {
        return this.carSeats;
    }

    /**
     * Method to set seats of a car.
     */
    public void setCarSeats(int seats) {
        this.carSeats = seats;
    }

    /**
     * Method to get index from the days array.
     */
    public int indexOf(String day) {
        for (int index = 0; index < days.length; index++)
            if (days[index].equals(day))
                return index;
        return -1;
    }

    /**
     * Method to get the last maintenance date.
     */
    public DateTime getLastMaintenance() {
        return this.LastMaintenance;
    }

    /**
     * Method to set the last maintenance date.
     */
    public void setLastMaintenance(DateTime date) {
        this.LastMaintenance = date;
    }

    /**
     * Method to check whether a vehicle can be rented for a specific number of days.
     * @param date  Date of rent
     * @param type  Type of vehicle
     * @return Minimum days it can be rented
     */
    public int canBeRentedForMinimumDays(DateTime date, String type) {
        int dayIndex = this.indexOf(date.getNameOfDay()) + 1;
        if (dayIndex <= 5 && dayIndex >= 1) {
            if (type.equals("car") || type.equals("suv")) {
                return 2;
            } else if (type.equals("truck")) {
                return 3;
            } else {
                return 1; // Van can be rented only for 1 day
            }
        } else {
            if (type.equals("car") || type.equals("suv")) {
                return 3;
            } else if (type.equals("truck")) {
                return 4;
            } else {
                return 1; // Van can be rented only for 1 day
            }
        }
    }

    /**
     * Method to check whether a vehicle is under maintenance or not.
     * @param rentDate       Date of rent
     * @param type           Type of vehicle
     * @param numOfRentDays  Number of rent days
     * @return True if under maintenance, false otherwise
     */
    public boolean IsUnderMaintenance(DateTime rentDate, String type, int numOfRentDays) {
        DateTime nextMaintenance = new DateTime(this.LastMaintenance, 12);
        if (type.equals("van") && DateTime.diffDays(nextMaintenance, new DateTime(rentDate, numOfRentDays)) >= 0 && numOfRentDays <= 12) {
            return false;
        } else if (type.equals("car") || type.equals("suv") || type.equals("truck")) {
            return false;
        } else {
            return true;
        }
    }
}
