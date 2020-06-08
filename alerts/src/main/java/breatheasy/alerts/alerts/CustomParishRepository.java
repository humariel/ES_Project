package breatheasy.alerts.alerts;

interface CustomParishRepository {

    Parish findParishContainingEntity(double[] point);

}