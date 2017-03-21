package com.Richard;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class TicketManager {

    public static void main(String[] args) {
        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("1. Enter Ticket\n2. Delete Ticket by ID\n3. Display All Tickets\n4. Quit");
            int task = scan.nextInt();

            if(task == 1){
                addTickets(ticketQueue);
            }else if(task == 2){
                deleteTicket(ticketQueue);
            }else if(task == 4){
                System.out.println("Quitting program");
                break;
            }else {
                printAllTickets(ticketQueue);
            }
        }
        scan.close();
    }

    protected static void deleteTicket(LinkedList<Ticket> ticketQueue){
        Scanner IDscan = new Scanner(System.in);

        printAllTickets(ticketQueue);
        while (true){
            boolean found = false;
            if(ticketQueue.size() == 0){
                System.out.println("No tickets found.");
                return;
            }

            //System.out.println("Please enter ID of ticket you wish to delete");
            int id = Input.getPositiveIntInput("Please enter ID of ticket you wish to delete");
            for (Ticket t : ticketQueue) {
                int ticketId = t.getTicketID();
                if (id == ticketId) {
                    ticketQueue.remove(t);
                    found = true;
                    String more = Input.getStringInput("delete more tickets?");

                    if(more.equalsIgnoreCase("N")){
                        return;
                    }

                }
            }
            if (found == false) {
                System.out.println("Ticket ID not found. Please try again.");
            }
            //Updated ticket list
            printAllTickets(ticketQueue);
        }
    }

    protected static void ticketStringSearch(LinkedList<Ticket> ticketList, String term){

    }


    protected  static  void addTickets(LinkedList<Ticket> ticketQueue){
        Scanner sc = new Scanner(System.in);

        String description;
        String reporter;
        String more;
        //Save time this ticket was created.
        Date dateReported = new Date();
        int priority;


        while(true){
            //System.out.println("Enter problem");
            description = Input.getStringInput("Enter problem");
            //System.out.println("Who reported this issue?");
            reporter = Input.getStringInput("Who reported this issue?");
            //System.out.println("Enter priority of " + description);
            priority = Input.getPositiveIntInput("Enter priority of " + description);
            Ticket t = new Ticket(description, priority, reporter, dateReported);
            //ticketQueue.add(t);
            addTicketInPriorityOrder(ticketQueue, t);

            printAllTickets(ticketQueue);

            //System.out.println("More tickets?");
            more = Input.getStringInput("More tickets?");

            if(more.equalsIgnoreCase("N")){
                return;
            }
        }
        //sc.close();
    }

    protected static void addTicketInPriorityOrder(LinkedList<Ticket>tickets, Ticket newTicket){
        if(tickets.size() == 0){
            tickets.add(newTicket);
            return;
        }
        //Tickets with highest priority go to the top of list.
        //Tickets with the lowest go to the and of list.
        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size(); x++){
            if(newTicketPriority >=tickets.get(x).getPriority()){
                tickets.add(x, newTicket);
                return;
            }
        }
        //If ticket is not added in the for loop it is a lower priority ticket and gets added to the end.
        tickets.addLast(newTicket);
    }

    protected static void printAllTickets(LinkedList<Ticket> tickets){
        System.out.println("------All tickets------");
        for(Ticket t : tickets){
            System.out.println(t);
        }
        System.out.println("------End of ticket list------");
    }
}
