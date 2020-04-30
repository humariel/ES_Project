package application;

interface CustomParishRepository {

    Parish findParishContainingEntity(double[] point);

}