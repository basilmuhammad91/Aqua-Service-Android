package com.example.aqua;

public class Service {
    public String ServiceListsId, UserId, ServiceId, Address, full_name, service_name, phone, territory_id, territory_name;

    public Service() {

    }

    public Service(String serviceListsId, String userId, String serviceId, String address, String full_name, String service_name, String phone, String territory_id, String territory_name) {
        this.ServiceListsId = serviceListsId;
        this.UserId = userId;
        this.ServiceId = serviceId;
        this.Address = address;
        this.full_name = full_name;
        this.service_name = service_name;
        this.phone = phone;
        this.territory_id = territory_id;
        this.territory_name = territory_name;
    }

    public String getServiceListsId() {
        return ServiceListsId;
    }

    public void setServiceListsId(String serviceListsId) {
        ServiceListsId = serviceListsId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getServiceId() {
        return ServiceId;
    }

    public void setServiceId(String serviceId) {
        ServiceId = serviceId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTerritory_id() {
        return territory_id;
    }

    public void setTerritory_id(String territory_id) {
        this.territory_id = territory_id;
    }

    public String getTerritory_name() {
        return territory_name;
    }

    public void setTerritory_name(String territory_name) {
        this.territory_name = territory_name;
    }
}
