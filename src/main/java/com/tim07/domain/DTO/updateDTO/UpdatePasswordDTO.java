package com.tim07.domain.DTO.updateDTO;

import java.io.Serializable;

/**
 * Created by Ivana Zeljkovic on 16-Apr-17.
 */
public class UpdatePasswordDTO implements Serializable {
    private String oldPassword;

    private String newPassword;

    public UpdatePasswordDTO() { }

    public UpdatePasswordDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
