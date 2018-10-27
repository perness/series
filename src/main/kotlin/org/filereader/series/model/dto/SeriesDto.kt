package org.filereader.series.model.dto

import io.swagger.annotations.ApiModelProperty

data class SeriesDto
(
        @ApiModelProperty("The name of the series")
        var seriesId: String? = null,

        @ApiModelProperty("The air date of the series")
        var date: Int? = null,

        @ApiModelProperty("The network that aired the series")
        var screen: String? = null,

        @ApiModelProperty("The number of views")
        var views: Int? = null,

        @ApiModelProperty("The series id")
        var id: String? = null
)