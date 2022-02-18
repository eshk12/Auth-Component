package com.itzikbarabie.moni.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.ArrayUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseEntity {

    @Id
    @Column(name="objectId", insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long objectId;

    @Column(name="createdDate", updatable = false/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    @CreationTimestamp
    private Date createdDate;

    @Column(name="updatedDate"/*, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"*/)
    @UpdateTimestamp
    private Date updatedDate;

    @Column(name="active", columnDefinition = "boolean default false")
    private boolean active;

    public void setObject(Object other) {
        try {
            Field[] fields = new Field[0];
            Class clazz = this.getClass();
            do {
                fields = (Field[]) ArrayUtils.addAll(fields, clazz.getDeclaredFields());
                clazz = clazz.getSuperclass();

            } while (!clazz.equals(Object.class));
            for (Field f : fields) {
                f.setAccessible(true);
                Object value = f.get(other);
                if (value != null) {
                    f.set(this, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
