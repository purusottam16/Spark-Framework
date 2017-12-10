package org.stackspace.spark.web.helper;

import org.stackspace.spark.command.Command;
import org.stackspace.spark.exception.CommandCreationException;
import org.stackspace.spark.spi.config.MetadataContext;

public class CommandHelper {
	public static Command getCommandObject(String action) {
		MetadataContext context = null;
		String commandClass = null;
		Command command = null;

		try {
			context = MetadataContext.getInstance();
			commandClass = context.getCommandClass(action);
			command = (Command) Class.forName(commandClass).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
			throw new CommandCreationException(
					"Unable to instantiate the Command object", e);
		}

		return command;
	}
}
