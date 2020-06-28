package uk.codora.xvg.config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import uk.codora.xvg.exception.VergeConfigurationFileException;

public class DefaultConfigurationService implements ConfigurationService {

    private static final String PROPERTY_FILE_LOCATION = "classpath:/config.properties";

    @Override
    public Configuration getConfiguration() {
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<>(
                PropertiesConfiguration.class);
        builder.configure(configureParameters());
        return findConfigurationFromFile(builder);
    }

    private FileBasedConfiguration findConfigurationFromFile(
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder) {
        try {
            return builder.getConfiguration();
        } catch (ConfigurationException e) {
            throw new VergeConfigurationFileException(e);
        }
    }

    private PropertiesBuilderParameters configureParameters() {
        Parameters params = new Parameters();
        return params.properties().setFileName(PROPERTY_FILE_LOCATION);
    }

}
