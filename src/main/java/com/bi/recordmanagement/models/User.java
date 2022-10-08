package com.bi.recordmanagement.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.bi.recordmanagement.views.AuditableViews.AuditableBasicView;
import com.bi.recordmanagement.views.OauthUserView;
import com.bi.recordmanagement.views.OauthUserView.OauthUserBasicView;
import com.bi.recordmanagement.views.OauthUserView.OauthUserDetailedView;
import com.bi.recordmanagement.views.PublisherView.AuditableView;
import com.bi.recordmanagement.views.PublisherView.PublisherBasicView;
import com.bi.recordmanagement.views.UserBasicView;
import com.bi.recordmanagement.views.UserViews;
import com.bi.recordmanagement.views.UserViews.UserPasswordResetView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
//import com.gm.auth2.services.Searchable;
//import com.gm.auth2.validator.CustomConstraint;
//import com.gm.auth2.views.AuditableViews.AuditableBasicView;
//import com.gm.auth2.views.OauthUserView;
//import com.gm.auth2.views.OauthUserView.OauthUserBasicView;
//import com.gm.auth2.views.OauthUserView.OauthUserDetailedView;
//import com.gm.auth2.views.PublisherView.AuditableView;
//import com.gm.auth2.views.PublisherView.PublisherBasicView;
//import com.gm.auth2.views.TokenViews.TokenBasicView;
//import com.gm.auth2.views.UserViews;
//import com.gm.auth2.views.UserViews.UserBasicView;
//import com.gm.auth2.views.UserViews.UserGetView;
//import com.gm.auth2.views.UserViews.UserPasswordResetView;
//import com.gm.auth2.model.Role;
//import com.gm.auth2.model.Speciality;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Api(value = "User Details")
@ApiModel(value = "name", description = "desc", reference = "ref")
@Table(name = "users", uniqueConstraints =
{
@UniqueConstraint(columnNames = {"email","clientId","isDeleted"}, name = "user_email_clientId_isDeleted_unique")
,@UniqueConstraint(columnNames = {"loginName","clientId","isDeleted"}, name = "user_loginName_client_id_is_deleted")
})
@Audited
//@CustomConstraint(message ="err.user.email.phone.not.found")
//@CustomOtpConstraint(message = "err.otp.phone.email.empty")
@Where(clause = "is_deleted = 0")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 7823628616613823732L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
//    @JsonView({AuditableView.class, UserBasicView.class,UserViews.UserRegisterView.class,OauthUserBasicView.class,OauthUserDetailedView.class,TokenBasicView.class})
    private Long id;

    @NotEmpty(message = "{err.user.phone.empty}", groups = {UserPasswordResetView.class, UserBasicView.class})
    @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    @Searchable
    private String loginName;

    @JsonView({AuditableBasicView.class})
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate = new Date();
    
    @JsonView({AuditableBasicView.class,UserBasicView.class,UserViews.UserRegisterView.class})
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOnDate;
    
    
    @JsonView({PublisherBasicView.class, UserBasicView.class,UserViews.UserRegisterView.class,OauthUserDetailedView.class})
    private String firstName;
    
    @JsonView({PublisherBasicView.class, UserBasicView.class,UserViews.UserRegisterView.class,OauthUserDetailedView.class})
    private String lastName;
    
    // TODO digit validation
    @JsonView({PublisherBasicView.class, UserBasicView.class,UserViews.UserRegisterView.class,OauthUserDetailedView.class, UserViews.UserRegisterWithTokenView.class})
    @Email(message = "{err.user.valid.email}")
    private String email;
    

    @JsonView({UserViews.UserRegisterView.class, UserViews.UserRegisterWithTokenView.class})
    @NotBlank(message = "{err.client.empty}", groups = {UserPasswordResetView.class, UserBasicView.class, UserViews.UserRegisterWithTokenView.class})
    private String clientId;


//    @Getter(onMethod = @__( @JsonIgnore ))
    @NotEmpty(message = "{err.user.password.empty}", groups = {UserViews.UserRegisterView.class, UserViews.UserRegisterWithTokenView.class})
   @JsonView({PublisherBasicView.class, UserBasicView.class,UserViews.UserRegisterView.class, UserViews.UserRegisterWithTokenView.class})
    private String password;
    
   // @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    public boolean isNoPassword() {
    	return StringUtils.isEmpty(this.password);
    }

    @Getter(onMethod = @__(@JsonIgnore))
    @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private boolean accountExpired;

    @Getter(onMethod = @__(@JsonIgnore))
    @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private boolean accountLocked;

    @Getter(onMethod = @__(@JsonIgnore))
    @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private boolean credentialsExpired;

    @Getter(onMethod = @__(@JsonIgnore))
    @JsonView({PublisherBasicView.class, UserBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private boolean enabled;

    @JsonView({AuditableView.class, UserBasicView.class, OauthUserBasicView.class,OauthUserDetailedView.class})
    private long isDeleted;

    @Getter(onMethod = @__(@JsonIgnore))
    @Transient
    @JsonView(OauthUserView.class)
    private Collection<? extends GrantedAuthority> authorities;


    @JsonView({PublisherBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"), foreignKey = @ForeignKey(name = "fk_role_user", value = ConstraintMode.CONSTRAINT))
    private Collection<Role> roles;

    @Transient
    public Collection<String> allAuthorities;
    
    private Long attempts;

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return !isAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return !isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return !isCredentialsExpired();
    }


}


