package org.stackspace.spark.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stackspace.spark.command.Command;
import org.stackspace.spark.spi.config.MetadataContext;
import org.stackspace.spark.spi.config.MetadataContextManager;
import org.stackspace.spark.spi.config.XmlMetadataContextManagerImpl;
import org.stackspace.spark.web.helper.CommandHelper;
import org.stackspace.spark.web.helper.Dispatcher;

public class SparkServlet extends HttpServlet {
	private final String SPARK_CONFIG_NAMESPACE = "/WEB-INF/spark-web.xml";
	private String SPARK_WEB_CONTEXT_PATH;
	private MetadataContext context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		MetadataContextManager manager = null;
		ServletContext servletContext = null;

		servletContext = config.getServletContext();
		SPARK_WEB_CONTEXT_PATH = servletContext
				.getRealPath(SPARK_CONFIG_NAMESPACE);

		manager = new XmlMetadataContextManagerImpl();
		context = manager.createMetadataContext(SPARK_WEB_CONTEXT_PATH);
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = null;
		String outcome = null;
		Command command = null;

		action = request.getServletPath();
		command = CommandHelper.getCommandObject(action);
		outcome = command.execute(request, response);
		Dispatcher.dispatch(request, response, outcome);
	}

}
