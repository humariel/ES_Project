package application;

import java.util.List;

import application.Value;

interface CustomValueRepository {

    List<Value> getValuesFromParish(String parish, long minCount);

}