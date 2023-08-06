package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SOAPI {
    
    @WebMethod
    void registrar(String bancoOrigen, String nombre, String apellido, int dni, int clave, int saldo);

    @WebMethod
    void retirar(String bancoOrigen, int dni, int clave, int monto);

    @WebMethod
    void depositar(String bancoOrigen, int dni, int clave, int monto);

    @WebMethod
    void transferencia(String bancoOrigen, String bancoDestino, int dniOrigen, int dniDestino, int dni, int clave, int monto);

    @WebMethod
    void estadoCuenta(String bancoOrigen, int dni);

    @WebMethod
    void estadoCuenta2(String bancoOrigen, int dni);
}
