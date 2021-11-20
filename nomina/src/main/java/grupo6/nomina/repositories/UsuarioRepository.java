package grupo6.nomina.repositories;

import java.util.Optional;

import grupo6.nomina.models.UsuarioModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel,String>{
    
    //Para buscar a un usuario por nombre de usuario (username)
    public Optional<UsuarioModel> findByUsername(String username);

    

}
