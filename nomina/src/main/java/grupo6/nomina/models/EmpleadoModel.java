package grupo6.nomina.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="empleados")
public class EmpleadoModel {
    @Id
    private String documento;
    private String nombre;
    private String cargo;
    private Float salario;
    
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Float getSalario() {
        return salario;
    }
    public void setSalario(Float salario) {
        this.salario = salario;
    }

    
}
