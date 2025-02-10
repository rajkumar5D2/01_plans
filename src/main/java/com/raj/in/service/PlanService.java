package com.raj.in.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.raj.in.entity.PlanEntity;

@Service
public interface PlanService {
	public Map<Integer, String> getPlanCategories();
	
	public List<PlanEntity> getAllPlans();
	
	public boolean save(PlanEntity plan);
	
	public PlanEntity getPlanById(Integer planId);
	
	public boolean updatePlan(PlanEntity plan);
	
	public boolean deletePlanById(Integer planId);
	
	public boolean planStatus(Integer planID, String status);
}
