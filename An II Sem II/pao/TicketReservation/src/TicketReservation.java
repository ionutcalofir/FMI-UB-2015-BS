import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface TicketReservation extends Remote {
	int requestReservation(ArrayList<Integer> seats) throws RemoteException;
	ArrayList<Integer> querySeats() throws RemoteException;
	ArrayList<Integer> queryReservation(int reservationID) throws RemoteException;
}