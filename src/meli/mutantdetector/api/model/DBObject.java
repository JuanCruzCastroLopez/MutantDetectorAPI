/* AMCO @ 2018 */

package meli.mutantdetector.api.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBObject {
    
    public void setByResultSet(final ResultSet rs) throws SQLException;

    public String getQuery();

}