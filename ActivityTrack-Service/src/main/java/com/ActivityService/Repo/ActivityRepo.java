package com.ActivityService.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ActivityService.model.ActivityModel;

@Repository
public interface ActivityRepo extends JpaRepository<ActivityModel, String> {
	
	List<ActivityModel> findByUserId(String uId);

}
