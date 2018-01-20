package com.infoPulse.lessons.controllers;


import com.infoPulse.lessons.daoTools.Dao;
import com.infoPulse.lessons.classesForTable.*;
import com.infoPulse.lessons.dto.CustomerServiceDto;
import com.infoPulse.lessons.dto.CustomerServicesDto;
import com.infoPulse.lessons.dto.ServiceDto;
import com.infoPulse.lessons.dto.ServicesDto;
import com.infoPulse.lessons.service.CustomerServiceImpl;
import com.infoPulse.lessons.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@SessionAttributes (types = User.class)
public class MyController {

    @Autowired
    Dao dao;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    HttpSession httpSession;
    @Autowired
    FormUserValidator formUserValidator;
    @Autowired
    LoginUserValidator loginUserValidator;
    @Autowired
    NewServiceValidator newServiceValidator;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
//        System.out.println(myUser);
//        if (myUser == null) {
//            modelAndView.setViewName("/WEB-INF/index.jsp");
//        } else {
//            modelAndView.setViewName("enterssful");
//        }

        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("registeredUser", user);
        return "registrationform";
    }


    @RequestMapping(value = "/userregistration", method = RequestMethod.POST)
    public ModelAndView doValidationRegistration(
            @ModelAttribute(name = "registeredUser")
            @Validated
                   User myUser
            ,
            BindingResult bindingResult){
//        System.out.println(myUser);
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registrationform");
        } else {
            modelAndView.setViewName("confirmation");
        }
        return modelAndView;
    }


    @InitBinder ("registeredUser")
    protected void regValidator(WebDataBinder binder) {
        // bind validator to controller
        binder.setValidator(this.formUserValidator);
    }


    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String getConfirmation(SessionStatus sessionStatus) {
        dao.addEntity((User) httpSession.getAttribute("registeredUser"));
        User user2 =(User) httpSession.getAttribute("registeredUser");
        System.out.println("getConfirmation:" + user2);

        sessionStatus.setComplete();

        return "regssful";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getloginForm(Model model) {
               User user = new User();
               model.addAttribute("loginUser", user);
               return "loginform";
    }


    @RequestMapping(value = "/userlogin", method = RequestMethod.POST)
    public ModelAndView doLogin(
//            Model model,
            ModelAndView modelAndViewDoLogin ,
            @ModelAttribute(name = "loginUser")
            @Validated
            User myUser,
            BindingResult bindingResult) {


//        ModelAndView modelAndViewDoLogin = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndViewDoLogin.setViewName("loginform");
        } else {
//               User userFromDB = dao.findEntityForID(User.class, myUser.getLogin());
               User userFromDB = dao.findUserByLogin(myUser.getLogin());
        System.out.println(userFromDB);
            System.out.println(userFromDB.getCustomerList());
            List<Customer> customerList = userFromDB.getCustomerList();
//            model.addAttribute("customerList", customerList);
            modelAndViewDoLogin.addObject("customerList", customerList);
            Customer customer = new Customer();
//            model.addAttribute("loginUser", userFromDB);
            modelAndViewDoLogin.addObject("loginUser", userFromDB);
//            model.addAttribute("selectedCustomer", customer);
            modelAndViewDoLogin.addObject("selectedCustomer", customer);
            modelAndViewDoLogin.setViewName("loginhome");
        }

//        if (!userFromDB.getPassword().equals(myUser.getPassword())) {
//            modelAndViewDoLogin.setViewName("login");
//        }else {
//            System.out.println(userFromDB.getCustomerList());
//            List<Customer> customerList = userFromDB.getCustomerList();
//            model.addAttribute("customerList", customerList);
//            modelAndViewDoLogin.setViewName("enterssful");
//        }
        return modelAndViewDoLogin;
    }

    @InitBinder ("loginUser")
    protected void loginValidator(WebDataBinder binder) {
        // bind validator to controller
        binder.setValidator(this.loginUserValidator);
    }


    @RequestMapping(value = "/userlogin", method = RequestMethod.GET)
    public ModelAndView doLoginG(
            Model model,
            @ModelAttribute(name = "loginUser")
                    User myUser
    ) {

        System.out.println(myUser.getLogin());

        ModelAndView modelAndViewDoLogin = new ModelAndView();

//        List<Customer> customerList = myUser.getCustomerList();
        List<Customer> customerList = dao.findCustomersForUser(myUser.getLogin());
        model.addAttribute("customerList", customerList);

        Customer customer = new Customer();
        model.addAttribute("selectedCustomer", customer);
        modelAndViewDoLogin.setViewName("loginhome");

        return modelAndViewDoLogin;
    }


    @RequestMapping (value = "/logout", method = RequestMethod.GET)
    public String getLogout(SessionStatus sessionStatus) {
                sessionStatus.setComplete();
                return "logout";
    }


    @RequestMapping (value = "/customer/{phonenumber}", method = RequestMethod.GET)
    public String getCustomerRoom(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,Model model
//            ,@ModelAttribute(name = "selectedCustomer")
//                    Customer selCustomer
    ) {
        System.out.println("Come /customer/{phonenumber}");
//        System.out.println(selCustomer);
        System.out.println(phoneNumber);
//                Customer myCustomer = dao.findEntityForID(Customer.class, selCustomer.getPhoneNumber());
        // TODO Dao method "findCustomerByPhoneNumber"
//                Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
                Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
                model.addAttribute("selectedCustomer", myCustomer);
                List<Service> serviceList = dao.findAllEntity(Service.class, "Service");
                model.addAttribute("serviceList", serviceList);
        System.out.println(myCustomer);
        return "customers/customerroom";
    }


    @RequestMapping (value = "customer/customerservices/show", headers = {"Accept=application/xml"})
    public ResponseEntity<CustomerServicesDto> getServicesForCustomer(
            @RequestParam String selectedPhoneNumber
    ) {
                Customer selectedCustomer = dao.findCustomerByPhoneNumber(selectedPhoneNumber);
        List<CustomerService> customerServiceList = selectedCustomer.getCustomerServiceList();
        List<CustomerServiceDto> customerServiceDtoList = new LinkedList<>();
        for (CustomerService customerService : customerServiceList) {
//            System.out.println(service.getCustomerServiceList().size() + "|" + service.getEventList());
            customerServiceDtoList.add(new CustomerServiceDto(customerService));
        }
        System.out.println(customerServiceList);
        System.out.println(customerServiceDtoList);
        return new ResponseEntity<>(new CustomerServicesDto(customerServiceDtoList), HttpStatus.OK);
    }


    @RequestMapping (value = "customer/customerservices/add", method = RequestMethod.GET)
    public ResponseEntity<String> getServicesForCustomer(
            @RequestParam String selectedPhoneNumber
            ,@RequestParam String selectedServiceName
    ) {
        customerServiceImpl.addCustomerService(selectedPhoneNumber, selectedServiceName);
        return new ResponseEntity<>("Fine", HttpStatus.OK);
    }


    @RequestMapping (value = "/customerservice/{phonenumber}/{servicename}/delete", method = RequestMethod.GET, headers = {"Accept=application/xml"})
    public ResponseEntity<String> doDeleteCustomerService(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
//            ,Model model
    ) {
//        System.out.println("/customerservice/{phonenumber}/{servicename}/delete: " + phoneNumber + "|" + serviceName);
//        CustomerService customerService = dao.getCustomerServicesByPhoneNumberAndServiceName(phoneNumber, serviceName);
//        System.out.println("Delete CustomerService id: " + customerService.getId());
//        dao.deleteEntity(customerService);
//
//        Customer customer = dao.findCustomerByPhoneNumber(phoneNumber);
//        Service service = dao.getServiceForName(serviceName);
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        customer.removeService(service);
//        dao.updateEntity(customer);
//        dao.updateEntity(service);
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());

        customerServiceImpl.deleteCustomerService(phoneNumber, serviceName);


//        // TODO Dao method "findCustomerByPhoneNumber"
////        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
//        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
//        model.addAttribute("customerForm", myCustomer);
//        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
//        return "customers/customerform";
        return new ResponseEntity<>( HttpStatus.OK);
    }








    @RequestMapping (value = "/customer/{phonenumber}/update", method = RequestMethod.GET)
    public String showUpdateCustomerForm(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,Model model
    ) {
        System.out.println("customer/update(phoneNumber): " + phoneNumber);
        // TODO Dao method "findCustomerByPhoneNumber"
//        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
        model.addAttribute("customerForm", myCustomer);
        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
        List<CustomerStatus> customerStatusList = dao.findAllEntity(CustomerStatus.class, "CustomerStatus");
        model.addAttribute("customerStatusList", customerStatusList);
        return "customers/customerform";
    }


    @RequestMapping (value = "/customer/{phonenumber}/delete", method = RequestMethod.GET)
    public String doDeleteCustomer(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,Model model
    ) {
        System.out.println("customer/delete(phoneNumber): " + phoneNumber);

        User user = (User) httpSession.getAttribute("loginUser");
        System.out.println("User Login: " + user.getLogin());

//        boolean resultDel = userServiceImpl.deleteCustomer(user, phoneNumber);
        boolean resultDel = userServiceImpl.deleteCustomer2(user, phoneNumber);
        System.out.println(resultDel);

        // TODO Dao method "findCustomerByPhoneNumber"
////        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
//        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
//        dao.deleteEntity(myCustomer);



        return "loginhome";
    }


    // save or update customer
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(
            Model model
            , RedirectAttributes redirectAttributes
            ,@ModelAttribute(name = "customerForm")
            Customer addCustomer
            ,@ModelAttribute(name = "loginUser")
            User user
            ) {
        System.out.println("COME :/customers");
        System.out.println(addCustomer.getUser());
        System.out.println(addCustomer.getCustomerStatus());

                String userLoginForSet = addCustomer.getUser().getLogin();
        System.out.println(addCustomer.getPhoneNumber());
        System.out.println(userLoginForSet);

        // TODO Dao method "findUserByLogin"
//        User userForSet = dao.findEntityForID(User.class, userLoginForSet);
//        User userForSet = dao.findUserByLogin(userLoginForSet);
//        boolean isCustomerExists = dao.isEntityExist(Customer.class, addCustomer.getPhoneNumber());
        boolean isCustomerExists = dao.isCustomerExist(addCustomer.getPhoneNumber());
        System.out.println(isCustomerExists);
        if (isCustomerExists) {
            System.out.println("isCustomerExists true" + isCustomerExists);
//            errors.rejectValue("login", "error.loginExists");
//            userService.updateCustomer(user, addCustomer);
//            dao.updateEntity(addCustomer);
            customerServiceImpl.updateCustomer(addCustomer.getUser().getLogin(), addCustomer.getCustomerStatus().getName(), addCustomer);
            redirectAttributes.addFlashAttribute("message", "Customer update successfully!");
//            addCustomer.setUser(userForSet);
        } else {
            System.out.println("isCustomerExists false" + isCustomerExists);
            userServiceImpl.addCustomer(addCustomer.getUser().getLogin(), addCustomer.getCustomerStatus().getName(), addCustomer);
            redirectAttributes.addFlashAttribute("message", "Customer added successfully!");
//            System.out.println(userForSet.getLogin());
//            addCustomer.setUser(userForSet);
//            dao.addEntity(addCustomer);
        }
        System.out.println("END :/customers");
        return "redirect:/customer/" + addCustomer.getPhoneNumber();
    }


    // Show add customer form
    @RequestMapping(value = "/customers/add", method = RequestMethod.GET)
    public String showAddCustomerForm(Model model) {
                Customer addCustomer = new Customer();
                model.addAttribute("customerForm", addCustomer);
                List<CustomerStatus> customerStatusList = dao.findAllEntity(CustomerStatus.class, "CustomerStatus");
                model.addAttribute("customerStatusList", customerStatusList);
                return "customers/customerform";
    }


    // Show find customer form
    @RequestMapping (value = "/customers/find", method = RequestMethod.GET)
    public String showFindCustomerForm(Model model) {
//                List<Customer> findCustomerList = new LinkedList<>();
//                model.addAttribute("findCustomerList", findCustomerList);
                return "customers/findcustomerform";
    }

    // Find customers
    @RequestMapping (value = "/findcustomer", method = RequestMethod.GET)
    public String doFindCustomer(
            Model model,
//            @ModelAttribute(name = "findCustomerPhoneNumber")
            @RequestParam(name = "findCustomerPhoneNumber")
                    String findCustomerPhoneNumber
//            @ModelAttribute(name = "findCustomerList")
//                    List<Customer> findCustomerList
    ) {
        System.out.println("findCustomerPhoneNumber: " + findCustomerPhoneNumber);
//        Customer findCustomer = dao.findEntityForID(Customer.class, findCustomerPhoneNumber);
        List<Customer> findCustomerList = dao.findCustomersLikePhoneNumber(findCustomerPhoneNumber);
//        System.out.println(findCustomerList);
//        List<Customer> findCustomerList = new LinkedList<>();
//        findCustomerList.add(findCustomer);

        Customer selCustomer = new Customer();
        model.addAttribute("selectedCustomer", selCustomer);
        model.addAttribute("findCustomerList", findCustomerList);
        return "customers/findcustomerssful";
    }


    // Show new service form
    @RequestMapping(value = "/services/create", method = RequestMethod.GET)
    public String showNewServiceForm(Model model) {
        Service newService = new Service();
        model.addAttribute("serviceForm", newService);
        return "services/serviceform";
    }


    // save new service
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ModelAndView saveNewService(
            Model model
            , RedirectAttributes redirectAttributes
            ,@ModelAttribute(name = "serviceForm")
            @Validated
                    Service serviceForm
            ,BindingResult bindingResult
//            ,@ModelAttribute(name = "loginUser")
//                    User user
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("services/serviceform");
        } else {
            dao.addEntity(serviceForm);
            redirectAttributes.addFlashAttribute("message", "Service update successfully!");
            List<Service> serviceList = dao.findAllEntity(Service.class, "Service");
            model.addAttribute("serviceList", serviceList);
            modelAndView.setViewName("services/servicelist");
        }
        return modelAndView;


//        if (isServiceExists) {
////            errors.rejectValue("login", "error.loginExists");
//            redirectAttributes.addFlashAttribute("message", "Service update successfully!");
//            addCustomer.setUser(userForSet);
//            dao.updateEntity(addCustomer);
//        } else {
//            redirectAttributes.addFlashAttribute("message", "Customer added successfully!");
//            System.out.println(userForSet.getLogin());
//            addCustomer.setUser(userForSet);
//            dao.addEntity(addCustomer);
//        }
//        return "redirect:/customer/" + addCustomer.getPhoneNumber();
    }

    @InitBinder ("serviceForm")
    protected void setNewServiceValidatorValidator(WebDataBinder binder) {
        // bind validator to controller
        binder.setValidator(this.newServiceValidator);
    }


    // show services list
    @RequestMapping(value = "/services/list", method = RequestMethod.GET)
    public ModelAndView showServicesList(
            Model model
            , RedirectAttributes redirectAttributes
            ,@ModelAttribute(name = "serviceForm")
            @Validated
                    Service serviceForm
            ,BindingResult bindingResult
            ,@ModelAttribute(name = "loginUser")
                    User user
    ) {
        ModelAndView modelAndView = new ModelAndView();
        List<Service> serviceList = dao.findAllEntity(Service.class, "Service");
        model.addAttribute("serviceList", serviceList);
        modelAndView.setViewName("services/servicelist");
        return modelAndView;
    }








    @RequestMapping(value = "/autocomplete", headers = {"Accept=application/xml"})
//    @ResponseBody
    public ResponseEntity<ServicesDto> getAutoForm(
//            Model model
             @RequestParam(name = "action")
                    String action
            , @RequestParam(name = "search")
                    String search
            ) {

        List<Service> servicesAll = dao.findAllEntity(Service.class, "Service");
        System.out.println(servicesAll);

        List<ServiceDto> serviceDtoList = new LinkedList<>();
        for (Service service : servicesAll) {
            serviceDtoList.add(new ServiceDto(service));
//            System.out.println(service.getCustomerServiceList().size() + "|" + service.getEventList());
        }

        ServicesDto servicesDto = new ServicesDto(serviceDtoList);
        System.out.println("END : " + servicesDto.getServiceList().size());

//        List<Service> searchServiceList = new LinkedList<>();
//        for (Service service : servicesAll) {
//            Service serviceTemp = new Service();
//            serviceTemp.setName(service.getName());
//            serviceTemp.setPayroll(service.getPayroll());
//            searchServiceList.add(serviceTemp);
//        }

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache");
//        return new ResponseEntity<ServicesDto>(new ServicesDto(servicesAll), HttpStatus.OK);
//        return new ResponseEntity<>(new ServicesDto(servicesAll), HttpStatus.OK);
        return new ResponseEntity<>(servicesDto, HttpStatus.OK);
    }
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            mapper.writeValue(new File("file.json"), serviceList.get(0));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        StringBuilder stringBuilder = new StringBuilder();

//        if ("complete".equals(action)) {
//            if (searchString != null) {
//                searchString = searchString.trim().toLowerCase();
//                // a flag whether any products where added

//                // check if user sent not empty string
//                if (!"".equals(searchString)) {

//                    boolean isProductsAdded = false;
//                    for (Service service : serviceList) {
//                        // create XML tags with product information
//                        stringBuilder.append("<service>")
//                                .append("<name>")
//                                .append(service.getName())
//                                .append("</name>")
//                                .append("<payroll>")
//                                .append(service.getPayroll())
//                                .append("</payroll>")
//                                .append("</service>");
//                        isProductsAdded = true;
//                    }
//                }
//            }
//        }


//        if (isProductsAdded) {
//            // warn the HttpResponse that we are going to send XML
////            response.setContentType("text/xml");
//            model.addAttribute("findStingHtml", stringBuilder);
//            // don't cache previous result
////            response.setHeader("Cache-Control", "no-cache");
//            // put all <product> tags into <products>
////            response.getWriter().write("<products>" + sb.toString() + "</products>");
//        } else {
//            //there is nothing to show
////            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        }




//        User user = new User();
//        model.addAttribute("loginUser", user);





    @RequestMapping(value = "getSearchResult", headers = {"Accept=application/xml"})
    public ResponseEntity<String> getSearchResultViaAjax(
            @RequestParam String search
            , @RequestParam String action) {

        List<Service> serviceList = dao.findAllEntity(Service.class, "Service");

        // create StringBuilder to put results of search
        StringBuilder sb = new StringBuilder();

        if ("complete".equals(action)) {
            if (search != null) {
                search = search.trim().toLowerCase();
                // a flag whether any products where added
                boolean isProductsAdded = false;
                // check if user sent not empty string
                if (!"".equals(search)) {

                    for (Service service : serviceList) {
                        if ( // searchString starts with name
                                service.getName().toLowerCase().startsWith(search)
//                                        ||
                                        // searchString starts with category
//                                        product.getCategory().toLowerCase().startsWith(search) ||
                                        // searchString starts with name and category
//                                        product.getName().toLowerCase().concat(" ")
//                                                .concat(product.getCategory().toLowerCase()).startsWith(search)
                        ) {

                            // create XML tags with product information
                            sb.append("<service>")
                                    .append("<name>")
                                    .append(service.getName())
                                    .append("</name>")
                                    .append("<payroll>")
                                    .append(service.getPayroll()                                    )
                                    .append("</payroll>")
//                                    .append("<category>")
//                                    .append(product.getCategory())
//                                    .append("</category>")
//                                    .append("<price>")
//                                    .append(product.getPrice())
//                                    .append("</price>")
                                    .append("</service>");
                            isProductsAdded = true;
                        }
                    }

                    String resp = "<services>" + sb.toString() + "</services>";

//                if (isProductsAdded) {
//                    // warn the HttpResponse that we are going to send XML
//                    response.setContentType("text/xml");
//                    // don't cache previous result
//                    response.setHeader("Cache-Control", "no-cache");
//                    // put all <product> tags into <products>
//                    response.getWriter().write("<products>" + sb.toString() + "</products>");
//                } else {
//                    //there is nothing to show
//                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//                }


                    // TODO    Need to send Service ID also  into jsp and then create a link to activate service via spring form
                    if (search != null) {
//            List<ServicesDTO> servicesDTOList = new ArrayList<>();
//            for (Service service : serviceRepository
//                    .findAllByServiceNameMatchesAndServicePayrollBetween(search.getServiceName()
//                            , search.getServicePayrollFrom()
//                            , search.getServicePayrollTo()
//                            , search.getCustomerId())) {
//                servicesDTOList.add(new ServicesDTO(service.getID(), service.getServiceName(), service.getServicePayroll()));
//            }
                        return new ResponseEntity<>(resp, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }
                }


            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
