package org.filereader.series.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class SeriesEntity(
        var seriesId: String,
        var date: Int,
        var screen: String,
        var views: Int,
        @get:Id @get:GeneratedValue
        var id: Long? = null
)