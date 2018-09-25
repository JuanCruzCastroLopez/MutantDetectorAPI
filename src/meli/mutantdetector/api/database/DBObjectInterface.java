
package meli.mutantdetector.api.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DBObjectInterface extends Queryable {
    
    public void setByResultSet(final ResultSet rs) throws SQLException;

}