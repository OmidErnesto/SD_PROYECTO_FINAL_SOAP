package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import soap.SOAPI;
import soap.SOAPImplService;
import soap.SOAPImplServiceLocator;


public class UserClient {

	private static String bancoA = "A";
	private static String bancoB = "B";
	private static String bancoC = "C";
	static Scanner sc= new Scanner(System.in);

	public static void main(String[] args) {
		int opcion=0;
		do{
			System.out.println("Eliga su Banco.\n\t0:Salir del Sistema.\n\t1:Banco A.\n\t2:Banco B.\n\t3:Banco C.\n\t4:Cuentas/Cliente.");
			opcion=sc.nextInt();
			switch(opcion){
			case 1: BancoA();
			break;
			case 2: BancoB();
			break;
			case 3: BancoC();
			break;
			case 4: CuentasXCliente();
			break;
			}
		}while(opcion!=0);
	}
	public static void BancoA() {
		try {
			// Crea una instancia del servicio web y obtén el puerto
			SOAPImplService service = new SOAPImplServiceLocator();
			SOAPI port = service.getSOAPImplPort();
			int opcion = 0;
			do{
				System.out.println("Se encuentra en el Banco A. Eliga una opción."
						+ "\n\t0:Salir del sistema del banco A."
						+ "\n\t1:Registrarse en el Banco A."
						+ "\n\t2:Retirar del Banco A."
						+ "\n\t3:Depositar en el Banco A."
						+ "\n\t4:Transferencia Interbancaria."
						+ "\n\t5:Estado de la Cuenta.");
				opcion=sc.nextInt();
				switch(opcion){
				case 1: Registrar(bancoA, port);
				break;
				case 2: Retirar(bancoA,port);
				break;
				case 3: Depositar(bancoA,port);
				break;
				case 4: Transferencia(bancoA,port);
				break;
				case 5: EstadoCuenta(bancoA,port);
				break;
				}
			}while(opcion!=0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void BancoB() {
		try {
			// Crea una instancia del servicio web y obtén el puerto
			SOAPImplService service = new SOAPImplServiceLocator();
			SOAPI port = service.getSOAPImplPort();
			int opcion = 0;
			do{
				System.out.println("Se encuentra en el Banco B. Eliga una opción."
						+ "\n\t0:Salir del sistema del banco B."
						+ "\n\t1:Registrarse en el Banco B."
						+ "\n\t2:Retirar del Banco B."
						+ "\n\t3:Depositar en el Banco B."
						+ "\n\t4:Transferencia Interbancaria."
						+ "\n\t5:Estado de la Cuenta.");
				opcion=sc.nextInt();
				switch(opcion){
				case 1: Registrar(bancoB, port);
				break;
				case 2: Retirar(bancoB,port);
				break;
				case 3: Depositar(bancoB,port);
				break;
				case 4: Transferencia(bancoB,port);
				break;
				case 5: EstadoCuenta(bancoB,port);
				break;
				}
			}while(opcion!=0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void BancoC() {
		try {
			// Crea una instancia del servicio web y obtén el puerto
			SOAPImplService service = new SOAPImplServiceLocator();
			SOAPI port = service.getSOAPImplPort();
			int opcion = 0;
			do{
				System.out.println("Se encuentra en el Banco C. Eliga una opción."
						+ "\n\t0:Salir del sistema del banco C."
						+ "\n\t1:Registrarse en el Banco C."
						+ "\n\t2:Retirar del Banco C."
						+ "\n\t3:Depositar en el Banco C."
						+ "\n\t4:Transferencia Interbancaria."
						+ "\n\t5:Estado de la Cuenta.");
				opcion=sc.nextInt();
				switch(opcion){
				case 1: Registrar(bancoC, port);
				break;
				case 2: Retirar(bancoC,port);
				break;
				case 3: Depositar(bancoC,port);
				break;
				case 4: Transferencia(bancoC,port);
				break;
				case 5: EstadoCuenta(bancoC,port);
				break;
				}
			}while(opcion!=0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CuentasXCliente() {

		try {
			SOAPImplService service = new SOAPImplServiceLocator();
			SOAPI port = service.getSOAPImplPort();

			System.out.println("Ingrese DNI del Usuario.");
			int dni=sc.nextInt();
			System.out.println("-----BANCO A-----");
			port.estadoCuenta2(bancoA, dni);
			System.out.println("-----BANCO B-----");
			port.estadoCuenta2(bancoB, dni);
			System.out.println("-----BANCO C-----");
			port.estadoCuenta2(bancoC, dni);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void Registrar(String banco, SOAPI port) throws RemoteException{
		//Se capturan los datos
		System.out.println("Ingrese su nombre:");
		String nombre=sc.next();
		System.out.println("Ingrese su apellido:");
		String apellido=sc.next();
		System.out.println("Ingrese su dni:");
		int dni=sc.nextInt();
		System.out.println("Ingrese su clave:");
		int clave=sc.nextInt();
		System.out.println("Ingrese su saldo inicial:");
		int saldo=sc.nextInt();
		
		// Llamar a los métodos del servicio web
		port.registrar(banco, nombre, apellido, dni, clave, saldo);
		
	}

	public static void Retirar(String banco, SOAPI port) throws RemoteException{
		int money=0;
		//Se capturan los datos
		System.out.println("Ingrese su DNI:");
		int dni=sc.nextInt();
		System.out.println("Ingrese su Clave:");
		int clave=sc.nextInt();
		System.out.println("Ingrese el monto a retirar:");
		money=sc.nextInt();
		
		port.retirar(banco,dni, clave, money);
		
	}

	public static void Depositar(String banco, SOAPI port) throws RemoteException{
		int money=0;
		//Se capturan los datos
		System.out.println("Ingrese su DNI:");
		int dni=sc.nextInt();
		System.out.println("Ingrese su Clave:");
		int clave=sc.nextInt();
		System.out.println("Ingrese el monto a depositar:");
		money=sc.nextInt();
		
		port.depositar(banco,dni, clave, money);
		
	}
	public static void Transferencia(String banco, SOAPI port) throws RemoteException{
		//Se capturan los datos
		System.out.println("Ingrese Banco Destino:");
		String Bank=sc.next();
		System.out.println("Ingrese su DNI de la otra cuenta:");
		int dni2=sc.nextInt();
		System.out.println("Ingrese su DNI:");
		int dni=sc.nextInt();
		System.out.println("Ingrese su Clave:");
		int clave=sc.nextInt();
		System.out.println("Ingrese el monto a depositar:");
		int moneyDepo=sc.nextInt();
		
		port.transferencia(banco,Bank, 0, dni2, dni, clave, moneyDepo);
		
	}
	public static void EstadoCuenta(String banco, SOAPI port) throws RemoteException{
		//Se capturan los datos
		System.out.println("Ingrese su DNI:");
		int dni=sc.nextInt();
		port.estadoCuenta(banco,dni);
	}
	
}