package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.DbMantisUsers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DbHelper {
  private final SessionFactory sessionFactory;
  private ApplicationManager app;

  public DbHelper(ApplicationManager app) {
    // A SessionFactory is set up once for an application!
    this.app = app;
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public List<DbMantisUsers> users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<DbMantisUsers> result = session.createQuery( "from DbMantisUsers" ).list();
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public DbMantisUsers randomUser(List<DbMantisUsers> listOfUsers, int excludeAccessLevel){
    List<DbMantisUsers> filteredUsers = listOfUsers.stream()
            .filter((u)->u.getAccessLevel()!=excludeAccessLevel).collect(Collectors.toList());
    if(filteredUsers.size() == 0) {
      return null;
    }
    Random rand = new Random();
    DbMantisUsers user = filteredUsers.get(rand.nextInt(filteredUsers.size()));
    return user;
  }
}