package com.bi.recordmanagement.models;

import com.fasterxml.jackson.annotation.JsonView;
//import com.gm.auth2.views.PublisherView;
//import com.gm.auth2.views.OauthUserView.OauthUserBasicView;
//import com.gm.auth2.views.OauthUserView.OauthUserDetailedView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "roles")
@Api(value = "Role of Person in Organization")
@Audited

public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -4086015819365950952L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonView({PublisherView.PublisherBasicView.class,OauthUserDetailedView.class})
    private Long id;

    @ApiModelProperty(notes = "Name of Role")
    @NotBlank(message = "{err.role.name.empty}")
    @Size(min = 2, max = 15, message = "{err.role.name.length}")
//    @JsonView({PublisherView.PublisherBasicView.class,OauthUserBasicView.class,OauthUserDetailedView.class})
    private String name;

    @ApiModelProperty(notes = "Number of Users in Role")
    @NotEmpty(message = "{err.role.users.empty}")
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

//    @JsonView({OauthUserBasicView.class,OauthUserDetailedView.class})
    @ApiModelProperty(notes = "Authorities of Role")
    @NotEmpty(message = "{err.role.authorities.empty}")
    @ManyToMany
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"))
    private Collection<Authority> authorities;
}

