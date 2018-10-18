package fr.epf.demoseptembre.models

import org.hibernate.annotations.Type
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * Cette classe utilise Kotlin (parce qu'on est grave des Hipsters).
 * La "data class" représente un "POJO" (plain-old java object), c'est à dire un objet qui n'est destiné qu'à "représenter" une entité.
 * Ici, en l'occurence, un utilisateur.
 * @author Loïc Ortola on 10/09/2018
 */
@Entity
data class Page(@Id @GeneratedValue var page_id: Int? = null, var title: String? = null, @ManyToOne var story: Story? = null,
                var knot: String? = null, @Type(type = "text") var text: String? = null)

