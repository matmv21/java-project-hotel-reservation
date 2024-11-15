package application;

import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// SOLUÇÃO MUITO RUIM - Algumas informaçõed deveriam ter sido declaradas na classe Reservation.

public class Program {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Room number: ");
        int number = sc.nextInt();
        System.out.print("Check-in date (dd/MM/yyyy): ");
        Date checkIn = sdf.parse(sc.next());
        System.out.print("Check-Out date (dd/MM/yyyy): ");
        Date checkOut = sdf.parse(sc.next());
        
        if (!checkOut.after(checkIn)){
            System.out.print("Error in reservation: Check-Out date must be after Check-In date.");
        } else {
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation has been created.");
            System.out.println("RESERVATION: " + reservation);

            System.out.println();
            System.out.println("Enter date to update the reservation: ");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Check-Out date (dd/MM/yyyy): ");
            checkOut = sdf.parse(sc.next());

            Date now = new Date();
            if (checkIn.before(now) || checkOut.after(now)){
                System.out.println("Error in reservation: Updates in reservation dates must be future dates.");
            } else if (!checkOut.after(checkIn)) {
                System.out.print("Error in reservation: Check-Out date must be after Check-In date.");
            } else {
                reservation.updateDates(checkIn, checkOut);
                System.out.print("RESERVATION: " + reservation);
            }
        }

        sc.close();
    }
}
