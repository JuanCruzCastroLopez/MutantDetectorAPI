package meli.mutantdetector.api.model;

import meli.mutantdetector.api.database.DBObjectInterface;
import ace.gson.Json;
import com.google.gson.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Stadistic implements DBObjectInterface, JsonParseable {
    
    private static final String COUNT_DNA_FIELD = "COUNT_DNA";
    private static final String IS_MUTANT_FIELD = "IS_MUTANT";
    private static final String IS_MUTANT = "T";

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
            return (_countMutantDna / (double)_countHumanDna);
        } catch (final ArithmeticException ex) {
            return 0d;
        }
    }

    @Override
    public void setByResultSet(final ResultSet rs) throws SQLException {
        if (rs != null) {
            while (rs.next()) {
                final String isMutant = rs.getString(IS_MUTANT_FIELD);
                if (isMutant.equals(IS_MUTANT)) {
                    _countMutantDna = rs.getInt(COUNT_DNA_FIELD);
                } else {
                    _countHumanDna = rs.getInt(COUNT_DNA_FIELD);
                }
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
        return "SELECT IS_MUTANT, COUNT(*) AS COUNT_DNA FROM VERIFIED_DNAS GROUP BY IS_MUTANT";
    }

}
