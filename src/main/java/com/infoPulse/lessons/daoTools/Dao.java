package com.infoPulse.lessons.daoTools;

import com.infoPulse.lessons.classesForTable.*;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class Dao {

    // Fields

    @PersistenceContext
    private EntityManager entityManager;




    // Methods
    public <T, K> T findEntityForID(Class<T> aClass, K key) {
//        entityManager.getTransaction().begin();
        T entity = null;
        entity = entityManager.find(aClass, key);
        System.out.println();
//        entityManager.getTransaction().commit();
        return entity;
    }

    @Transactional
    public <T> void addEntity(T entity) {
//        entityManager.getTransaction().begin();
        entityManager.persist(entity);
//        entityManager.getTransaction().commit();
    }

public <T> List<T> findAllEntity(Class<T> aClass, String strClass) {
//        entityManager.getTransaction().begin();
        List<T> tList = entityManager.createQuery("from " + strClass).getResultList();
//        entityManager.getTransaction().commit();
        return tList;
    }

    public <T,K> boolean isEntityExist(Class<T> aClass, K key) {
//        entityManager.getTransaction().begin();
        T entity = null;
        entity = entityManager.find(aClass, key);
        if (entity == null) {
//            entityManager.getTransaction().commit();
            return false;
        }
//        entityManager.getTransaction().commit();
        return true;
    }


    public boolean isUserLoginExist(String login) {
//        entityManager.getTransaction().begin();
        List<User> userList = entityManager.createQuery("SELECT u " +
                        " FROM User u " +
                "WHERE u.login = :login"
        , User.class)
        .setParameter("login", login)
        .getResultList();
        if (userList.isEmpty()) {
//            entityManager.getTransaction().commit();
            return false;
        }
//        entityManager.getTransaction().commit();
        return true;
    }

//    @Transactional
    public User findUserByLogin(String login) {
//        entityManager.getTransaction().begin();
        User entity = null;
        entity = entityManager.createQuery("SELECT u " +
                        " FROM User u " +
                        "WHERE u.login = :login"
                , User.class)
                .setParameter("login", login)
                .getSingleResult();
        entity.getCustomerList();
//        Hibernate.initialize(entity.getCustomerList());
        System.out.println();
//        entityManager.getTransaction().commit();
        return entity;
    }


    public boolean isCustomerExist(String phoneNumber) {
//        entityManager.getTransaction().begin();
        List<Customer> customerList = entityManager.createQuery("SELECT cu " +
                        " FROM Customer cu " +
                        "WHERE cu.phoneNumber = :phoneNumber"
                , Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
        if (customerList.isEmpty()) {
//            entityManager.getTransaction().commit();
            return false;
        }
//        entityManager.getTransaction().commit();
        return true;
    }


    public Customer findCustomerByPhoneNumber(String phoneNumber) {
//        entityManager.getTransaction().begin();
        List<Customer> customerList = entityManager.createQuery(" FROM Customer cu " +
                        "WHERE cu.phoneNumber = :phoneNumber"
                , Customer.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
//        System.out.println();
//        entityManager.getTransaction().commit();
        return customerList.get(0);
    }


    public List<Customer> findCustomersForUser(String userLogin) {
        List<Customer> customerList = entityManager.createQuery("SELECT cu FROM Customer cu " +
                        "JOIN User u ON u.id = cu.user.id " +
                        "WHERE u.login = :userLogin"
                , Customer.class)
                .setParameter("userLogin", userLogin)
                .getResultList();
        return customerList;
    }


    public List<Customer> findCustomersLikePhoneNumber(String phoneNumber) {
//        entityManager.getTransaction().begin();
        List<Customer> customerList = entityManager.createQuery(" FROM Customer cu " +
                        "WHERE cu.phoneNumber LIKE :phoneNumber"
                , Customer.class)
                .setParameter("phoneNumber", "%" + phoneNumber + "%")
                .getResultList();
        System.out.println();
//        entityManager.getTransaction().commit();
        return customerList;
    }


    public CustomerStatus findCustomerStatusByName(String name) {
//        entityManager.getTransaction().begin();
        CustomerStatus entity = null;
        entity = entityManager.createQuery("SELECT cust " +
                        " FROM CustomerStatus cust " +
                        "WHERE cust.name = :name"
                , CustomerStatus.class)
                .setParameter("name", name)
                .getSingleResult();
        System.out.println();
//        entityManager.getTransaction().commit();
        return entity;
    }


    public boolean isServiceExist(String name) {
//        entityManager.getTransaction().begin();
        List<Service> serviceList = entityManager.createQuery("SELECT se " +
                        " FROM Service se " +
                        "WHERE se.name = :name"
                , Service.class)
                .setParameter("name", name)
                .getResultList();
        if (serviceList.isEmpty()) {
//            entityManager.getTransaction().commit();
            return false;
        }
//        entityManager.getTransaction().commit();
        return true;
    }




//    public List<Customer> getCustomersForUser() {
//        entityManager.getTransaction().begin();
//
//        Query query = entityManager.createQuery("SELECT cu FROM Customer cu " + "WHERE cu.phoneNumber = :phone_number", Customer.class);
//        query.setParameter("phone_number", phoneNumber);
//        Customer otherCustomer = (Customer) query.getResultList().get(0);
//
//        entityManager.getTransaction().commit();
//    }

    public Customer getCustomerForNumber(String phoneNumber) {
//        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT cu FROM Customer cu " + "WHERE cu.phoneNumber = :phone_number", Customer.class);
        query.setParameter("phone_number", phoneNumber);
        Customer otherCustomer = (Customer) query.getResultList().get(0);

//        entityManager.getTransaction().commit();
        return otherCustomer;
    }


//    public Customer getCustomerForNumberCriteria(String phoneNumber) {
//        entityManager.getTransaction().begin();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
//        Root<Customer> root = criteriaQuery.from(Customer.class);
////        criteriaQuery.select(root.get(jpql.Customer_.name));
//        criteriaQuery.where(criteriaBuilder.equal(root.get(com.infoPulse.lessons.classesForTable.Customer_.phoneNumber), phoneNumber));
//        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery);
//
//        Customer otherCustomer = typedQuery.getResultList().get(0);
//
//        entityManager.getTransaction().commit();
//        return otherCustomer;
//    }


    public Service getServiceForName(String name) {
//        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT se FROM Service se " + "WHERE se.name = :servise_name", Service.class);
        query.setParameter("servise_name", name);
        Service service = (Service) query.getResultList().get(0);

//        entityManager.getTransaction().commit();
        return service;
    }

    public void getServicesForCustomerTest(Customer customer) {
//        entityManager.getTransaction().begin();

        List<Object[]> objects = entityManager.createQuery("SELECT cu.phoneNumber, se.name  FROM Customer cu " +
                "JOIN cu.customerServiceList csl ON cu.phoneNumber = csl.customer.phoneNumber " +
                "JOIN Service se ON csl.service.name = se.name " +
                "WHERE cu.phoneNumber = :phone_number ", Object[].class).
                setParameter("phone_number", customer.getPhoneNumber()).getResultList();
//        query.setParameter("customer_id", customer.getCustomer_id());
//        List<Object[]> objects = query.getResultList();

        for (Object[] obj : objects) {
            System.out.println(obj[0].toString() + "|" + obj[1].toString());
        }

//        entityManager.getTransaction().commit();
    }

    public List<Service> getServicesForCustomer(Customer customer) {
//        entityManager.getTransaction().begin();

        List<Service> serviceList = entityManager.createQuery("SELECT se FROM Customer cu " +
                "JOIN cu.customerServiceList csl ON cu.phoneNumber = csl.customer.phoneNumber " +
                "JOIN Service se ON csl.service.name = se.name " +
                "WHERE cu.phoneNumber = :phone_number ", Service.class).
                setParameter("phone_number", customer.getPhoneNumber()).getResultList();

//        entityManager.getTransaction().commit();
        return serviceList;
    }

    public List<Event> getEventsForCustomer(Customer customer, String startDate, String endDate) {
//        entityManager.getTransaction().begin();

        List<Event> eventList = entityManager.createQuery("SELECT ev FROM Event ev " +
//                "JOIN cu.customerServicesList csl ON cu.customer_id = csl.customer.customer_id " +
//                "JOIN Service se ON csl.service.id = se.id " +
                "WHERE ev.customer.phoneNumber = :phone_number " +
                "AND ev.date " +
                "BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s') ",
                Event.class).
                setParameter("phone_number", customer.getPhoneNumber()).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                getResultList();

//        entityManager.getTransaction().commit();
        return eventList;
    }


    public float getAmountForPeriodForCustomer(Customer customer, String startDate, String endDate) {
//        entityManager.getTransaction().begin();

        Object obj = entityManager.createQuery("SELECT SUM(ev.cost) FROM Event ev " +
                        "WHERE ev.customer.phoneNumber = :phone_number " +
                        "AND ev.date " +
                        "BETWEEN STR_TO_DATE(:startDate, '%Y-%m-%d %H:%i:%s') AND  STR_TO_DATE(:endDate, '%Y-%m-%d %H:%i:%s') ",
                Object.class).
                setParameter("phone_number", customer.getPhoneNumber()).
                setParameter("startDate", startDate).
                setParameter("endDate", endDate).
                getResultList().get(0);

//        System.out.println(obj.toString());

//        entityManager.getTransaction().commit();
        return Float.valueOf(obj.toString());
    }



//    public float getAmountForPeriodForCustomerCriteria(Customer customer, String startDate, String endDate) {
//
//        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
//        Date parsingStartDate = null;
//        Date parsingEndDate = null;
//        try {
//            parsingStartDate = ft.parse(startDate);
//            parsingEndDate = ft.parse(endDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        System.out.println("11111111111111111" + parsingStartDate);
////        System.out.println("22222222222222222" + parsingEndDate);
//
//        entityManager.getTransaction().begin();
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Float> criteriaQuery = criteriaBuilder.createQuery(Float.class);
//        Root<Event> root = criteriaQuery.from(Event.class);
//        Path<Date> datePath = root.get(com.infoPulse.lessons.classesForTable.Event_.date);
//        Path<Float> costPath = root.get(com.infoPulse.lessons.classesForTable.Event_.cost);
//        criteriaQuery.select(criteriaBuilder.sum(costPath));
//
//        Predicate predicate = criteriaBuilder.and(
//                criteriaBuilder.equal(root.get(com.infoPulse.lessons.classesForTable.Event_.customer), customer),
//                criteriaBuilder.between(root.get(com.infoPulse.lessons.classesForTable.Event_.date), parsingStartDate, parsingEndDate));
//
//        criteriaQuery.where(predicate);
//
//        float amount = Float.valueOf(entityManager.createQuery(criteriaQuery).getResultList().get(0));
//
//        entityManager.getTransaction().commit();
//        return amount;
//    }



    public ServiceStatus getServiceStatusForName(String name) {
//        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT ss FROM ServiceStatus ss " + "WHERE ss.name = :service_status_name", ServiceStatus.class);
        query.setParameter("service_status_name", name);
        ServiceStatus serviceStatus = (ServiceStatus) query.getResultList().get(0);

//        entityManager.getTransaction().commit();
        return serviceStatus;
    }


    public CustomerService getCustomerServicesByPhoneNumberAndServiceName(String phoneNumber, String serviceName) {
//        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT cs FROM CustomerService cs " +
                "JOIN Customer cu ON cs.customer.id = cu.id " +
                "JOIN Service se ON cs.service.id = se.id AND cu.phoneNumber = :phoneNumber " +
                "AND se.name = :serviceName", CustomerService.class);
        query.setParameter("phoneNumber", phoneNumber);
        query.setParameter("serviceName", serviceName);
        CustomerService customerService = (CustomerService) query.getResultList().get(0);

//        entityManager.getTransaction().commit();
        return customerService;
    }


    @Transactional
    public <T> void updateEntity(T entity) {
//        entityManager.getTransaction().begin();
        entityManager.merge(entity);
//        entityManager.persist(entity);
//        entityManager.getTransaction().commit();
    }


//    @Transactional
    public <T> void deleteEntity(T entity) {
//        entityManager.getTransaction().begin();

//        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.remove(entity);
//        entityManager.getTransaction().commit();
    }

    // Old version to close
//    public void endDao() {
//        entityManager.close();
//        sessionFactory.close();
//    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
