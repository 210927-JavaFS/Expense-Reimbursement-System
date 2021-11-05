package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.ErsUser;
import com.revature.models.UserDTO;
import com.revature.service.ErsUserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ErsUserController implements Controller{

	private ErsUserService ersUserService = new ErsUserService();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");
	
	public Handler getUser = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
			String idString = ctx.pathParam("username");
			ErsUser ersUser = ersUserService.getUser(idString);
			ctx.json(ersUser);
			ctx.status(200);
		//}
		//else {
		//	ctx.status(401);
		//}
	};
	
	public Handler addUser = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
		myLogger.info("Attempt to register");
			ErsUser ersUser = ctx.bodyAsClass(ErsUser.class);
			if(ersUserService.addUser(ersUser)) {
				ctx.status(201);
				myLogger.info("Attempt Successful");
			}
			else {
				ctx.status(400);
				myLogger.info("Attempt Failed");
			}
		//}
		//else {
		//	ctx.status(400);
		//}
	};
	
	public Handler deleteUser = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
			ErsUser ersUser = ctx.bodyAsClass(ErsUser.class);
			if(ersUserService.deleteUser(ersUser)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		//}
		//else {
		//	ctx.status(400);
		//}
	};
	
	private Handler loginAttempt = (ctx) -> {
		myLogger.info("Attempt login");
		UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
		
		if(ErsUserService.login(userDTO)) {
			ctx.req.getSession(); 
			ctx.status(200);
			myLogger.info("Attempt Successful");
		}else {
			ctx.req.getSession().invalidate();
			ctx.status(401);
			myLogger.info("Attempt Failed");
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/ErsUser/:username", this.getUser);
		app.post("/Login", this.loginAttempt);
		app.post("/ErsUser", this.addUser);
		app.delete("/ErsUser", this.deleteUser);
	}

}
