package com.revature;

import com.revature.controllers.ErsReimbursementController;
import com.revature.controllers.Controller;
import com.revature.controllers.ErsUserController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App {
	
	private static Javalin app;

	public static void main(String[] args) {
		app = Javalin.create();
		app = Javalin.create((config)->{
			config.addStaticFiles("/static", Location.CLASSPATH);
		});
		
		configure(new ErsReimbursementController(), new ErsUserController());
		
		app.start(8081);

	}
	
	private static void configure(Controller... controllers) {
		for(Controller c:controllers) {
			c.addRoutes(app);
		}
	}

}