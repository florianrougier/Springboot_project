package fr.epf.demoseptembre.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * TODO class details.
 *
 *
 * User model. It has a login and a password which are used for the connection
 *
 * @author ROUSSIN ROUGIER DANTY
 *
 */
@Entity
data class User(@Id @GeneratedValue var user_id: Int? = null, var login: String? = null, var password: String? = null)
