package org.filereader.series.model.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.*

@Entity
data class SeriesEntity(
        @get:NotBlank @get:Size(max = 128)
        var seriesId: String,

        @Positive @Max(value = 99999999)
        var date: Int,

        @get:NotBlank @get:Size(max = 64)
        var screen: String,

        @PositiveOrZero
        var views: Int,

        @get:Id @get:GeneratedValue
        var id: Long? = null
)