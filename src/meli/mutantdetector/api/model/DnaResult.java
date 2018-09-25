
package meli.mutantdetector.api.model;

import meli.mutantdetector.api.database.Queryable;

public class DnaResult implements Queryable {

    private final String[] _dna;
    private final boolean _isMutant;

    public DnaResult(final String[] dna, boolean isMutant) {
        _dna = dna;
        _isMutant = isMutant;
    }
    
    @Override
    public String getQuery() {
        final String dna = String.join(",", _dna);
        final String isMutant = _isMutant ? "T" : "F";
        
        final StringBuilder query = new StringBuilder("INSERT INTO VERIFIED_DNAS (DNA, IS_MUTANT) VALUES ('");
        query.append(dna);
        query.append("', '");
        query.append(isMutant);
        query.append("' )");
        
        return query.toString();
    }

}