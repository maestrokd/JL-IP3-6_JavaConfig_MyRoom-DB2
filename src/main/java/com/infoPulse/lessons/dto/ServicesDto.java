package com.infoPulse.lessons.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "services")
public class ServicesDto {

    private List<ServiceDto> serviceList;

    public ServicesDto() {}

    public ServicesDto(List<ServiceDto> serviceList) {
        this.serviceList = serviceList;
        System.out.println("From Constructor: " + this.serviceList);
    }

    @XmlElement(name = "service")
//    @XmlElement
    public List<ServiceDto> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceDto> serviceList) {
        this.serviceList = serviceList;
    }
}
