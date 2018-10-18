package fr.epf.demoseptembre.models

import org.hibernate.annotations.Type
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

/**
 * TODO class details.
 *
 * Page Model. It has a title , a 'knot' which represents its place in the story, a text and a story's id which is the page's story
 *
 * @author ROUSSIN ROUGIER DANTY
 *
 */
@Entity
data class Page(@Id @GeneratedValue var page_id: Int? = null, var title: String? = null, @ManyToOne var story: Story? = null,
                var knot: String? = null, @Type(type = "text") var text: String? = null)

