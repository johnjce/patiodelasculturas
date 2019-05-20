package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Credentiales implements Serializable {
    @Basic(optional = false)
    @Column(name = "nickname")
    private String nickname;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    

    public Credentiales() {
        super();
    }

    Credentiales(String nickname, String password) {
        super();
        this.nickname = nickname;
        this.password = password;

    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
