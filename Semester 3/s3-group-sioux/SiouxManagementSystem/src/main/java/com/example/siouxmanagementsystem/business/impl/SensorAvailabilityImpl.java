package com.example.siouxmanagementsystem.business.impl;

import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SensorAvailabilityImpl {
    private static SerialPort comPorts[];
    private static int port=-1;
    public static SerialPort comPort;

    public static Boolean checkAvailibility() {
        Scanner console = new Scanner(System.in);
        System.out.println("List COM ports");
        comPorts = SerialPort.getCommPorts(); // read list of COM ports and display them
        // if no COM ports found exit
        if(comPorts.length<1) {
            System.out.println("no COM ports found");
            return false;
        }
        // print list of COM ports, add to COMportMenu and attach event handler
        for (int i = 0; i < comPorts.length; i++) {
            System.out.println("comPort[" + i + "] = " + comPorts[i].getDescriptivePortName());
        }

        // if 1 COM port found open it
        if(comPorts.length==1) {
            port=0;
//            openCOMport();

            if(comPort == null) {
                System.out.println("comport is NULL");
                openCOMport();
            }
            else if (comPort.openPort() || comPort != null) {
                System.out.println("comport is opened");
            }
            else {
                System.out.println("comport is NOT opened");
                openCOMport();
            }
        }
        else {
            System.out.print("\nEnter COM port (0, 1, 2 etc) to select serial port ");
            if(console.hasNextInt()) {
                port = console.nextInt();
                openCOMport();
            }
        }

        //   read characters from serial and display them
        try {
            String availability = "";
            while (true)
            {
                // read serial port  and display data
                if(comPort.bytesAvailable() > 0)
                {
                    byte[] data = new byte[10];
                    comPort.readBytes(data,1);
                    System.out.println((char)data[0] + "char data");
                    if ((char)data[0]== 'o' || (char)data[0]== 'a') {
                        availability = availability + String.valueOf((char)data[0]);
                        System.out.println(availability);
                    }
                    //System.out.print((char)data[0]);

                     if(availability.equals("o")) {
                         comPorts[port].closePort();
                         System.out.println("the parking spot is occupied");
                         return false;
                     }

                     else if (availability.equals("a")) {
                         comPorts[port].closePort();
                         System.out.println("the parking spot is available");

                         return true;
                     }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPorts[port].closePort();
        return false;
    }


    // attempt to open COM port
    static void openCOMport() {
        comPort = SerialPort.getCommPorts()[port];
        System.out.println("attempting to open " +  comPorts[port].getDescriptivePortName() + "\n");
        if(!comPort.openPort()) {
            System.out.println("failed to open COM port " + comPorts[port].getDescriptivePortName() + "\n");
            port=-1;
            return;
        }
        System.out.println("Opened COM port " + comPorts[port].getDescriptivePortName() + " OK\n");
        comPort.setBaudRate(9600);
    }
}
