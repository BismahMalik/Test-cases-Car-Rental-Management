/**
 * Class to save all the truck details
 * This class contains all the calculations and details of the truck
 * It extends Vehicle so it has all the features of a vehicle
 */
public class Truck extends Vehicle {

    private double rentRate = 120;
    private double lateFee;
    private int cargoCapacity;

    Truck(String VehicleId, int Year, String Make, String Model, int status, VehicleType vehicleType, String color, String ownerName) {
        super(VehicleId, Year, Make, Model, status, vehicleType, color, ownerName);
        cargoCapacity = vehicleType.getTruckCargoCapacity();
    }

    /**
     * Used to calculate the late fee
     * @param endDate,startDate accepts start date and end date
     * @return lateFee the late fee
     */
    public double getLateFee(DateTime endDate, DateTime startDate) {
        if (DateTime.diffDays(endDate, startDate) > 0)
            this.lateFee = 1.25 * this.rentRate * DateTime.diffDays(endDate, startDate);
        else
            this.lateFee = 0.0;
        return this.lateFee;
    }

    /**
     * Used to return the truck
     * @param returnDate accepts the date as to when it has to be returned
     * @return Returns true if returned else false with appropriate messages
     */
    public boolean returnVehicle(DateTime returnDate) {
        String vehicleType = "truck";
        if (this.vehicleStatus != 0) {
            DateTime estdate = this.records[this.getLastElementIndex()].getEstimatedReturnDate();
            DateTime rentDate = this.records[this.getLastElementIndex()].getRentDate();
            if (DateTime.diffDays(returnDate, estdate) < 0 && DateTime.diffDays(returnDate, rentDate) < this.vehicleType.canBeRentedForMinimumDays(rentDate, vehicleType)) {
                return false;
            } else {
                this.records[this.getLastElementIndex()].setData(returnDate, this.rentRate * DateTime.diffDays(returnDate, rentDate), this.getLateFee(returnDate, estdate));
                this.vehicleStatus = 0;
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * sets the vehicle status to available after maintenance
     * @return Returns true if returned else false
     */
    public boolean completeMaintenance() {
        if (super.vehicleStatus != 2)
            return false;
        this.vehicleStatus = 0;
        return true;
    }

    /**
     * Method used to get details of truck with their rental history
     * Prints the rental record of truck
     */
    public String getDetails() {
        String details = super.getDetails();
        if (this.records[0] == null) {
            details += "\nRENTAL RECORD:\tempty";
        } else {
            details += "\nRENTAL RECORD\n";
            int count = 0;
            for (int index = 0; this.records[index] != null; index++)
                count++;
            for (int index = count - 1; index >= 0; index--) {
                details += this.records[index].getDetails() + "                     \n";
                for (int innerIndex = 0; innerIndex < 10; innerIndex++)
                    details += "-";
                details += "                     \n";
            }
        }
        return details;
    }
}
