package application.mongo;

import application.data.Parish;

interface CustomParishRepository {

    Parish findParishContainingEntity(double[] point);

}