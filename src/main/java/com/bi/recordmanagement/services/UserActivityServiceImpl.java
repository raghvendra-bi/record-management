package com.bi.recordmanagement.services;

import com.bi.recordmanagement.models.UserActivity;
import com.bi.recordmanagement.repository.UserActivityRepository;
import com.bi.recordmanagement.services.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    private UserActivityRepository activityRepository;

    @Override
    public void save(UserActivity activity) {
        activityRepository.save(activity);
    }
}
