package grupo6.nomina.services;

import java.util.List;

import grupo6.nomina.models.NominaModel;
import grupo6.nomina.repositories.NominaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominaService {
    
    @Autowired
    NominaRepository nominaRepository;

    public void guardarNomina(NominaModel nomina){
        this.nominaRepository.save(nomina);

    }

    public List<NominaModel> traerTodos(){
        return this.nominaRepository.findAll();
    }
    
}
