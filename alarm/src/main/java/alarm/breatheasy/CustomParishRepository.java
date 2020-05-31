package alarm.breatheasy;

import alarm.breatheasy.Parish;

interface CustomParishRepository {

    Parish findParishContainingEntity(double[] point);

}