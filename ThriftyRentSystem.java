import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class ThriftyRentSystem {
    private Car cars[] = new Car[50];
    private Van vans[] = new Van[50];
    public static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public void run() {
        while (true) {
            System.out.println("\n**** ThriftyRent SYSTEM MENU ****\n");
            System.out.println("Add vehicle:            1");
            System.out.println("Rent vehicle:           2");
            System.out.println("Return vehicle:         3");
            System.out.println("Vehicle Maintenance:    4");
            System.out.println("Complete Maintenance:   5");
            System.out.println("Display All Vehicles:   6");
            System.out.println("Exit Program:           7");
            System.out.println("Enter your choice:");
            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    this.add(sc);
                    break;
                case 2:
                    this.rent(sc);
                    break;
                case 3:
                    this.returnCar(sc);
                    break;
                case 4:
                    this.vehicleMaintenance(sc);
                    break;
                case 5:
                    this.completeMaintenance(sc);
                    break;
                case 6:
                    this.getDetails();
                    break;
                case 7:
                    sc.close();
                    return;
            }
        }
    }

    public void add(Scanner scan) {
        int i = 0;
        String vehicleID = "";
        int seats = 0;
        String maintenanceDate = null;
        System.out.print("Vehicle Type(Van or Car): ");
        String vehicleType = scan.nextLine();
        while (!(vehicleType.equalsIgnoreCase("car") || vehicleType.equalsIgnoreCase("van"))) {
            System.out.print("Please enter either van or car: ");
            vehicleType = scan.nextLine();
        }
        System.out.print("Year: ");
        int year = Integer.parseInt(scan.nextLine());
        while (year < 0 || year > 2019) {
            System.out.println("Please enter a valid year");
            year = Integer.parseInt(scan.nextLine());
        }

        System.out.print("Make: ");
        String make = scan.nextLine();
        System.out.print("Model: ");
        String model = scan.nextLine();

        if (vehicleType.equals("car")) {
            System.out.print("Vehicle ID: C_");
            vehicleID = scan.nextLine();
            vehicleID = "C_" + vehicleID;
            if (this.cars[0] != null && vehicleID.contains("C_")) {
                for (i = 0; this.cars[i] != null; i++) {
                    if ((this.cars[i].getVehicleId()).equals(vehicleID)) {
                        System.out.println("ID Already used, Please add a new vehicle");
                        return;
                    }
                }
            }
            System.out.print("Number of seats: ");
            seats = Integer.parseInt(scan.nextLine());
            while ((seats != 4 && seats != 7)) {
                System.out.println("Please enter seats as either 4 or 7");
                seats = Integer.parseInt(scan.nextLine());
            }
            if (i < 50) {
                Vehicle newVehicle = new Car(vehicleID, year, make, model, 0, new VehicleType(seats));
                this.cars[i] = (Car) newVehicle;
                System.out.println(newVehicle.toString());
            }
        }
        if (vehicleType.equalsIgnoreCase("van")) {
            System.out.print("Vehicle ID: V_");
            vehicleID = scan.nextLine();
            vehicleID = "V_" + vehicleID;
            if (this.vans[0] != null && vehicleID.contains("V_")) {
                for (i = 0; this.vans[i] != null; i++) {
                    if ((this.vans[i].getVehicleId()).equals(vehicleID)) {
                        System.out.println("ID Already used, Please add a new vehicle");
                        return;
                    }
                }
            }
            seats = 15;
            System.out.print("Last Maintenance (dd/mm/yyyy): ");
            maintenanceDate = scan.next();
            format.setLenient(false);
            while (maintenanceDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
                System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
                maintenanceDate = scan.nextLine();
            }
            String dateSplit[] = maintenanceDate.split("/");
            DateTime Lastmain = new DateTime(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]),
                    Integer.parseInt(dateSplit[2]));
            if (i < 50) {
                Vehicle newVehicle = new Van(vehicleID, year, make, model, 0, new VehicleType(seats, Lastmain));
                this.vans[i] = (Van) newVehicle;
                System.out.println(newVehicle.toString());
            }
        }
    }

    private void rent(Scanner sc) {
        System.out.print("Vehicle id: ");
        String id = sc.nextLine();
        String type = "";

        if (id.contains("C_") && this.cars[0] == null) {
            System.out.println("There are no cars currently at the moment.");
            return;
        }
        if (id.contains("V_") && this.vans[0] == null) {
            System.out.println("There are no vans currently at the moment.");
            return;
        }
        if (this.cars[0] != null && id.contains("C_")) {
            boolean flag = false;
            for (int i = 0; this.cars[i] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    if (this.cars[i].vehicleStatus != 0) {
                        System.out.println(
                                "The car with ID : " + id
                                        + " is already either rented or under maintenance, please choose another car.");
                        return;
                    }

                    type = "car";
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("ID is incorrect, please try again!");
                return;
            }
        }
        if (this.vans[0] != null && id.contains("V_")) {
            boolean flag = false;
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    if (this.vans[0].vehicleStatus != 0) {
                        System.out.println(
                                "The van with ID : " + id
                                        + " is already either rented or under maintenance. \nPlease choose another van.");
                        return;
                    }
                    type = "van";
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Id is incorrect, please try again!");
                return;
            }
        }
        if (!(id.contains("V_") || id.contains("C_"))) {
            System.out.println("Please Enter a Valid ID either starting from 'V_' or 'C_'.");
            return;
        }

        System.out.print("Customer ID: ");
        String customerId = sc.next();
        System.out.print("Customer Name: ");
        String customerName = sc.next();
        System.out.print("Contact Number: ");
        String contactNumber = sc.next();
        System.out.print("Location: ");
        String location = sc.next();

        System.out.print("Rent date( dd/mm/yyyy): ");
        String date = sc.next();
        format.setLenient(false);
        while (date.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
            System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
            date = sc.nextLine();
        }
        String dates[] = date.split("/");
        DateTime rentDate = new DateTime(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                Integer.parseInt(dates[2]));
        System.out.print("How many days?: ");
        int days = sc.nextInt();
        if (type.equals("car")) {
            for (int i = 0; this.cars[0] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    if (this.cars[i].rent(customerId, customerName, contactNumber, location, rentDate, days))
                        break;
                    else {
                        System.out.println("Vehicle " + id + " could not be rented.");
                        return;
                    }
                }
            }
            System.out.println("Vehicle " + id + " is now rented by customer " + customerId);
        }

        if (type.equals("van")) {
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    if (this.vans[i].rent(customerId, customerName, contactNumber, location, rentDate, days))
                        break;
                    else {
                        System.out.println("Vehicle " + id + " could not be rented.");
                        return;
                    }
                }
            }
            System.out.println("Vehicle " + id + " is now rented by customer " + customerId);
        }
    }

    private void returnCar(Scanner sc) {
        System.out.print("Vehicle id: ");
        String id = sc.next();
        if (this.cars[0] != null && id.contains("C_")) {
            boolean flag = false;
            for (int i = 0; this.cars[i] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    if (this.cars[i].vehicleStatus != 1) {
                        System.out.println("The car is not rented, please enter a valid car id.");
                        return;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid car id.");
                return;
            }
        }
        if (this.vans[0] != null && id.contains("V_")) {
            boolean flag = false;
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    if (this.vans[i].vehicleStatus != 1) {
                        System.out.println("The van is not rented, please enter a valid van id.");
                        return;
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println("Invalid van id.");
                return;
            }
        }
        if (id.contains("C_")) {
            for (int i = 0; this.cars[i] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    System.out.print("Return date (dd/mm/yyyy): ");
                    String returnDate = sc.next();
                    format.setLenient(false);
                    while (returnDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
                        System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
                        returnDate = sc.next();
                    }
                    String dates[] = returnDate.split("/");
                    DateTime date = new DateTime(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                            Integer.parseInt(dates[2]));
                    this.cars[i].returnVehicle(date);
                    System.out.println("Vehicle " + id + " is now returned.");
                    break;
                }
            }
        }
        if (id.contains("V_")) {
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    System.out.print("Return date (dd/mm/yyyy): ");
                    String returnDate = sc.next();
                    format.setLenient(false);
                    while (returnDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
                        System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
                        returnDate = sc.next();
                    }
                    String dates[] = returnDate.split("/");
                    DateTime date = new DateTime(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                            Integer.parseInt(dates[2]));
                    this.vans[i].returnVehicle(date);
                    System.out.println("Vehicle " + id + " is now returned.");
                    break;
                }
            }
        }
    }

    private void vehicleMaintenance(Scanner sc) {
        System.out.print("Vehicle id: ");
        String id = sc.next();
        if (this.cars[0] != null && id.contains("C_")) {
            for (int i = 0; this.cars[i] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    this.cars[i].performMaintenance();
                    System.out.println("Vehicle " + id + " is now under maintenance.");
                    break;
                }
            }
        }
        if (this.vans[0] != null && id.contains("V_")) {
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    this.vans[i].performMaintenance();
                    System.out.println("Vehicle " + id + " is now under maintenance.");
                    break;
                }
            }
        }
    }

    private void completeMaintenance(Scanner sc) {
        System.out.print("Vehicle id: ");
        String id = sc.next();
        if (this.cars[0] != null && id.contains("C_")) {
            for (int i = 0; this.cars[i] != null; i++) {
                if ((this.cars[i].getVehicleId()).equals(id)) {
                    this.cars[i].completeMaintenance();
                    System.out.println("Vehicle " + id + " is now available.");
                    break;
                }
            }
        }
        if (this.vans[0] != null && id.contains("V_")) {
            for (int i = 0; this.vans[i] != null; i++) {
                if ((this.vans[i].getVehicleId()).equals(id)) {
                    System.out.print("Maintenance completion date (dd/mm/yyyy): ");
                    String completeMaintenanceDate = sc.next();
                    format.setLenient(false);
                    while (completeMaintenanceDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
                        System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
                        completeMaintenanceDate = sc.next();
                    }
                    String dates[] = completeMaintenanceDate.split("/");
                    DateTime date = new DateTime(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]),
                            Integer.parseInt(dates[2]));
                    this.vans[i].completeMaintenance(date);
                    System.out.println("Vehicle " + id + " is now available.");
                    break;
                }
            }
        }
    }

    private void getDetails() {
        if (this.cars[0] == null && this.vans[0] == null) {
            System.out.println("No vehicles in the system.");
            return;
        }
        if (this.cars[0] != null) {
            System.out.println("Cars :");
            for (int i = 0; this.cars[i] != null; i++) {
                System.out.println(this.cars[i].toString());
            }
        }
        if (this.vans[0] != null) {
            System.out.println("Vans :");
            for (int i = 0; this.vans[i] != null; i++) {
                System.out.println(this.vans[i].toString());
            }
        }
    }

    public static void main(String[] args) {
        ThriftyRentSystem system = new ThriftyRentSystem();
        system.run();
    }
}