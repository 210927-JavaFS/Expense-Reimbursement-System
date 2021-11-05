package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.ErsReimbursement;
import com.revature.service.ErsReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ErsReimbursementController implements Controller {

	private ErsReimbursementService ersReimbursementService = new ErsReimbursementService();
	public static Logger myLogger = LoggerFactory.getLogger("myLogger");
	
	public Handler getAllReimbursements = (ctx) -> {
		
		if (ctx.req.getSession(false) != null) {
			myLogger.info("Attempt to get all Reimbs");
			List<ErsReimbursement> list = ersReimbursementService.getAllReimbursements();

			ctx.json(list);
			ctx.status(200);
			myLogger.info("Attempt Successful");
		} else {
			ctx.status(401);
			myLogger.info("Attempt failed");
		}
	};

	public Handler getReimbursementByStatus = (ctx) -> {
		
		if (ctx.req.getSession(false) != null) {
			myLogger.info("Attempt to get Reimbs by status");
			try {
				String idString = ctx.pathParam("statusId");
				List<ErsReimbursement> list = ersReimbursementService.getReimbursementByStatus(idString);
				ctx.json(list);
				ctx.status(200);
				myLogger.info("Attempt Successful");
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				ctx.status(406);
			}
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler getMyReimbursement = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			myLogger.info("Attempt to get Reimbs by user");
			try {
				String idString = ctx.pathParam("userId");
				int userId = Integer.parseInt(idString);
				List<ErsReimbursement> list = ersReimbursementService.getMyReimbursement(userId);
				ctx.json(list);
				ctx.status(200);
				myLogger.info("Attempt Successful");
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				ctx.status(406);
				myLogger.info("Attempt Failed");
			}
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler getReimbursementById = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			try {
				myLogger.info("Attempt to get Reimbs by id");
				String idString = ctx.pathParam("reimbId");
				int reimbId = Integer.parseInt(idString);
				List<ErsReimbursement> ersReimbursement= ersReimbursementService.getReimbursementById(reimbId);
				ctx.json(ersReimbursement);
				ctx.status(200);
				myLogger.info("Attempt Successful");
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				ctx.status(406);
				myLogger.info("Attempt Failed");
			}
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler addReimbursement = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			myLogger.info("Attempt to add Reimb");
				ErsReimbursement reimbursement= ctx.bodyAsClass(ErsReimbursement.class);
				if(ersReimbursementService.addReimbursement(reimbursement)) {
					ctx.status(201);
					myLogger.info("Attempt Successful");
				}
				else {
					ctx.status(400);
					myLogger.info("Attempt Failed");
				}
			
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler updateReimbursement = (ctx) -> {
		if(ctx.req.getSession(false) != null) {
			myLogger.info("Attempt to update reimb");
				ErsReimbursement reimbursement= ctx.bodyAsClass(ErsReimbursement.class);
				if(ersReimbursementService.updateReimbursement(reimbursement)) {
					ctx.status(200);
					myLogger.info("Attempt Successful");
				}
				else {
					ctx.status(400);
					myLogger.info("Attempt Failed");
				}
			
		}
		else {
			ctx.status(401);
		}
	};
	
	public Handler deleteReimbursement = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
				String idString = ctx.pathParam("reimbId");
				try {
					int reimbId = Integer.parseInt(idString);
					ErsReimbursement reimbursement = ersReimbursementService.getReimbursementById(reimbId).get(0);
					ersReimbursementService.deleteReimbursement(reimbursement);
					ctx.status(201);
				}
				catch(NumberFormatException e){
					e.printStackTrace();
					ctx.status(406);
				}
				
				
		//}
		//else {
			//ctx.status(401);
		//}
	};
	
	@Override
	public void addRoutes(Javalin app) {
		app.get("/ErsReimbursement",this.getAllReimbursements);
		app.get("/ErsReimbursement/status/:statusId",this.getReimbursementByStatus);
		app.get("/ErsReimbursement/:reimbId",this.getReimbursementById);
		app.get("/ErsReimbursement/user/:userId",this.getMyReimbursement);
		app.post("/ErsReimbursement", this.addReimbursement);
		app.put("/ErsReimbursement/:reimbId", this.updateReimbursement);
		app.delete("ErsReimbursement/:reimbId", this.deleteReimbursement);
	}

}
