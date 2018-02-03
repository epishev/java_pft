package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="mantis_user_table")
public class DbMantisUsers {

  @Expose
  @Column(name="username")
  private String username;
  @Column(name="realname")
  private String realname;
  @Expose
  @Column(name="email")
  private String email;
  @Expose
  @Column(name="access_level", columnDefinition = "SMALLINT")
  private int accessLevel;
  @Expose
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  public DbMantisUsers withUsername(String username) {
    this.username = username;
    return this;
  }

  public DbMantisUsers withRealname(String realname) {
    this.realname = realname;
    return this;
  }

  public DbMantisUsers withEmail(String email) {
    this.email = email;
    return this;
  }

  public DbMantisUsers withAccessLevel(int accessLevel) {
    this.accessLevel = accessLevel;
    return this;
  }
  public DbMantisUsers withId(int id) {
    this.id = id;
    return this;
  }



  public int getId() {
    return id;
  }
  public String getUsername() {
    return username;
  }
  public String getRealname() {
    return realname;
  }
  public int getAccessLevel() { return accessLevel; }
  public String getEmail() {
    return email;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DbMantisUsers that = (DbMantisUsers) o;
    return Objects.equals(username, that.username) &&
            Objects.equals(email, that.email) &&
            Objects.equals(accessLevel, that.accessLevel) &&
            Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(username, email, id);
  }

  @Override
  public String toString() {
    return "MantisUser{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", id='" + id + '\'' +
            '}';
  }
}