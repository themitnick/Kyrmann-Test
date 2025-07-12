package com.assure.user_service.entity;

import com.assure.common.entity.AuditableEntity;
import com.assure.common.enums.LanguePreferee;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "utilisateur")
public class Utilisateur extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "utilisateur_id")
    private UUID id;

    @Column(name = "identifiant", length = 64, nullable = false, unique = true)
    private String identifiant;

    @Column(name = "est_verifie", nullable = false)
    private Boolean estVerifie = false;

    @Column(name = "email", length = 256, unique = true)
    private String email;

    @Column(name = "telephone", length = 64, unique = true)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(name = "langue_preferee", length = 10)
    private LanguePreferee languePreferee;

    @Column(name = "date_derniere_connexion")
    private LocalDateTime dateDerniereConnexion;

    @Column(name = "origine_connexion")
    private String origineConnexion;

    @Column(name = "last_login_ip")
    private String lastLoginIp;

    @Column(name = "canal_par_defaut")
    private String canalParDefaut;

    @Column(name = "role_id", nullable = false)
    private UUID roleId;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Boolean getEstVerifie() {
        return estVerifie;
    }

    public void setEstVerifie(Boolean estVerifie) {
        this.estVerifie = estVerifie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public LanguePreferee getLanguePreferee() {
        return languePreferee;
    }

    public void setLanguePreferee(LanguePreferee languePreferee) {
        this.languePreferee = languePreferee;
    }

    public LocalDateTime getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    public void setDateDerniereConnexion(LocalDateTime dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    public String getOrigineConnexion() {
        return origineConnexion;
    }

    public void setOrigineConnexion(String origineConnexion) {
        this.origineConnexion = origineConnexion;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getCanalParDefaut() {
        return canalParDefaut;
    }

    public void setCanalParDefaut(String canalParDefaut) {
        this.canalParDefaut = canalParDefaut;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }
}
