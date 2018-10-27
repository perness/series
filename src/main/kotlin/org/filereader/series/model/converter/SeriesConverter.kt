package org.filereader.series.model.converter

import org.filereader.series.model.dto.SeriesDto
import org.filereader.series.model.entity.SeriesEntity

class SeriesConverter
{
    companion object
    {
        fun transform(entity: SeriesEntity) : SeriesDto
        {
            return SeriesDto(
                    seriesId = entity.seriesId,
                    date = entity.date,
                    screen = entity.screen,
                    views = entity.views,
                    id = entity.id.toString()
            )
        }

        fun transform(entities: Iterable<SeriesEntity>) : List<SeriesDto>
        {
            return entities.map { transform(it) }
        }
    }
}