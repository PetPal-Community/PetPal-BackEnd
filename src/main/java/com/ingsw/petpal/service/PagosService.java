package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.dto.PagosDetails;

import java.util.List;

public interface PagosService {
    List<PagosDetails> getAll();

    PagosDetails findById(Integer id);

    PagosDetails create(PagosDTO pagosDTO);

    PagosDetails update(Integer id, PagosDTO updatedPagosDTO);

    PagosDetails buscarPorContratoID(Integer contratoID);

    void delete(Integer id);

    PagosDetails confirmPurchase(Integer pagosId);
}
