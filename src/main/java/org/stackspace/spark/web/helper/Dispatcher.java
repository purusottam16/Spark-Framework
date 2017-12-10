package org.stackspace.spark.web.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stackspace.spark.exception.UnableToDispatchViewException;
import org.stackspace.spark.spi.config.MetadataContext;

public class Dispatcher {
	public static void dispatch(HttpServletRequest request,
			HttpServletResponse response, String outcome) {
		String view = null;
		String action = null;
		MetadataContext context = null;

		try {
			action = request.getServletPath();
			context = MetadataContext.getInstance();
			view = context.getView(action, outcome);
			request.getRequestDispatcher(view).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnableToDispatchViewException(
					"Error while forwarding to view", e);
		}
	}
}
