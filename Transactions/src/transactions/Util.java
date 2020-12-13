package transactions;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;

public class Util {
    
    public static void listResult(List<?> result){
    	System.out.println(result.size());
        result.stream().forEach((o) -> {
            System.out.println(o);
        });
    }
    
    public static void listRefreshedResult(ObjectContainer container,ObjectSet result,int depth) {
        System.out.println(result.size());
        while(result.hasNext()) {
            Object obj = result.next();
            container.ext().refresh(obj, depth);
            System.out.println(obj);
        }
    }
    
    public static void retrieveAll(ObjectContainer db){
        ObjectSet result=db.queryByExample(new Object());
        listResult(result);
    }
    
    public static void deleteAll(ObjectContainer db) {
        ObjectSet result=db.queryByExample(new Object());
        while(result.hasNext()) {
            db.delete(result.next());
        }
    }
}