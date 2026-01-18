import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class RegistrarParking {
    private int id;

    public RegistrarParking(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class ParkingPool {
    private final Queue<RegistrarParking> pool = new LinkedList<>();

    public synchronized void addParking(RegistrarParking parking) {
        pool.add(parking);
        notifyAll();
    }

    public synchronized RegistrarParking getParking() throws InterruptedException {
        while (pool.isEmpty()) {
            wait();
        }
        return pool.poll();
    }
}

class ParkingAgent extends Thread {
    private final ParkingPool pool;
    private final String agentName;

    public ParkingAgent(String name, ParkingPool pool) {
        this.agentName = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                RegistrarParking parking = pool.getParking();
                System.out.println(agentName + " processed car ID: " + parking.getId());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MainClass {
    public static void main(String[] args) {
        ParkingPool pool = new ParkingPool();

        new ParkingAgent("Agent-1", pool).start();
        new ParkingAgent("Agent-2", pool).start();
        new ParkingAgent("Agent-3", pool).start();

        Scanner scanner = new Scanner(System.in);
        int parkingCount = 0;

        while (parkingCount < 10) {
            System.out.println("Press Enter to register a car...");
            scanner.nextLine();
            
            RegistrarParking parking = new RegistrarParking(parkingCount + 1);
            pool.addParking(parking);
            parkingCount++;
        }

        scanner.close();
        System.out.println("All cars registered.");
    }
}
