package Lesson_06.Ex_04;

public class Decision implements Runnable {
    Thread thrd;
    static int current = 0;

    Port port = new Port();
    static int docksIs = 1;

    Decision(String name) {
        thrd = new Thread(this, name);
    }

    public static Decision createAndStart(String name) {
        Decision myThrd = new Decision(name);
        myThrd.thrd.start(); // start the thread
        return myThrd;
    }

    @SuppressWarnings("static-access")
    synchronized public void run() {

        System.out.println(thrd.getName() + " moving to port.");
        try {
            for (int count = 0; count < 10; count++) {
                Ship ship = new Ship();

                while ((this.docksIs >= this.port.docks || current >= this.port.capacityPort)) {
                    System.out.println(thrd.getName() + " entered the port and waiting in line  ");
                    if (ship.bool) {
                        System.out.print("Full " + "\n");
                    } else {
                        System.out.print("Empty " + "\n");
                    }
                    Thread.sleep(40 + (int) (Math.random() * ((40 - 20) + 1)));
                }

                Thread.sleep(20 + (int) (Math.random() * ((40 - 20) + 1)));

                if (this.docksIs < (this.port.docks) && current < Port.capacityPort && ship.bool) {
                    System.out.println(
                            thrd.getName() + " entered the port and began to unload at the pier " + "№" + (this.docksIs));

                    int doks = this.docksIs;
                    this.docksIs++;
                    current += ship.capacity;

                    Thread.sleep(20 + (int) (Math.random() * ((40 - 20) + 1)));
                    System.out.println(thrd.getName() + " finished unloading" + " at the pier №" + (doks) + "\nIn port "
                            + current + " containers ");
                    Thread.sleep(20 + (int) (Math.random() * ((40 - 20) + 1)));
                    System.out.println(thrd.getName() + " Out of port");
                    this.docksIs--;
                }

                while ((this.docksIs >= this.port.docks || current >= this.port.capacityPort)) {
                    System.out.println(thrd.getName() + " entered the port and waiting in line ");

                    if (ship.bool) {
                        System.out.print("The hold is full" + "\n");
                    } else {
                        System.out.print("the hold is empty" + "\n");
                    }
                    Thread.sleep(40 + (int) (Math.random() * ((40 - 20) + 1)));
                }
                if (this.docksIs < port.docks && current > 10 && ship.capacity == 0) {
                    System.out.println(
                            thrd.getName() + " entered the port and began loading at the pier" + " №" + (this.docksIs));

                    int dooks = this.docksIs;
                    this.docksIs++;
                    current -= 10;
                    Thread.sleep(20 + (int) (Math.random() * ((40 - 20) + 1)));

                    System.out.println(thrd.getName() + " finished loading " + " №" + (dooks) + "\nIn port " + current
                            + " containers ");

                    Thread.sleep(20 + (int) (Math.random() * ((40 - 20) + 1)));

                    System.out.println(thrd.getName() + " Out of port ");
                    this.docksIs--;
                }
            }
        } catch (InterruptedException exc) {
            System.out.println(thrd.getName() + " startup error.");
        }
    }
}