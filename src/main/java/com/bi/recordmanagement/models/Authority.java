package com.bi.recordmanagement.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonView;
//import com.gm.auth2.views.OauthUserView.OauthUserBasicView;
//import com.gm.auth2.views.OauthUserView.OauthUserDetailedView;

import io.swagger.annotations.Api;

@Entity
@Table(name = "authorities")
@EntityListeners(AuditingEntityListener.class)
@Api(value = "")
@Audited
/*public class Authority implements GrantedAuthority, Serializable {*/
public class Authority extends Auditable<User> implements GrantedAuthority, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 71979273327636279L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @JsonView({OauthUserBasicView.class,OauthUserDetailedView.class})
    @NotBlank(message = "{err.authority.name.empty}")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Authority other = (Authority) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Authority [name=" + name + "]";
    }
}
