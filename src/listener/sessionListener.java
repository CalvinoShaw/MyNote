package listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class sessionListener implements HttpSessionListener {
 
    private static int count = 0;
 
    public void sessionCreated(HttpSessionEvent se) {
       count++;
       System.out.println("session创建：" + new java.util.Date());
 
    }
 
    public void sessionDestroyed(HttpSessionEvent se) {
       count--;
       System.out.println("session销毁：" + new java.util.Date());
    }
 
    public static int getCount() {
       return count;
    }
    
    
}