package fr.epf.demoseptembre.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * TODO class details.
 *
 * Story model. It has a name, a summary, an image and a user's id which is the story's author.
 *
 * @author ROUSSIN ROUGIER DANTY
 *
 */
@Entity
data class Story(@Id @GeneratedValue var story_id: Int? = null, var name: String? = null, var summary: String? = null, @ManyToOne var user: User? = null, var img: String? = null)

