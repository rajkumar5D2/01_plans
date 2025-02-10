package com.raj.in.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raj.in.entity.PlanCategoryEntity;
import com.raj.in.entity.PlanEntity;
import com.raj.in.repository.PlanCategoryRepository;
import com.raj.in.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanCategoryRepository planCategory;
	@Autowired
	PlanRepository planRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		// TODO Auto-generated method stub
		Map<Integer, String> planCategoryMap = new HashMap<>();
		List<PlanCategoryEntity> allPlanCategory = planCategory.findAll();
		for (PlanCategoryEntity entity : allPlanCategory) {
            planCategoryMap.put(entity.getCategoryID(), entity.getCategoryName());
        }
		return planCategoryMap;
	}

	@Override
	public List<PlanEntity> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepo.findAll();
	}

	@Override
	public boolean save(PlanEntity plan) {
		// TODO Auto-generated method stub
		PlanEntity saved = planRepo.save(plan);
		return saved.getPlanId() != null;
	}

	@Override
	public PlanEntity getPlanById(Integer planId) {
		// TODO Auto-generated method stub
		Optional<PlanEntity> byId = planRepo.findById(planId);
		if (byId.isPresent()) {
			return byId.get();
			
		}
		return null;
	}

	@Override
	public boolean updatePlan(PlanEntity plan) {
		// TODO Auto-generated method stub
		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		// TODO Auto-generated method stub
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatus(Integer planID, String status) {
		// TODO Auto-generated method stub
		Optional<PlanEntity> byId = planRepo.findById(planID);
		if (byId != null) {
			PlanEntity planEntity = byId.get();
			planEntity.setActiveSw(status);
			planRepo.save(planEntity);
			return true;
		}
		return false;
	}

}
