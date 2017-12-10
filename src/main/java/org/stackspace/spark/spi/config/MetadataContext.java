package org.stackspace.spark.spi.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stackspace.spark.config.web.CommandMapping;
import org.stackspace.spark.config.web.Forward;
import org.stackspace.spark.config.web.SparkConfig;
import org.stackspace.spark.exception.CommandNotFoundException;
import org.stackspace.spark.exception.MetadataInitializeException;
import org.stackspace.spark.exception.UnMappedOutcomeException;

final public class MetadataContext {
	private Map<String, CommandMapping> metadataMap;
	private static MetadataContext instance;

	private MetadataContext() {
		metadataMap = new HashMap<String, CommandMapping>();
	}

	public static MetadataContext getInstance() {
		if (instance == null) {
			synchronized (MetadataContext.class) {
				if (instance == null) {
					instance = new MetadataContext();
				}
			}
		}
		return instance;
	}

	public void initialize(SparkConfig config) {
		List<CommandMapping> mappings = null;
		CommandMapping mapping = null;

		mappings = config.getCommandMappings().getCommandMapping();
		for (int i = 0; i < mappings.size(); i++) {
			mapping = mappings.get(i);
			metadataMap.put(mapping.getAction(), mapping);
		}
	}

	public String getCommandClass(String action) {
		String commandClass = null;
		CommandMapping mapping = null;

		if (metadataMap.size() <= 0) {
			throw new MetadataInitializeException("metadata not initialized");
		}
		if (metadataMap.containsKey(action) == false) {
			throw new CommandNotFoundException("Command is not available");
		}
		mapping = metadataMap.get(action);
		commandClass = mapping.getCommandClass();

		return commandClass;
	}

	public String getView(String action, String outcome) {
		CommandMapping mapping = null;
		List<Forward> forwards = null;
		String view = null;

		if (metadataMap.size() <= 0) {
			throw new MetadataInitializeException("metadata not initialized");
		}
		if (metadataMap.containsKey(action) == false) {
			throw new CommandNotFoundException("Command is not available");
		}
		mapping = metadataMap.get(action);
		forwards = mapping.getForward();
		for (Forward forward : forwards) {
			if (forward.getOutcome().equals(outcome)) {
				view = forward.getView();
				break;
			}
		}
		if (view == null || view.trim().length() == 0) {
			throw new UnMappedOutcomeException("Outcome not found");
		}

		return view;
	}
}
