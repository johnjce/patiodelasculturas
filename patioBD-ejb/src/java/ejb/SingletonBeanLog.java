package ejb;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@Startup
public class SingletonBeanLog {

    private int index = 0;
    private String user = "";

    private Map<Integer, List<String>> log = new HashMap<>();
    private Map<Integer, List<String>> clon = new HashMap<>();

    @PostConstruct
    public void init() {
        index = 0;
        user = "";
        log.clear();
        clon.clear();
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LogBean", "PostConstruct", user);
        } catch (NamingException | IOException ex) {
        }
    }

    @Lock(LockType.WRITE)
    public void setUser(String user) {
        this.user = user;
    }

    @Lock(LockType.WRITE)
    public void add(List thing, String method) {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LogBean", method, user);
        } catch (NamingException | IOException ex) {
        }
        log.put(index++, thing);
    }

    @Lock(LockType.READ)
    public Map<Integer, List<String>> getLog() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LogBean", "getLog", user);
        } catch (NamingException | IOException ex) {
        }
        return log;
    }
/*
    @Lock(LockType.READ)
    @Schedule(second = "* /5", minute = "*", hour = "*")
    public void timer() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LogBean", "timer5", user);
        } catch (NamingException | IOException ex) {
        }
        if (log.equals(clon)) {
            add(new ArrayList<>(Arrays.asList("Automata Log 5 segundos", "Sistema")),"Automata5");
        }
        clon = new HashMap<>(log);
    }

    @Lock(LockType.READ)
    @Schedule(second="* /2", minute="*", hour="*")
    public void writeOnDisk(){
        try {
            String message ="Automata Log 2 segundos\n";
            String path = "/var/www/vhosts/elpatiodelasculturas.org.es/appservers/payara-5x/logs/log.txt";
            File document = new File(path);
            PrintWriter printWriter;
            try (FileWriter fileWriter = new FileWriter(document, false)) {
                printWriter = new PrintWriter(fileWriter);
                message = getLog()
                        .entrySet()
                        .stream()
                        .map((entry) -> entry.getValue() +"\n")
                        .reduce(message, String::concat);
                        printWriter.println(message);
            }
            System.out.println(message);
            printWriter.close();
        } catch (IOException ex) {
        }
    }*/
    
    @PreDestroy
    public void predestroy() {
        try {
            PersistLog persistLog = InitialContext.doLookup("java:global/patioBD/patioBD-ejb/PersistLog!ejb.PersistLog");
            persistLog.setText("LogBean", "PreDestroy", user);
        } catch (NamingException | IOException ex) {
        }

    }

}
