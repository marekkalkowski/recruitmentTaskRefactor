package pl.opegieka.it.RecruitmentTask.Model;

import org.springframework.stereotype.Component;

@Component
public class PermissionDTO {

    private long cardNumber;
    private String resourceName;


    public PermissionDTO() {
    }

    public PermissionDTO(long cardNumber, String resourceName) {
        this.cardNumber = cardNumber;
        this.resourceName = resourceName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

}
