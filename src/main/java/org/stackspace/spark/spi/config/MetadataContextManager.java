package org.stackspace.spark.spi.config;

public interface MetadataContextManager {
	MetadataContext createMetadataContext(String contextPath);
}
