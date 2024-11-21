package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratoReporteDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;
import com.ingsw.petpal.model.entity.Contrats;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContratsService {
    List<ContratoDetailsDTO> findAll();


    ContratoDetailsDTO create(ContratsCreateUpdateDTO contrats);
    ContratoDetailsDTO findById(int id);
    List<ContratoDetailsDTO> findByCarerId(int carerid);
    ContratoDetailsDTO update(ContratsCreateUpdateDTO contrats);
    ContratoReporteDTO getContractReportByMonth(Integer userGeneralId, int mes, int anio);

    @Transactional
    ContratoDetailsDTO update(Integer id, ContratsCreateUpdateDTO contrats);

    ContratoDetailsDTO updateEstado(Integer id, String nuevoEstado);
    List<ContratoDetailsDTO> findByUserId(Integer userId);

    void delete(Integer id);

}
