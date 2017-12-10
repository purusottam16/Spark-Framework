package org.stackspace.spark.spi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.stackspace.spark.config.web.SparkConfig;
import org.stackspace.spark.exception.MetadataException;
import org.xml.sax.SAXException;

public class XmlMetadataContextManagerImpl implements MetadataContextManager {
	private final String SPARK_CONFIG_LOCATION = "META-INF/spark-web.xsd";

	public MetadataContext createMetadataContext(String contextPath) {
		Unmarshaller unmarshaller = null;
		MetadataContext context = null;
		SchemaFactory sfactory = null;
		Schema sparkWebSchema = null;
		JAXBContext jContext = null;

		try {
			sfactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			sparkWebSchema = sfactory.newSchema(this.getClass()
					.getClassLoader().getResource(SPARK_CONFIG_LOCATION));

			jContext = JAXBContext.newInstance("org.stackspace.spark.config.web");
			unmarshaller = jContext.createUnmarshaller();
			unmarshaller.setSchema(sparkWebSchema);
			JAXBElement<SparkConfig> jElement = (JAXBElement<SparkConfig>) unmarshaller
					.unmarshal(new FileInputStream(new File(contextPath)));
			SparkConfig config = jElement.getValue();
			context = MetadataContext.getInstance();
			context.initialize(config);
		} catch (SAXException | JAXBException | FileNotFoundException e) {
			e.printStackTrace();
			throw new MetadataException("Unable to load the configuration", e);
		}
		return context;
	}
}
