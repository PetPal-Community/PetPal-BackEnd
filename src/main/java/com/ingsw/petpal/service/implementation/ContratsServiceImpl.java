package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.ContratoDetailsDTO;
import com.ingsw.petpal.dto.ContratoReporteDTO;
import com.ingsw.petpal.dto.ContratsCreateUpdateDTO;
import com.ingsw.petpal.exception.ResourceNotFoundException;
import com.ingsw.petpal.mapper.ContratsMapper;

import com.ingsw.petpal.model.entity.*;
import com.ingsw.petpal.repository.*;
import com.ingsw.petpal.service.ContratsService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContratsServiceImpl implements ContratsService {

    private final ContratsRepository contratsRepository;
    private final ServicesRepository servicesRepository;
    private final CarerRepository carerRepository;
    private final UserRepository userRepository;
    private final ContratsMapper contratsMapper;
    private final UserGeneralRepository userGeneralRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ContratoDetailsDTO> findAll() {
        List<Contrats> contrats = contratsRepository.findAll();
        return contrats.stream()
                .map(contratsMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public ContratoDetailsDTO create(ContratsCreateUpdateDTO contratsCreateUpdateDTO) {
        Services services = servicesRepository.findById(contratsCreateUpdateDTO.getServicio()).orElseThrow(() ->new ResourceNotFoundException("services not found with id: " + contratsCreateUpdateDTO.getServicio()));
        /*
        UserGeneral userGCarer = userGeneralRepository.findById(contratsCreateUpdateDTO.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + contratsCreateUpdateDTO.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));
        */

        UserGeneral userG = userGeneralRepository.findById(contratsCreateUpdateDTO.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + contratsCreateUpdateDTO.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));



        Contrats contrats =  contratsMapper.toEntity(contratsCreateUpdateDTO);
        contrats.setDuracionContrato(contratsCreateUpdateDTO.getDuracionContrato());
        contrats.setEstado(contratsCreateUpdateDTO.getEstado());
        contrats.setDetalles(contratsCreateUpdateDTO.getDetalles());
        contrats.setServicio(services);
        contrats.setCuidador(services.getCuidador());
        contrats.setUsuario(user);
        return contratsMapper.toDetailsDTO(contratsRepository.save(contrats));
    }

    @Transactional(readOnly = true)
    @Override
    public ContratoDetailsDTO findById(int id) {
        Contrats contrats = contratsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("contrat not found with id: " + id));
        return contratsMapper.toDetailsDTO(contrats);
    }


    @Override
    public ContratoDetailsDTO update(ContratsCreateUpdateDTO contrats) {
        return null;
    }

    @Transactional
    @Override
    public ContratoDetailsDTO update(Integer id, ContratsCreateUpdateDTO updatedContrats) {
        Contrats contratsFromDb =contratsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("contrat not found with id: " + id));

        Services services = servicesRepository.findById(updatedContrats.getServicio()).orElseThrow(() ->new ResourceNotFoundException("services not found with id: " + updatedContrats.getServicio()));
        /*
        UserGeneral userGCarer = userGeneralRepository.findById(updatedContrats.getCuidadorGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedContrats.getCuidadorGId()));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));
        */

        UserGeneral userG = userGeneralRepository.findById(updatedContrats.getUsuarioGId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updatedContrats.getUsuarioGId()));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));


        contratsFromDb.setServicio(services);
        contratsFromDb.setCuidador(services.getCuidador());
        contratsFromDb.setUsuario(user);
        contratsFromDb.setDuracionContrato(updatedContrats.getDuracionContrato());
        contratsFromDb.setEstado(updatedContrats.getEstado());
        contratsFromDb.setDetalles(updatedContrats.getDetalles());

        return contratsMapper.toDetailsDTO(contratsRepository.save(contratsFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Contrats contrats = contratsRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("contrat not found with id: " + id));
        contratsRepository.delete(contrats);
    }

    @Override
    public List<ContratoDetailsDTO> findByCarerId(int carerid){
        UserGeneral userGCarer = userGeneralRepository.findById(carerid)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + carerid));
        Integer userIDCarer = userGCarer.getCarer().getId();

        Carer carer = carerRepository.findById(userIDCarer).orElseThrow(() -> new ResourceNotFoundException("carer not found with id: " + userIDCarer));

        List<Contrats> contratos = contratsRepository.findByCuidador(carer);

        return contratos.stream()
                .map(contratsMapper::toDetailsDTO)
                .toList();
    }


    @Override
    public List<ContratoDetailsDTO> findByUserId(Integer userId){
        UserGeneral userG = userGeneralRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + userId));
        Integer userID = userG.getUsuario().getId();

        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        List<Contrats> contratos = contratsRepository.findByUsuario(user);

        return contratos.stream()
                .map(contratsMapper::toDetailsDTO)
                .toList();
    }


    @Transactional
    @Override
    public ContratoDetailsDTO updateEstado(Integer id, String estado) {
        Contrats contrato = contratsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado con id: " + id));
        contrato.setEstado(estado);
        return contratsMapper.toDetailsDTO(contratsRepository.save(contrato));
    }

    @Override
    @Transactional(readOnly = true)
    public ContratoReporteDTO getContractReportByMonth(Integer userGeneralId, int mes, int anio) {
        // Ejecutamos la consulta que obtiene el total de contratos y el total generado
        List<Object[]> results = contratsRepository.getContractReportByMonthAndYear(userGeneralId, mes, anio);

        // En este caso, esperamos que la consulta devuelva una sola fila, por eso tomamos el primer (y único) elemento
        Object[] result = results.isEmpty() ? null : results.get(0);

        // Inicializamos las variables con valores predeterminados
        int totalContracts = 0;  // Total de contratos
        BigDecimal totalAmount = BigDecimal.ZERO;  // Total de dinero generado

        if (result != null) {
            // Verificamos si el primer valor (total de contratos) no es nulo, y lo convertimos a Long y luego a int
            if (result[0] != null) {
                totalContracts = ((Long) result[0]).intValue();  // Convertimos a Long y luego a 'int'
            }

            // Verificamos si el segundo valor (total generado) no es nulo, y lo convertimos a BigDecimal
            if (result[1] != null) {
                totalAmount = new BigDecimal(result[1].toString());  // Convertimos el valor a BigDecimal
            }
        }

        // Retornamos el DTO con los valores mapeados
        return new ContratoReporteDTO(totalContracts, totalAmount);
    }


}
