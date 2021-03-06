package ifmo;

import javax.persistence.*;

/**
 * Created by Богдана on 20.11.2017.
 */
@Entity
public class Usr {
    private int id;
    private String name;
    private String surname;
    private String login;
    private int password;
    private String mail;
  public Usr(){}
  public Usr(String name, String surname, String login, String password, String mail){
      this.name =name;
      this.surname =surname;
      this.login =login;
      this.password=password.hashCode();
      this.mail =  mail;
  }
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO, generator = "usr")
    @SequenceGenerator(name = "usr", sequenceName = "usr_id_seq")
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 30)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 102)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usr usr = (Usr) o;

        if (id != usr.id) return false;
        if (password != usr.password) return false;
        if (name != null ? !name.equals(usr.name) : usr.name != null) return false;
        if (surname != null ? !surname.equals(usr.surname) : usr.surname != null) return false;
        if (login != null ? !login.equals(usr.login) : usr.login != null) return false;
        if (mail != null ? !mail.equals(usr.mail) : usr.mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + password;
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}
