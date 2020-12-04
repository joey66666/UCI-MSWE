public class TrafficController {

    private volatile boolean isCar = false;

    public void enterLeft() {
        addCar();
    }

    public void enterRight() {
        addCar();
    }

    public void leaveLeft() {
        passCar();
    }

    public void leaveRight() {
        passCar();
    }

    public synchronized void addCar() {
        while (true) {
            if (!isCar) {
                break;
            }
        }
        isCar = true;
    }

    public void passCar() {
        isCar = false;
    }
}