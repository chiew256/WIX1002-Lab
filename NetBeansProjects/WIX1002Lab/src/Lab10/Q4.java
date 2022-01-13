/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lab10;

/**
 *
 * @author chiew256
 */
import java.io.*;
import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        try{
            Scanner inputStream = new Scanner(new FileInputStream("appointment.txt"));
            inputStream.close();
        } catch (FileNotFoundException e){
            System.out.println("Creating file");
            try{
                PrintWriter outputStream = new PrintWriter(new FileOutputStream("appointment.txt"));
                outputStream.close();
            }catch(IOException f){
                System.out.println("Problem with creating file");
            }
        }catch (IOException e) {
            System.out.println("Problem with file output");
        }

        Appointment app1 = new Appointment(1,1, 2000, 1000, 1200);

        if(Appointment.search(app1.getDateStartTime(), app1.getDateEndTime())){
            app1.saveAppointment();
        }
        else{
            System.out.println("Please choose another date, the date is not available");
        }

        //same as above
        Appointment app2 = new Appointment(1,1,2000,900,1100);
        if(Appointment.search(app2.getDateStartTime() , app2.getDateEndTime())){
            app2.saveAppointment();
        }else{
            System.out.println("Please choose another date, the date is not available");
        }

        //apointment 3
        Appointment app3 = new Appointment(1,1,2000,1000,1100);
        if(Appointment.search(app3.getDateStartTime() , app3.getDateEndTime())){
            app3.saveAppointment();
        }else{
            System.out.println("Please choose another date, the date is not available");
        }

        //appointment 4
        Appointment app4 = new Appointment(1,1,2000,1300,1400);
        if(Appointment.search(app4.getDateStartTime() , app4.getDateEndTime())){
            app4.saveAppointment();
        }else{
            System.out.println("Please choose another date, the date is not available");
        }

        //appointment 5
        Appointment app5 = new Appointment(4,1,2000,1000,1200);
        if(Appointment.search(app5.getDateStartTime() , app5.getDateEndTime())){
            app5.saveAppointment();
        }else{
            System.out.println("Please choose another date, the date is not available");
        }

        //appointment 6
        Appointment app6 = new Appointment(1,1,2000,1100,1300);
        if(Appointment.search(app5.getDateStartTime() , app5.getDateEndTime())){
            app5.saveAppointment();
        }else{
            System.out.println("Please choose another date, the date is not available");
        }
    }
}
interface Searchable{
    static boolean search(String dateStartTime, String dateEndTime){
        return true;
    }
}

class Appointment implements Searchable{
    private String dateStartTime,  dateEndTime;

    public Appointment(int day, int month, int year, int startTime, int endTime){
        this.dateStartTime = day + "/" + month + "/" + year + "/" + startTime;
        this.dateEndTime = day + "/" + month + "/" + year + "/" + endTime;
    }

    public void saveAppointment(){
        try{
            PrintWriter outputStream = new PrintWriter(new FileOutputStream("appointment.txt", true));
            outputStream.println(this.dateStartTime);
            outputStream.println(this.dateEndTime);
            outputStream.println();
            outputStream.close();

            System.out.println("Appointment is saved");
        } catch(IOException e){
            System.out.println("Problem with file output");
        }
    }

    public String getDateStartTime() {
        return dateStartTime;
    }

    public String getDateEndTime() {
        return dateEndTime;
    }

    public static boolean search(String dateStartTime, String dateEndTime){
        try{
            Scanner inputStream = new Scanner(new FileInputStream("appointment.txt"));
            //  format : day/month/year/time
            String [] dateStartTimeArray = dateStartTime.split("/");
            String [] dateEndTimeArray = dateEndTime.split("/");

            int day = Integer.parseInt(dateStartTimeArray[0]);
            int month = Integer.parseInt(dateStartTimeArray[1]);
            int year = Integer.parseInt(dateStartTimeArray[2]);
            int startTime = Integer.parseInt(dateStartTimeArray[3]);
            int endTime = Integer.parseInt(dateEndTimeArray[3]);

            while(inputStream.hasNextLine()){
                String [] appointmentDateStartTime = inputStream.nextLine().split("/");
                String [] appointmentDateEndTime = inputStream.nextLine().split("/");

                inputStream.nextLine();

                int appointmentDay = Integer.parseInt(appointmentDateStartTime[0]);
                int appointmentMonth = Integer.parseInt(appointmentDateStartTime[1]);
                int appointmentYear = Integer.parseInt(appointmentDateStartTime[2]);
                int appointmentStartTime = Integer.parseInt(appointmentDateStartTime[3]);
                int appointmentEndTime = Integer.parseInt(appointmentDateEndTime[3]);

                if(appointmentDay == day && appointmentMonth == month && appointmentYear == year){
                    // if the start time is start before the start time of the saved appointment but end after it
                    //example recorded appointment : 10 - 12 , start time = 10
                    //new appointment : 9 - 13 , start time = 9, end time = 13
                    if(appointmentStartTime <= endTime && appointmentStartTime >= startTime){
                        return false;
                    }

                    //if the start time is start in between the time of the saved appointment ,then it is false
                    //example recorded appointment : 10 - 12 , start time = 10, end time = 12
                    //new appointment : 11 - 13 , start time = 11
                    else if(appointmentEndTime >= startTime && appointmentStartTime >= startTime){
                        return false;
                    }
                }

            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found");
        }
        return true;
    }
}
