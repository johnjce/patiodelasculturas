package ejb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class StaticsBean {

    private final ArrayList<String> users = new ArrayList<>();
    private final Map<String, Integer> pageCount = new HashMap<>();
    private final Map<String, Integer> componentCount = new HashMap<>();
    private String user = "";

    public void setUser(String user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        users.clear();
        pageCount.clear();
        componentCount.clear();
        user="";
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }
    }
    @Lock(LockType.READ)
    public ArrayList<String> getArrayUsers() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "getArrayUsers", user);
        } catch (NamingException | IOException ex) {
        }
        return users;
    }
    
    @PreDestroy
    public void destroy(){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "PostDestroy", user);
        } catch (NamingException | IOException ex) {
        }
        
    }
    @Lock(LockType.READ)
    public Map<String, Integer> getPageCount() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "getPageCount", user);
        } catch (NamingException | IOException ex) {
        }
        return pageCount;
    }
    @Lock(LockType.READ)
    public Map<String, Integer> getComponentCount() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "getComponentCount", user);
        } catch (NamingException | IOException ex) {
        }
        return componentCount;
    }
    
    @Lock(LockType.WRITE)
    public void addPage(String page){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "addPage", user);
        } catch (NamingException | IOException ex) {
        }
        if(pageCount.containsKey(page)){
            pageCount.put(page, pageCount.get(page)+1);
        }else{
            pageCount.put(page, 1);
        }
    }
    @Lock(LockType.WRITE)
    public void addComponent(String page){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "addComponent", user);
        } catch (NamingException | IOException ex) {
        }
        if(componentCount.containsKey(page)){
            componentCount.put(page, componentCount.get(page)+1);
        }else{
            componentCount.put(page, 1);
        }
    }
    @Lock(LockType.WRITE)
    public void addUser(String user){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "addUser", user);
        } catch (NamingException | IOException ex) {
        }
        if(!users.contains(user)){
            users.add(user);
        }
    }
    @Lock(LockType.WRITE)
    public void removeUser(String user){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "removeUser", user);
        } catch (NamingException | IOException ex) {
        }
        users.remove(user);
    }
    @Lock(LockType.READ)
    public int getTotalUsers(){
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patio/patio-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("StaticsBean", "getTotalUsers", user);
        } catch (NamingException | IOException ex) {
        }
        return users.size();
    }
}