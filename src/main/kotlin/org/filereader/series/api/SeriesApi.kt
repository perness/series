package org.filereader.series.api

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.filereader.series.model.converter.SeriesConverter
import org.filereader.series.model.dto.SeriesDto
import org.filereader.series.model.repository.SeriesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

const val ID_PARAM = "The numeric id of the series"
const val BASE_JSON = "application/json;charset=UTF-8"

@Api(value = "/series", description = "Handling of creating and retrieving series")
@RequestMapping(
        path = ["/series"], // when the url is "<base>/news", then this class will be used to handle it
        produces = [BASE_JSON]
)
@RestController
class SeriesApi
{
    @Autowired
    private lateinit var crud: SeriesRepository

    @Value("\${server.servlet.context-path}")
    private lateinit var contextPath: String

    @ApiOperation("Get all series")
    @GetMapping
    fun get(@ApiParam("The name of the series")
            @RequestParam("seriesId", required = false)
            seriesId: String?,

            @ApiParam("The date value")
            @RequestParam("date", required = false)
            date: Int?) : ResponseEntity<List<SeriesDto>>
    {
        val list = if (seriesId.isNullOrBlank() && date == null)
        {
            crud.findAll()
        }
        else if (!seriesId.isNullOrBlank() && date != null)
        {
            crud.findAllBySeriesIdAndDate(seriesId!!, date)
        }
        else if (!seriesId.isNullOrBlank())
        {
            crud.findAllBySeriesId(seriesId!!)
        }
        else
        {
            crud.findAllByViews(date!!)
        }
        return ResponseEntity.ok(SeriesConverter.transform(list))
    }

    @ApiOperation("Get most popular series")
    @GetMapping("/mostpopular")
    fun getMostPopularSeries() : ResponseEntity<SeriesDto>
    {
        val list = crud.getSeriesIdWithMostViews()

        return ResponseEntity.ok(SeriesConverter.transform(list[0]))
    }

    @ApiOperation("List all series seen on tv")
    @GetMapping("/seenontv")
    fun getSeriesSeenOnTv() : ResponseEntity<List<SeriesDto>>
    {
        val list = crud.findAllByScreen("tv")

        return ResponseEntity.ok(SeriesConverter.transform(list))
    }

    @ApiOperation("List all series and views")
    @GetMapping("/seriesandviews")
    fun getSeriesAndViews() : ResponseEntity<List<Pair<String, Int>>>
    {
        val seriesList = crud.getAllSeriesId()
        val viewsList = crud.getAllViews()
        val combinedList = seriesList.zip(viewsList)

        return ResponseEntity.ok(combinedList)
    }

    @ApiOperation("List all series and views sorted by date")
    @GetMapping("/seriesandviewssorted")
    fun getSeriesAndViewsSorted() : ResponseEntity<List<SeriesDto>>
    {
        val list = crud.getAllOrderByDate()

        return ResponseEntity.ok(SeriesConverter.transform(list))
    }

}





















