package soap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.jws.WebService;

@WebService(endpointInterface = "soap.SOAPI")
public class SOAPImpl implements SOAPI {
	
	static Connection connectionA = DataBaseBancoA.getConnection();
    static Connection connectionB = DataBaseBancoB.getConnection();
    static Connection connectionC = DataBaseBancoC.getConnection();
	
    private PreparedStatement stmt1 = null;
    static PreparedStatement stmt2 = null;
    static int dni = 0;
    static int clave = 0;
    static int saldo = 0;
    static Statement st1;
    static Statement st2;
    static ResultSet rs1;
    static ResultSet rs2;

	@Override
	public void registrar(String bancoOrigen, String nombre, String apellido, int dni, int clave, int saldo) {
		try {
			
			if (bancoOrigen.equalsIgnoreCase("A")) {
				// se deshabilita el modo de confirmación automática
	            connectionA.setAutoCommit(false);
			    stmt1 = connectionA.prepareStatement("INSERT INTO clientes_a VALUES( ?, ?, ?, ?, ? );");
			    
			    stmt1.setString(1, nombre);
	            stmt1.setString(2, apellido);
	            stmt1.setInt(3, dni);
	            stmt1.setInt(4, clave);
	            stmt1.setInt(5, saldo);
	            stmt1.executeUpdate();
	            
	            // se indica que se deben aplicar los cambios en la base de datos
	            connectionA.commit();
			    
			}
			
			else if (bancoOrigen.equalsIgnoreCase("B")) {
				// se deshabilita el modo de confirmación automática
	            connectionB.setAutoCommit(false);
			    stmt1 = connectionB.prepareStatement("INSERT INTO clientes_b VALUES( ?, ?, ?, ?, ? );");
			    
			    stmt1.setString(1, nombre);
	            stmt1.setString(2, apellido);
	            stmt1.setInt(3, dni);
	            stmt1.setInt(4, clave);
	            stmt1.setInt(5, saldo);
	            stmt1.executeUpdate();
	            
	            // se indica que se deben aplicar los cambios en la base de datos
	            connectionB.commit();
		    }
			
			else if (bancoOrigen.equalsIgnoreCase("C")) {
				// se deshabilita el modo de confirmación automática
	            connectionC.setAutoCommit(false);
			    stmt1 = connectionC.prepareStatement("INSERT INTO clientes_c VALUES( ?, ?, ?, ?, ? );");
			    
			    stmt1.setString(1, nombre);
	            stmt1.setString(2, apellido);
	            stmt1.setInt(3, dni);
	            stmt1.setInt(4, clave);
	            stmt1.setInt(5, saldo);
	            stmt1.executeUpdate();
	            
	            // se indica que se deben aplicar los cambios en la base de datos
	            connectionC.commit();
		    }
            
            System.out.println("Operación exitosa en el Banco " + bancoOrigen.toUpperCase());
            return;
        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
            
            if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
            
            System.out.println("Error en la operación: " + ex.getMessage());
            return;
        } finally {
            try {
                if (stmt1 != null) {
                    stmt1.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar el statement: " + ex.getMessage());
            }
        }
    }
		

	@Override
	public void retirar(String bancoOrigen, int dni, int clave, int monto) {
	    try {
	        int money = monto;
	        
	        if (bancoOrigen.equalsIgnoreCase("A")) {
	        	st1 = connectionA.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") - money;

		        // se deshabilita el modo de confirmación automática
		        connectionA.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo='" + saldo + "' WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionA.commit();
	        }
	        
	        else if (bancoOrigen.equalsIgnoreCase("B")) {
	        	st1 = connectionB.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") - money;

		        // se deshabilita el modo de confirmación automática
		        connectionB.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionB.prepareStatement("UPDATE clientes_b SET saldo='" + saldo + "' WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionB.commit();
	        }
	        
	        else if (bancoOrigen.equalsIgnoreCase("C")) {
	        	st1 = connectionC.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") - money;

		        // se deshabilita el modo de confirmación automática
		        connectionC.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionC.prepareStatement("UPDATE clientes_c SET saldo='" + saldo + "' WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionC.commit();
	        }
	        
	        System.out.println("Operación exitosa en el Banco " + bancoOrigen.toUpperCase());
	        return;
	    } catch (SQLException ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
	        System.out.println("Error en la operación: " + ex.getMessage());
	        return;
	    } finally {
	        try {
	            if (stmt1 != null) {
	                stmt1.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al cerrar el statement: " + ex.getMessage());
	        }
	    }
	}


	@Override
	public void depositar(String bancoOrigen, int dni, int clave, int monto) {
	    try {
	        int money = monto;
	        
	        if (bancoOrigen.equalsIgnoreCase("A")) {
	        	st1 = connectionA.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") + money;

		        // se deshabilita el modo de confirmación automática
		        connectionA.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo='" + saldo + "' WHERE dni='" + dni + "'AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionA.commit();
	        } else if (bancoOrigen.equalsIgnoreCase("B")) {
	        	st1 = connectionB.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") + money;

		        // se deshabilita el modo de confirmación automática
		        connectionB.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionB.prepareStatement("UPDATE clientes_b SET saldo='" + saldo + "' WHERE dni='" + dni + "'AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionB.commit();
	        } else if (bancoOrigen.equalsIgnoreCase("C")) {
	        	st1 = connectionC.createStatement();
		        rs1 = st1.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dni + "' AND clave='" + clave + "'");
		        rs1.next();
		        saldo = rs1.getInt("saldo") + money;

		        // se deshabilita el modo de confirmación automática
		        connectionC.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        stmt1 = connectionC.prepareStatement("UPDATE clientes_c SET saldo='" + saldo + "' WHERE dni='" + dni + "'AND clave='" + clave + "'");
		        stmt1.executeUpdate();

		        // se indica que se deben aplicar los cambios en la base de datos
		        connectionC.commit();
	        }
	        
	        System.out.println("Operación exitosa en el Banco " + bancoOrigen.toUpperCase());
	        return;
	    } catch (SQLException ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
	        System.out.println("Error en la operación: " + ex.getMessage());
	        return;
	    } finally {
	        try {
	            if (stmt1 != null) {
	                stmt1.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al cerrar el statement: " + ex.getMessage());
	        }
	    }
	}


	@Override
	public void transferencia(String bancoOrigen, String bancoDestino, int dniOrigen, int dniDestino, int dni, int clave, int monto) {
	    try {
	        int saldoOrigen = 0;
	        int saldoDestino = 0;
	        
	        if (bancoOrigen.equalsIgnoreCase("A")) {
	        	if (bancoDestino.equalsIgnoreCase("A")) {
		            st1 = connectionA.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionA.setAutoCommit(false);

		            stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionA.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("B")) {
		            st1 = connectionA.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		            	return;
		                //return "Saldo insuficiente para realizar la transferencia.";
		            }
		            saldoOrigen -= monto;
		            connectionA.setAutoCommit(false);

		            stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionB.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("C")) {
		            st1 = connectionA.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionA.setAutoCommit(false);

		            stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionC.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionC.prepareStatement("UPDATE clientes_c SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else {
		        	System.out.println("Banco no válido.");
		            return;
		        }

	        	if (bancoOrigen.equalsIgnoreCase("A")) {
		        	connectionA.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("B")) {
		        	connectionB.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("C")) {
		        	connectionC.commit();
		        }
	        }
	        
	        else if (bancoOrigen.equalsIgnoreCase("B")){
	        	if (bancoDestino.equalsIgnoreCase("A")) {
		            st1 = connectionB.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionB.setAutoCommit(false);

		            stmt1 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionA.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("B")) {
		            st1 = connectionB.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		            	return;
		                //return "Saldo insuficiente para realizar la transferencia.";
		            }
		            saldoOrigen -= monto;
		            connectionB.setAutoCommit(false);

		            stmt1 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionB.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("C")) {
		            st1 = connectionB.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionB.setAutoCommit(false);

		            stmt1 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionC.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionC.prepareStatement("UPDATE clientes_c SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else {
		        	System.out.println("Banco no válido.");
		            return;
		        }

	        	if (bancoOrigen.equalsIgnoreCase("A")) {
		        	connectionA.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("B")) {
		        	connectionB.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("C")) {
		        	connectionC.commit();
		        }
	        }
	        
	        else if (bancoOrigen.equalsIgnoreCase("C")){
	        	if (bancoDestino.equalsIgnoreCase("A")) {
		            st1 = connectionC.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionC.setAutoCommit(false);

		            stmt1 = connectionC.prepareStatement("UPDATE clientes_c SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionA.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("B")) {
		            st1 = connectionC.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		            	return;
		                //return "Saldo insuficiente para realizar la transferencia.";
		            }
		            saldoOrigen -= monto;
		            connectionC.setAutoCommit(false);

		            stmt1 = connectionC.prepareStatement("UPDATE clientes_c SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionB.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_b WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionB.prepareStatement("UPDATE clientes_b SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else if (bancoDestino.equalsIgnoreCase("C")) {
		            st1 = connectionA.createStatement();
		            rs1 = st1.executeQuery("SELECT saldo FROM clientes_a WHERE dni='" + dni + "' AND clave='" + clave + "'");
		            rs1.next();
		            saldoOrigen = rs1.getInt("saldo");
		            if (saldoOrigen < monto) {
		            	System.out.println("Saldo insuficiente para realizar la transferencia.");
		                return;
		            }
		            saldoOrigen -= monto;
		            connectionA.setAutoCommit(false);

		            stmt1 = connectionA.prepareStatement("UPDATE clientes_a SET saldo=? WHERE dni=? AND clave=?");
		            stmt1.setInt(1, saldoOrigen);
		            stmt1.setInt(2, dni);
		            stmt1.setInt(3, clave);
		            stmt1.executeUpdate();

		            st2 = connectionC.createStatement();
		            rs2 = st2.executeQuery("SELECT saldo FROM clientes_c WHERE dni='" + dniDestino + "'");
		            rs2.next();
		            saldoDestino = rs2.getInt("saldo");
		            saldoDestino += monto;

		            stmt2 = connectionC.prepareStatement("UPDATE clientes_c SET saldo=? WHERE dni=?");
		            stmt2.setInt(1, saldoDestino);
		            stmt2.setInt(2, dniDestino);
		            stmt2.executeUpdate();

		        } else {
		        	System.out.println("Banco no válido.");
		            return;
		        }
	        	if (bancoOrigen.equalsIgnoreCase("A")) {
		        	connectionA.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("B")) {
		        	connectionB.commit();
		        }
		        else if (bancoOrigen.equalsIgnoreCase("C")) {
		        	connectionC.commit();
		        }

		        
	        }
	        
	        System.out.println("Operación exitosa en el Banco B");
	        return;
	    } catch (SQLException ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
	        System.out.println("Error en la operación: " + ex.getMessage());
	        return;
	    } finally {
	        try {
	            if (stmt1 != null) {
	                stmt1.close();
	            }
	            if (stmt2 != null) {
	                stmt2.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al cerrar el statement: " + ex.getMessage());
	        }
	    }
	}


	@Override
	public void estadoCuenta(String bancoOrigen, int dni) {
	    try {
	        int saldo = 0;

	        if (bancoOrigen.equalsIgnoreCase("A")) {
	        	// se deshabilita el modo de confirmación automática
		        connectionA.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionA.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_a WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionA.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        else if(bancoOrigen.equalsIgnoreCase("B")) {
	        	// se deshabilita el modo de confirmación automática
		        connectionB.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionB.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_b WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionB.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        else if(bancoOrigen.equalsIgnoreCase("C")) {
	        	// se deshabilita el modo de confirmación automática
		        connectionC.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionC.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_c WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionC.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        
	    } catch (SQLException ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
	        System.out.println("Error en la operación: " + ex.getMessage());
	        return;
	    } finally {
	        try {
	            if (rs1 != null) {
	                rs1.close();
	            }
	            if (st1 != null) {
	                st1.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al cerrar el statement o resultSet: " + ex.getMessage());
	        }
	    }
	}


	@Override
	public void estadoCuenta2(String bancoOrigen, int dni) {
	    try {
	        int saldo = 0;

	        if (bancoOrigen.equalsIgnoreCase("A")) {
	        	// se deshabilita el modo de confirmación automática
		        connectionA.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionA.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_a WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionA.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        else if(bancoOrigen.equalsIgnoreCase("B")){
	        	// se deshabilita el modo de confirmación automática
		        connectionB.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionB.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_b WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionB.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        else if(bancoOrigen.equalsIgnoreCase("C")){
	        	// se deshabilita el modo de confirmación automática
		        connectionC.setAutoCommit(false);
		        // Se preparan las sentencias SQL
		        st1 = connectionC.createStatement();
		        rs1 = st1.executeQuery("SELECT * FROM clientes_c WHERE dni='" + dni + "'");

		        if (rs1.next()) {
		            String nombre = rs1.getString("nombre");
		            String apellido = rs1.getString("apellido");
		            saldo = rs1.getInt("saldo");

		            connectionC.commit();
		            System.out.println("INFORMACIÓN DE LA CUENTA\nNombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni + "\nSaldo: " + saldo);
		            return;
		        } else {
		        	System.out.println("No se encontró información para el DNI proporcionado.");
		            return;
		        }
	        }
	        

	        
	    } catch (SQLException ex) {
	        System.err.println("ERROR: " + ex.getMessage());
	        if (bancoOrigen.equalsIgnoreCase("A")) {
            	if (connectionA != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionA.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("B")) {
            	if (connectionB != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionB.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            } else if (bancoOrigen.equalsIgnoreCase("C")) {
            	if (connectionC != null) {
                    System.out.println("Se restauran los datos");
                    try {
                        // deshace todos los cambios realizados en los datos
                        connectionC.rollback();
                    } catch (SQLException ex1) {
                        System.err.println("No se pudo deshacer los cambios" + ex1.getMessage());
                    }
                }
            }
	        System.out.println("Error en la operación: " + ex.getMessage());
	        return;
	    } finally {
	        try {
	            if (rs1 != null) {
	                rs1.close();
	            }
	            if (st1 != null) {
	                st1.close();
	            }
	        } catch (SQLException ex) {
	            System.err.println("Error al cerrar el statement o resultSet: " + ex.getMessage());
	        }
	    }
	}


}
