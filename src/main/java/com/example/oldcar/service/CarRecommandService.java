package com.example.oldcar.service;

import com.example.oldcar.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

public class CarRecommandService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private CarHeaderRepository carHeaderRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarSeriesRepository carSeriesRepository;

    private void Similarity() {

    }

}
