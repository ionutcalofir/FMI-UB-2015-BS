import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry();
            TicketReservation stub = (TicketReservation) registry.lookup("TicketReservation");
            
            while (true) {
            	System.out.println("\n");
            	System.out.println("Available options: ");
                System.out.println("0 - see all available seats");
    			System.out.println("1 - request a reservation");
    			System.out.println("2 - see a reservation");
    			System.out.println("/exit - exit");
    			System.out.println("\n");
            	
            	Scanner scanner = new Scanner(System.in);
            	String message = scanner.nextLine();
            	
            	if (message.length() >= 5 && message.substring(0, 5).equals("/exit")) {
            		System.out.println("Bye, bye.");
            		break;
            	} else if (message.equals("0")) {
            		ArrayList<Integer> seats = stub.querySeats();
            		
            		if (seats.isEmpty()) {
            			System.out.println("Niciun loc disponibil.");
            		} else {
            			System.out.println("Locurile disponibile sunt: " + stub.querySeats());
            		}
            	} else if (message.equals("1")) {
            		Integer seatsNumber, number;
            		ArrayList<Integer> seats = new ArrayList<Integer>();
            		while (true) {
            			try {
            				System.out.println("Introduceti numarul de locuri:");
            				message = scanner.nextLine();
            				
            				if (message.length() >= 5 && message.substring(0, 5).equals("/exit")) {
                        		System.out.println("Bye, bye.");
                        		System.exit(0);
            				}
            				
            				seatsNumber = Integer.parseInt(message);
            				if (seatsNumber <= 0) {
            					System.out.println("Va rugam introduceti un numar mai mare sau egal decat 0.\n");
            					continue;
            				}
            				break;
            			} catch (Exception e) {
            				System.out.println("Va rugam introduceti un numar.\n");
            				continue;
            			}
            		}
            		
            		for (int i = 1; i <= seatsNumber; ++i) {
            			while (true) {
                			try {
                				System.out.println("Introduceti numarul locului:");
                				message = scanner.nextLine();
                				
                				if (message.length() >= 5 && message.substring(0, 5).equals("/exit")) {
                            		System.out.println("Bye, bye.");
                            		System.exit(0);
                				}
                				
                				number = Integer.parseInt(message);
                				if (number <= 0) {
                					System.out.println("Va rugam introduceti un numar mai mare sau egal decat 0.\n");
                					continue;
                				}
                				
                				seats.add(number);
                				break;
                			} catch (Exception e) {
                				System.out.println("Va rugam introduceti un numar.\n");
                				continue;
                			}
                		}
            		}
            		
            		int reservationID = stub.requestReservation(seats);
            		if (reservationID != 0) {
            			System.out.println("Rezervarea a fost facuta. Numarul rezervarii dumneavoastre este: " + reservationID);
            		} else {
            			System.out.println("Rezervarea a esuat, va rugam incercati din nou.");
            		}
            	} else if (message.equals("2")) {
            		Integer number;
            		while (true) {
            			try {
            				System.out.println("Introduceti numarul rezervarii dumneavoastre:");
            				message = scanner.nextLine();
            				
            				if (message.length() >= 5 && message.substring(0, 5).equals("/exit")) {
                        		System.out.println("Bye, bye.");
                        		System.exit(0);
            				}
            				
            				number = Integer.parseInt(message);
            				if (number <= 0) {
            					System.out.println("Va rugam introduceti un numar mai mare sau egal decat 0.\n");
            					continue;
            				}
            				
            				ArrayList<Integer> seats = stub.queryReservation(number);
                    		
                    		if (seats.isEmpty()) {
                    			System.out.println("Nicio rezervare inregistrata.");
                    		} else {
                    			System.out.println("Locurile dumneavoastra sunt: " + seats);
                    		}
            				
            				break;
            			} catch (Exception e) {
            				System.out.println("Va rugam introduceti un numar.\n");
            				continue;
            			}
            		}
            	}
            }
            
            ArrayList<Integer> response = stub.querySeats();
            System.out.println("Raspunsul tau: " + response);
            
            ArrayList<Integer> op = new ArrayList<Integer>();
            op.add(4);
            System.out.println(stub.requestReservation(op));
            
            System.out.println("dsada" + stub.querySeats());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}