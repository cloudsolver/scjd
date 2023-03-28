/**
 * URLyBird Application Copyright Notice. Programmed by: Rohit Sood.
 * Motivation: Sun Certified Java Developer for Java 1.4 All rights reserved.
 */
package com.rohitsood.urlybird.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Properties;


/**
 * Maintains the configuration properties maintained in the
 * suncertify.properties file. Loading and saving of items in the properties
 * file is done here.
 *
 * @author Rohit Sood
 * @version 1.3
 */
public class ConfigurationProperties {
    /** The server protocol properties file constant. */
    private static final String PROTOCOL_KEY = "server.protocol";

    /** The server protocol properties file constant. */
    private static final String HOST_KEY = "server.host";

    /** The server protocol properties file constant. */
    private static final String PORT_KEY = "server.port";

    /** The server protocol properties file constant. */
    private static final String SERVER_LOOKUP_KEY = "server.lookup.name";

    /** The server protocol properties file constant. */
    private static final String DB_LOCATION = "db.location";

    /** The magic cookie value. */
    private static final String MAGIC_COOKIE_VALUE = "magic.cookie";

    /** The server protocol properties file constant. */
    private static final String PROTOCOL_SEPARATOR = "://";

    /** The server protocol properties file constant. */
    private static final String URI_SEPARATOR = "/";

    /** The server protocol properties file constant. */
    private static final String PORT_SEPARATOR = ":";

    /** The server protocol properties file constant. */
    private static final String PROPERTIES_FILE_NAME = "suncertify.properties";

    /** The server protocol properties file constant. */
    private static final String HOME_DIR_PROPERTY = "user.dir";

    /** The server protocol properties file constant. */
    private static final String PROPERTY_FILE_HEADLINE = "Do not manually modfiy this file. Last Modified: ";

    /** The server protocol. */
    private String protocol;

    /** The server host name. */
    private String host;

    /** The rmi port. */
    private String port;

    /** The remote object name. */
    private String serverName;

    /** The database location on the file system. */
    private String dbLocation;

    /** The properties. */
    private Properties suncertify;

    /** The registry look up name for the remote object. */
    private String lookupName;

    /** The magic cookie value. */
	private String magicCookie;

    /** The file object reference for the physical properties file. */
    private File file;

    /**
     * Creates a new ConfigurationProperties object.
     * Each new instance reloads the properties file from the file system.
     */
    public ConfigurationProperties() {
        reload();
    }

    /**
     * Sets the server host name.
     * This can be a name or an IP address.
     *
     * @param host The host name to store.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Sets the rmi registry port.
     *
     * @param port The port number.
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Sets the protocol for communication.
     *
     * @param protocol The protocol.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * Sets the name in the rmi registry for looking up the remote object.
     *
     * @param serverName The remote object name.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Sets the magic cookie value for the database file.
     * @param magicCookie The magic cookie value to store.
     */
    public void setMagicCookie(String magicCookie){
    	this.magicCookie=magicCookie;
    }

    /**
     * Returns the magic cookie value.
     * @return The magic cookie value.
     */
    public String getMagicCookie(){
    	return this.magicCookie;
    }

    /**
     * Reloads data from the physical properties file.
     */
    public void reload() {

        suncertify = new Properties();
        InputStream is = null;

        try {
            is = new FileInputStream(getFile());
            suncertify.load(is);
            mapFields(suncertify);
        } catch (IOException e) {
            System.err.println("IO Exception happened reloading properties.");
        }
    }

    /**
     * Locates the properties file and instantiates a <tt>File</tt> object.
     *
     * @return The <tt>File</tt> object corresponding to the properties file.
     *
     * @throws IOException If file could not be found, permission to read is
     *         denied or any other problems with the file.
     */
    private File getFile() throws IOException {
        if (file == null) {
            final String fileName = System.getProperty(HOME_DIR_PROPERTY) +
                URI_SEPARATOR + PROPERTIES_FILE_NAME;
            file = new File(fileName);

            //if the properties file does not exist create one
            if (!file.exists()) {
                file.createNewFile();
            }
        }

        return file;
    }

    /**
     * Maps the properties from the properties file to the fields in this
     * object.
     *
     * @param properties The properties to map from .
     */
    private void mapFields(Properties properties) {
        protocol = properties.getProperty(PROTOCOL_KEY);
        host = properties.getProperty(HOST_KEY);
        port = properties.getProperty(PORT_KEY);
        serverName = properties.getProperty(SERVER_LOOKUP_KEY);
        dbLocation = properties.getProperty(DB_LOCATION);
        magicCookie=properties.getProperty(MAGIC_COOKIE_VALUE);
    }

    /**
     * Figures out the remote factory look up name based on the protocol, port,
     * host and remote object names.
     *
     * @return The lookup name for the remote factory object.
     */
    public String getLookupName() {
        if (lookupName != null) {
            return lookupName;
        } else {
            reload();
        }

        final StringBuffer buffer = new StringBuffer();
        buffer.append(protocol);
        buffer.append(PROTOCOL_SEPARATOR);
        buffer.append(host);
        buffer.append(PORT_SEPARATOR);
        buffer.append(port);
        buffer.append(URI_SEPARATOR);
        buffer.append(serverName);

        lookupName = buffer.toString();

        return lookupName;
    }

    /**
     * Saves the properties in this object to the physical properties file.
     *
     * @throws IOException If problems happen while storing to the properties
     *         file.
     */
    public void persist() throws IOException {
        final OutputStream os = new FileOutputStream(getFile());

        suncertify.setProperty(PROTOCOL_KEY, protocol);
        suncertify.setProperty(HOST_KEY, host);
        suncertify.setProperty(PORT_KEY, port);
        suncertify.setProperty(SERVER_LOOKUP_KEY, serverName);
        suncertify.setProperty(DB_LOCATION, dbLocation);
        suncertify.setProperty(MAGIC_COOKIE_VALUE,magicCookie);

        suncertify.store(os, PROPERTY_FILE_HEADLINE + (new java.util.Date()));
        reload();
    }

    /**
     * Accessor for the host name.
     *
     * @return The host name.
     */
    public String getHost() {
        return host;
    }

    /**
     * Accessor for the port.
     *
     * @return The port.
     */
    public String getPort() {
        return port;
    }

    /**
     * Accessor for the protocol.
     *
     * @return The protocol.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Accessor for the server name. The remote object name to lookup.
     *
     * @return The lookup name of the remote object.
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Accessor for the location of the database on the file system.
     *
     * @return The file location.
     */
    public String getDbLocation() {
        return dbLocation;
    }

    /**
     * Mutator for the location of the database on the file system.
     *
     * @param dbLocation The file location.
     */
    public void setDbLocation(String dbLocation) {
        this.dbLocation = dbLocation;
    }
}
