package meli.mutantdetector.api.model;

import ace.gson.Json;
import com.google.gson.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stadistic implements DBObject, JsonParseable {
    
    private static final String QUERY = "";

    private static final String COUNT_MUTANT_DNA = "COUNT_MUTANT_DNA";
    private static final String COUNT_HUMAN_DNA = "COUNT_HUMAN_DNA";

    private static final String COUNT_MUTANT_DNA_KEY = "count_mutant_dna";
    private static final String COUNT_HUMAN_DNA_KEY = "count_human_dna";
    private static final String RATIO_KEY = "ratio";

    private int _countMutantDna;
    private int _countHumanDna;

    public Stadistic() {
        _countHumanDna = 0;
        _countMutantDna = 0;
    }

    private double calculateRatio() {
        try {
            return (_countHumanDna / _countMutantDna);
        } catch (final ArithmeticException ex) {
            return 0d;
        }
    }

    @Override
    public void setByResultSet(final ResultSet rs) throws SQLException {
        if (rs != null) {
            while (rs.next()) {
                _countMutantDna = rs.getInt(COUNT_MUTANT_DNA);
                _countHumanDna = rs.getInt(COUNT_HUMAN_DNA);
            }
        }
    }

    @Override
    public JsonObject toJsonObject() {
        final JsonObject jo = new JsonObject();
        jo.add(COUNT_MUTANT_DNA_KEY, Json.num(_countMutantDna));
        jo.add(COUNT_HUMAN_DNA_KEY, Json.num(_countHumanDna));
        jo.add(RATIO_KEY, Json.num(calculateRatio()));
        
        return jo;
    }

    @Override
    public String getQuery() {
        //TODO
        return QUERY;
    }

}
