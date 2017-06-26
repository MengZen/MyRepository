package cn.com.meng.base.web.rmi;
import java.rmi.*;    
   
/**   
 * 远程接口必须扩展接口java.rmi.Remote   
 */   
public interface HelloInterface extends Remote    
{    
   /**   
    * 远程接口方法必须抛出 java.rmi.RemoteException   
    */   
   public String say() throws RemoteException;    
}