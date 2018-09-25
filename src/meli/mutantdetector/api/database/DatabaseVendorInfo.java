package meli.mutantdetector.api.database;

public class DatabaseVendorInfo {

    public static final String MYSQL_VENDOR_NAME = "MYSQL";
    public static final String MYSQL_URI_SCHEME = "jdbc:mysql";
    public static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";

    private final String _driverName;
    private final String _uriScheme;
    private final String _vendorName;

    public DatabaseVendorInfo(final String vendorName, final String uriScheme, final String driverName) {
        _vendorName = vendorName;
        _uriScheme = uriScheme;
        _driverName = driverName;
    }

    public String getVendorName() {
        return _vendorName;
    }

    public String getUriScheme() {
        return _uriScheme;
    }

    public String getDriverName() {
        return _driverName;
    }

}
