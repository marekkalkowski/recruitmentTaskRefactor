package pl.opegieka.it.RecruitmentTask.Model;

import org.springframework.stereotype.Component;

@Component
public class PermissionDTO {

    private int cardNumber;
    private String resourceName;
    private PermissionStatus permissionStatus;


    public PermissionDTO() {
    }

    public PermissionDTO(int cardNumber, String resourceName) {
        this.cardNumber = cardNumber;
        this.resourceName = resourceName;
    }

    public PermissionDTO(int cardNumber, String resourceName, PermissionStatus permissionStatus) {
        this.cardNumber = cardNumber;
        this.resourceName = resourceName;
        this.permissionStatus = permissionStatus;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public PermissionStatus getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(PermissionStatus permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}
