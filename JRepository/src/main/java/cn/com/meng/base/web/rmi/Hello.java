package cn.com.meng.base.web.rmi;
import java.rmi.*;    
import java.rmi.server.*;    
   
/**   
 * 扩展了UnicastRemoteObject类，并实现远程接口 HelloInterface   
 */   
public class Hello extends UnicastRemoteObject implements HelloInterface    
{    
   /**
	 * 
	 */
	private static final long serialVersionUID = -3953818040410256107L;
private String message;    
   
   /**   
    * 必须定义构造方法，即使是默认构造方法，也必须把它明确地写出来，因为它必须抛出出RemoteException异常   
    */   
   public Hello(String msg) throws RemoteException    
   {    
      message = msg;    
   }    
   
   /**   
    * 远程接口方法的实现   
    */   
   public String say() throws RemoteException    
   {    
      System.out.println("Called by HelloClient");    
      return message;    
   }    
}