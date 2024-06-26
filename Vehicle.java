/**
 * Class to save all the vehicle details
 * This class contains all the vehicle attributes required for this application
 * This is the data access layer and main logic for this application is written here
 */
public class Vehicle {
    protected String Vehicle_id;
    protected int Year;
    protected String Make;
    protected String Model;
    protected int vehicleStatus;
    protected VehicleType vehicleType;
    protected String color;
    protected String ownerName;
    protected RentalRecord records[] = new RentalRecord[10];

    // Constructor to accept the details of a vehicle
    Vehicle(String VehicleId, int Year, String Make, String Model, int status, VehicleType vehicleType, String color, String ownerName) {
        this.Vehicle_id = VehicleId;
        this.Year = Year;
        this.Make = Make;
        this.Model = Model;
        this.vehicleStatus = status;
        this.vehicleType = vehicleType;
        this.color = color;
        this.ownerName = ownerName;
    }
//New constructor is being tested
    public Vehicle(String vehicleID, int year2, String make2, String model2, int i, VehicleType vehicleType2) {
		// TODO Auto-generated constructor stub
	}

	/**
     * Method to get vehicle ID
     */
    public String getVehicleId() {
        return this.Vehicle_id;
    }

    /**
     * Used to rent either available car or available van
     * @param location 
     * @param contactNumber 
     * @param customerName 
     * @param customerId,rentDate,numOfRentDay accepts customeID, date of rent, no of renting days
     * @return True or false as to vehicle is successfully rented or not
     */
    public boolean rent(String customerId, String customerName, String contactNumber, String location, DateTime rentDate, int numOfRentDay) {
        String typeOfVehicle;
        if (this.Vehicle_id.contains("C_"))
            typeOfVehicle = "car";
        else if (this.Vehicle_id.contains("T_"))
            typeOfVehicle = "truck";
        else if (this.Vehicle_id.contains("S_"))
            typeOfVehicle = "suv";
        else
            typeOfVehicle = "van";

        if (this.vehicleStatus != 0 || numOfRentDay < this.vehicleType.canBeRentedForMinimumDays(rentDate, typeOfVehicle) || numOfRentDay >= 14 || numOfRentDay < 2) {
            return false;
        } else if (typeOfVehicle.equals("van")) {
            if (this.vehicleStatus != 0 || this.vehicleType.IsUnderMaintenance(rentDate, typeOfVehicle, numOfRentDay) || numOfRentDay == 0)
                return false;
            else {
                String rentId = this.Vehicle_id + "_" + customerId + "_" + rentDate.getEightDigitDate();
                this.records[this.getLastElementIndex() + 1] = new RentalRecord(rentId, rentDate, new DateTime(rentDate, numOfRentDay));
                this.vehicleStatus = 1;
                return true;
            }
        } else {
            String rentId = this.Vehicle_id + "_" + customerId + "_" + rentDate.getEightDigitDate();
            this.records[this.getLastElementIndex() + 1] = new RentalRecord(rentId, rentDate, new DateTime(rentDate, numOfRentDay));
            this.vehicleStatus = 1;
            return true;
        }
    }

    /**
     * sets the vehicle status to available after maintenance
     * @return Returns true if returned else false
     */
    public boolean performMaintenance() {
        if (this.vehicleStatus == 1 || this.vehicleStatus == 2)
            return false;
        this.vehicleStatus = 2;
        return true;
    }

    /**
     * Method used to get details of vehicle
     */
    public String toString() {
        String repository = this.Vehicle_id + ":" + String.valueOf(this.Year) + ":" + this.Make + ":" + this.Model + ":" + this.color + ":" + this.ownerName + ":";

        switch (this.vehicleStatus) {
            case 0:
                repository += "Available";
                break;
            case 1:
                repository += "Rented";
                break;
            case 2:
                repository += "Maintenance";
                break;
        }
        return repository;
    }

    /**
     * Method used to get details of car or van with their rental history
     */
    public String getDetails() {
        String data = "Vehicle ID:\t" + this.Vehicle_id + "\n Year:\t " + String.valueOf(this.Year) + "\n Make:\t" + this.Make + "\n Model:\t" + this.Model + "\n Color:\t" + this.color + "\n Owner Name:\t" + this.ownerName + "\n Status:\t";
        switch (this.vehicleStatus) {
            case 0:
                data += "Available";
                break;
            case 1:
                data += "Rented";
                break;
            case 2:
                data += "Maintenance";
                break;
        }
        return data;
    }

    /**
     * Method used to get last element index
     */
    public int getLastElementIndex() {
        int index = 0;
        for (index = 0; this.records[index] != null; index++) ;
        return index - 1;
    }
}

