package com.revature.controllers;

import java.util.List;

import com.revature.models.ErsReimbursement;
import com.revature.service.ErsReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ErsReimbursementController implements Controller {

	private ErsReimbursementService ersReimbursementService = new ErsReimbursementService();

	public Handler getAllReimbursements = (ctx) -> {
		if (ctx.req.getSession(false) != null) {
			List<ErsReimbursement> list = ersReimbursementService.getAllReimbursements();

			ctx.json(list);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	public Handler getReimbursementByStatus = (ctx) -> {
		//if (ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("statusId");
				List<ErsReimbursement> list = ersReimbursementService.getReimbursementByStatus(idString);
				ctx.json(list);
				ctx.status(200);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				ctx.status(406);
			}
		//}
	};
	
	public Handler getReimbursementById = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
			try {
				String idString = ctx.pathParam("reimbId");
				int reimbId = Integer.parseInt(idString);
				ErsReimbursement ersReimbursement= ersReimbursementService.getReimbursementById(reimbId);
				ctx.json(ersReimbursement);
				ctx.status(200);
			}
			catch(NumberFormatException e){
				e.printStackTrace();
				ctx.status(406);
			}
		//}
	};
	
	public Handler addReimbursement = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {

				ErsReimbursement reimbursement= ctx.bodyAsClass(ErsReimbursement.class);
				if(ersReimbursementService.addReimbursement(reimbursement)) {
					ctx.status(201);
				}
				else {
					ctx.status(400);
				}
			
		//}
		//else {
			//ctx.status(401);
		//}
	};
	
	public Handler updateReimbursement = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {

				ErsReimbursement reimbursement= ctx.bodyAsClass(ErsReimbursement.class);
				if(ersReimbursementService.addReimbursement(reimbursement)) {
					ctx.status(200);
				}
				else {
					ctx.status(400);
				}
			
		//}
		//else {
		//	ctx.status(401);
		//}
	};
	
	public Handler deleteReimbursement = (ctx) -> {
		//if(ctx.req.getSession(false) != null) {
				String idString = ctx.pathParam("reimbId");
				try {
					int reimbId = Integer.parseInt(idString);
					ErsReimbursement reimbursement = ersReimbursementService.getReimbursementById(reimbId);
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
		app.post("/ErsReimbursement", this.addReimbursement);
		app.put("/ErsReimbursement/:reimbId", this.updateReimbursement);
		app.delete("ErsReimbursement/:reimbId", this.deleteReimbursement);
	}

}
