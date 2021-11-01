package com.revature.controllers;

import com.revature.models.ErsUser;
import com.revature.models.UserDTO;
import com.revature.service.ErsUserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ErsUserController implements Controller{

	private ErsUserService ersUserService = new ErsUserService();
	
	
	public Handler getUser = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
			ErsUser ersUser = ersUserService.getUser(userDTO.getUsername());
			ctx.json(ersUser);
			ctx.status(200);
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler addUser = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			
			ErsUser ersUser = ctx.bodyAsClass(ErsUser.class);
			if(ersUserService.addUser(ersUser)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}
		else {
			ctx.status(400);
		}
	};
	
	public Handler deleteUser = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			ErsUser ersUser = ctx.bodyAsClass(ErsUser.class);
			if(ersUserService.deleteUser(ersUser)) {
				ctx.status(201);
			}
			else {
				ctx.status(400);
			}
		}
		else {
			ctx.status(400);
		}
	};
	
	private Handler loginAttempt = (ctx) -> {
		UserDTO userDTO = ctx.bodyAsClass(UserDTO.class);
		
		if(ErsUserService.login(userDTO)) {
			ctx.req.getSession(); 
			ctx.status(200);
		}else {
			ctx.req.getSession().invalidate();
			ctx.status(401);
		}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/ErsUser", this.getUser);
		app.post("/Login", this.loginAttempt);
		app.post("/ErsUser", this.addUser);
		app.delete("/ErsUser", this.deleteUser);
	}

}
