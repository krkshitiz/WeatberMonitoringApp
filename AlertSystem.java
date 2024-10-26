public class AlertSystem {
    private double threshold;

    public AlertSystem(double threshold) {
        this.threshold = threshold;
    }

    public boolean checkAlert(double temperature) {
        return temperature > threshold;
    }
}
