package com.example.Surveillance.Services;

import com.example.Surveillance.Dtos.SurveillanceDTO;

import java.util.List;

public interface SurveillanceService {

    List<SurveillanceDTO> getAllSurveillances();
    SurveillanceDTO getSurveillanceById(Long id);
    SurveillanceDTO addSurveillance(SurveillanceDTO surveillanceDTO);
    SurveillanceDTO updateSurveillance(Long id, SurveillanceDTO surveillanceDTO);
    void deleteSurveillance(Long id);
}
