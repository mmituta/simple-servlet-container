package com.mituta.container.configuration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Is responsible for reading servlet container configuration.
 */
public class ContainerConfigurationReader
{

    /**
     * Reads an instance of {@link ContainerConfiguration} from the file with the specified name.
     *
     * @param fileName name of the file to read.
     * @return instance of {@link ContainerConfiguration} with properties read from the file.
     * @throws URISyntaxException
     * @throws JAXBException
     */
    public ContainerConfiguration readFromResources(
        String fileName ) throws URISyntaxException, JAXBException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource( fileName );
        if( resource == null )
        {
            throw new IllegalArgumentException( "file not found: " + fileName );
        }

        return readFromFile( new File( resource.toURI() ) );
    }

    private ContainerConfiguration readFromFile( File file ) throws JAXBException
    {
        Unmarshaller unmarshaller = createUnmarshaller();
        return (ContainerConfiguration)unmarshaller.unmarshal( file );
    }

    private Unmarshaller createUnmarshaller() throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance( ContainerConfiguration.class );
        return context.createUnmarshaller();
    }
}
