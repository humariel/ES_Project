package application;

import application.Parish;

interface CustomParishRepository {

    Parish findParishContainingEntity(double[] point);

}