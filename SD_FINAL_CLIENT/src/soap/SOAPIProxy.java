package soap;

public class SOAPIProxy implements soap.SOAPI {
  private String _endpoint = null;
  private soap.SOAPI sOAPI = null;
  
  public SOAPIProxy() {
    _initSOAPIProxy();
  }
  
  public SOAPIProxy(String endpoint) {
    _endpoint = endpoint;
    _initSOAPIProxy();
  }
  
  private void _initSOAPIProxy() {
    try {
      sOAPI = (new soap.SOAPImplServiceLocator()).getSOAPImplPort();
      if (sOAPI != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sOAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sOAPI)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sOAPI != null)
      ((javax.xml.rpc.Stub)sOAPI)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public soap.SOAPI getSOAPI() {
    if (sOAPI == null)
      _initSOAPIProxy();
    return sOAPI;
  }
  
  public void transferencia(java.lang.String arg0, java.lang.String arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.transferencia(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
  }
  
  public void estadoCuenta(java.lang.String arg0, int arg1) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.estadoCuenta(arg0, arg1);
  }
  
  public void retirar(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.retirar(arg0, arg1, arg2, arg3);
  }
  
  public void registrar(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int arg3, int arg4, int arg5) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.registrar(arg0, arg1, arg2, arg3, arg4, arg5);
  }
  
  public void estadoCuenta2(java.lang.String arg0, int arg1) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.estadoCuenta2(arg0, arg1);
  }
  
  public void depositar(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.rmi.RemoteException{
    if (sOAPI == null)
      _initSOAPIProxy();
    sOAPI.depositar(arg0, arg1, arg2, arg3);
  }
  
  
}