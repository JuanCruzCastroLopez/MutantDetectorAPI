package meli.mutantdetector.api.database;

public class DatabaseConnectionInfo {

    private final String _host;
    private final int _port;
    private final String _name;
    private final String _username;
    private final String _password;
    private final String _schema;
    private final String _tablespaceData;
    private final String _tablespaceIndexes;

    public DatabaseConnectionInfo(final String host, final int port, final String name, final String username, final String password, final String schema, final String tablespaceData, final String tablespaceIndexes) {
        _host = host;
        _port = port;
        _name = name;
        _username = username;
        _password = password;
        _schema = schema;
        _tablespaceData = tablespaceData;
        _tablespaceIndexes = tablespaceIndexes;
    }

    public String getSchema() {
        return _schema;
    }

    public String getHost() {
        return _host;
    }

    public int getPort() {
        return _port;
    }

    public String getName() {
        return _name;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public String getTablespaceData() {
        return _tablespaceData;
    }

    public String getTablespaceIndexes() {
        return _tablespaceIndexes;
    }

}
