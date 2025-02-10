package com.raj.in.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raj.in.Constants.AppConstants;
import com.raj.in.entity.PlanEntity;
import com.raj.in.properties.ProjectProperties;
import com.raj.in.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;

//	private ProjectProperties messages;
	
	private Map<String,String> messages2;
	
	public PlanRestController(PlanService planService, ProjectProperties messages) {
		super();
		this.planService = planService;
		this.messages2 = messages.getMessages();
		System.out.println(this.messages2);
	}

	//getting all categories
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getCategories(){
		Map<Integer, String> planCategories = planService.getPlanCategories();
		return new ResponseEntity<Map<Integer,String>>(planCategories,HttpStatus.OK);//Map is converted to JSON(ResponseEntity class) format to display in the web page
		
	}
	
	//sending user plan via form data and saving it to db
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanEntity plan){
		String responseMsg = AppConstants.EMPTY_STR;
		boolean isSaved = planService.save(plan);
		if(isSaved) {
			responseMsg = messages2.get(AppConstants.PLAN_SAVE_SUCCESS);
		}else {
			responseMsg = messages2.get(AppConstants.PLAN_SAVE_FAIL);
		}	
		return new ResponseEntity<String>(responseMsg,HttpStatus.CREATED);
	}
	
	//view plan on the screen
	@GetMapping("/viewPlans")
	public ResponseEntity<List<PlanEntity>> viewPlans(){
		List<PlanEntity> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<PlanEntity>>(allPlans,HttpStatus.OK);
	}
	//edit plan. when user clicks edit of particular plan this method takes the planID from the url and store it in the parameter to retrieve that plan from DB and display on the web page
	@GetMapping("/viewPlans/{planId}")
	public ResponseEntity<PlanEntity>editPlan(@PathVariable Integer planId){// @pathvariable is used to store a vale from the url to a variable in the method
		PlanEntity plan = planService.getPlanById(planId);
		return new ResponseEntity<PlanEntity>(plan,HttpStatus.OK);
	}
	
	@PutMapping("/viewPlans")
	public ResponseEntity<String> updatePlan(@RequestBody PlanEntity plan){
		String msg = AppConstants.EMPTY_STR;
		boolean updatePlan = planService.updatePlan(plan);
		if(updatePlan) {
			msg = messages2.get(AppConstants.PLAN_UPDATE_SUCCESS);
		}else {
			msg = messages2.get(AppConstants.PLAN_UPDATE_FAILED);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/viewPlans/{planId}")
	public ResponseEntity<String>deletePlan(@PathVariable Integer planId){
		String msg = AppConstants.EMPTY_STR;
		boolean deletePlan = planService.deletePlanById(planId);
		if(deletePlan) {
			msg = messages2.get(AppConstants.PLAN_DELETION_SUCCESS);
		}else {
			msg = messages2.get(AppConstants.PLAN_DELETION_FAILED);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status){
		String msg = AppConstants.EMPTY_STR;
		boolean statusChange = planService.planStatus(planId, status);
		if(statusChange) {
			msg = messages2.get(AppConstants.PLAN_STATUS_CHANGE_SUCCESS);
		}else {
			msg = messages2.get(AppConstants.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
//	@GetMapping("/welcome")
//	public String display() {
//		return "welcome!!!"; 
//	}
}
