package ejb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class PersistLog {

    private File document;
    private PrintWriter printWriter;
    private FileWriter fileWriter;
    private boolean isOpen = false;
    private String path = "/var/www/vhosts/elpatiodelasculturas.org.es/appservers/payara-5x/logs/log.txt";

    public void setPath(String path) {
        this.path = path;
    }

    @PostConstruct
    public void createDocument() {
        try {
            document = new File(path);
            fileWriter = new FileWriter(document, true);
            printWriter = new PrintWriter(fileWriter);
            printWriter.println("Log: El Patio De Las Culturas\n");
            close();
        } catch (IOException ex) {
        }
    }

    @Lock(LockType.WRITE)
    public void setText(String clase, String metodo, String usuario) throws IOException {
        document = new File(path);
        fileWriter = new FileWriter(document, true);
        printWriter = new PrintWriter(fileWriter);
        String mensaje = clase + "::" + metodo + "::" + usuario;
        printWriter.println(mensaje);
        close();
    }

    @Lock(LockType.READ)
    public void close() {
        try {
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
        }
    }

    @PreDestroy
    public void predestroy() {

    }
}
