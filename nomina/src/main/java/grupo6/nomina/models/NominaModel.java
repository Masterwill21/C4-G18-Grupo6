package grupo6.nomina.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="nomina")
public class NominaModel {
    @Id
    private String id_nomina;
    private EmpleadoModel docu_empleado;
    private Integer mes;
    private Integer dias_trabajados;
    private Float valor_pagar;

    public String getId_nomina() {
        return id_nomina;
    }
    public void setId_nomina(String id_nomina) {
        this.id_nomina = id_nomina;
    }
    public EmpleadoModel getDocu_empleado() {
        return docu_empleado;
    }
    public void setDocu_empleado(EmpleadoModel docu_empleado) {
        this.docu_empleado = docu_empleado;
    }
    public Integer getMes() {
        return mes;
    }
    public void setMes(Integer mes) {
        this.mes = mes;
    }
    public Integer getDias_trabajados() {
        return dias_trabajados;
    }
    public void setDias_trabajados(Integer dias_trabajados) {
        this.dias_trabajados = dias_trabajados;
    }
    public Float getValor_pagar() {
        return valor_pagar;
    }
    public void setValor_pagar(Float valor_pagar) {
        this.valor_pagar = valor_pagar;
    }
    

}
