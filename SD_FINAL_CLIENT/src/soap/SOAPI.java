/**
 * SOAPI.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package soap;

public interface SOAPI extends java.rmi.Remote {
    public void transferencia(java.lang.String arg0, java.lang.String arg1, int arg2, int arg3, int arg4, int arg5, int arg6) throws java.rmi.RemoteException;
    public void estadoCuenta(java.lang.String arg0, int arg1) throws java.rmi.RemoteException;
    public void retirar(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.rmi.RemoteException;
    public void registrar(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int arg3, int arg4, int arg5) throws java.rmi.RemoteException;
    public void estadoCuenta2(java.lang.String arg0, int arg1) throws java.rmi.RemoteException;
    public void depositar(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.rmi.RemoteException;
}
