package br.com.ellisonalves.crawlers.application.crawlers;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception that occurs always a crawler does not receive a parameter.
 *
 * @author ellison
 */
public class ConfigParameterNotFoundException extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(ConfigParameterNotFoundException.class.getCanonicalName());

    private Map<String, Object> configParameters;

    public ConfigParameterNotFoundException(String message, Map<String, Object> configParameters) {
        super(message);
        this.configParameters = configParameters;

        LOG.log(Level.INFO, toString());
    }

    @Override
    public String toString() {
        return "message: " + getMessage() + " configParameters: " + configParameters;
    }
}
