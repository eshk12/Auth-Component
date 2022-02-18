package com.itzikbarabie.moni.Model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public abstract class BaseEntityDto {

    private long objectId;
    private Date createdDate;
    private Date updatedDate;
    private boolean active;

}
