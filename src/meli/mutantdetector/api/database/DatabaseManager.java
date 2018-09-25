package meli.mutantdetector.api.database;

import ace.constants.STRINGS;
import ace.text.Strings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import meli.mutantdetector.api.MutantDetectorAPI;
import org.apache.log4j.Logger;
import meli.mutantdetector.api.model.DBObject;

public class DatabaseManager {

    private static Logger _logger = Logger.getLogger(MutantDetectorAPI.class);

    private final String _vendor;
    private final String _host;
    private final String _port;
    private final String _name;
    private final String _username;
    private final String _password;
    private final String _schema;
    private final String _tablespaceData;
    private final String _tablespaceIndexes;
    private final String _jdbcUrl;
    private Connection _connection;
    private boolean _isAvailable;
    private boolean _isConnected;

    public DatabaseManager(final DatabaseVendorInfo vendorInfo, final DatabaseConnectionInfo connectionInfo) {
        _connection = null;
        _vendor = vendorInfo.getVendorName();
        _host = connectionInfo.getHost();
        _port = String.valueOf(connectionInfo.getPort());
        _name = connectionInfo.getName();
        _jdbcUrl = Strings.concat(
                vendorInfo.getUriScheme(), STRINGS.COLON,
                DatabaseVendorInfo.MYSQL_VENDOR_NAME.equals(_vendor) ? STRINGS.AT : STRINGS.EMPTY,
                STRINGS.SLASH + STRINGS.SLASH, _host, STRINGS.COLON, _port, STRINGS.SLASH, _name
        );
        _username = connectionInfo.getUsername();
        _password = connectionInfo.getPassword();
        _schema = connectionInfo.getSchema();
        _tablespaceData = connectionInfo.getTablespaceData();
        _tablespaceIndexes = connectionInfo.getTablespaceIndexes();
        _logger.info("Creating database. Vendor: " + _vendor + " Host: " + _host + " Port: " + _port);
        final String jdbcClassname = vendorInfo.getDriverName();
        try {
            Class.forName(jdbcClassname);
            _isAvailable = true;
            _logger.info("Database available. Vendor: " + _vendor + " jdbcClassname: " + jdbcClassname);
        } catch (final Exception e) {
            _logger.info("Database unavailable. Vendor: " + _vendor + " jdbcClassname: " + jdbcClassname, e);
            _isAvailable = false;
        }
        if (_isAvailable) {
            connect();
        } else {
            _isConnected = false;
        }
    }

    public final String getVendor() {
        return _vendor;
    }

    public final String getSchema() {
        return _schema;
    }

    public final String getTablespaceData() {
        return _tablespaceData;
    }

    public final String getTablespaceIndexes() {
        return _tablespaceIndexes;
    }

    protected final void connect() {
        try {
            _connection = DriverManager.getConnection(_jdbcUrl, _username, _password);
            _isConnected = true;
            _logger.info("Connected database. Vendor: " + _vendor + " Host: " + _host + " Port: " + _port);
        } catch (final Exception e) {
            _logger.info("Unconnected database. Vendor: " + _vendor + " Host: " + _host + " Port: " + _port);
        }
    }

    public final void disconnect() {
        try {
            _connection.close();
            _logger.info("Database disconnected. Vendor: " + _vendor + " Host: " + _host + " Port: " + _port);
        } catch (final Exception e) {
            _logger.info("Database undisconnected. Vendor: " + _vendor + " Host: " + _host + " Port: " + _port);
        } finally {
            _connection = null;
        }
    }

    public boolean isAvailable() {
        return _isAvailable;
    }

    public boolean isConnected() {
        return _isConnected;
    }

    public DBObject executeSelect(final DBObject settable) {
        if (isActiveConnection()) {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = _connection.prepareStatement(settable.getQuery());
                rs = stmt.executeQuery();
                settable.setByResultSet(rs);
                return settable;
            } catch (final Exception e) {
                _logger.info("Database unexecutable ", e);
            } finally {
                closeResources(stmt, rs);
            }
        }
        return null;
    }

    private void closeResources(final AutoCloseable... items) {
        for (final AutoCloseable item : items) {
            if (item != null) {
                try {
                    item.close();
                } catch (final Exception e) {
                    _logger.info("Database uncloseable: " + item.getClass().getSimpleName());
                }
            }
        }
    }

    private boolean isActiveConnection() {
        try {
            if (_connection.isClosed()) {
                connect();
            }
        } catch (final Exception e) {
            _isConnected = false;
        }
        return _isConnected;
    }

}
