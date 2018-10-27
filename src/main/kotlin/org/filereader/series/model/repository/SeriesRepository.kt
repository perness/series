package org.filereader.series.model.repository

import org.filereader.series.model.entity.SeriesEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.TypedQuery

@Repository
interface SeriesRepository : CrudRepository<SeriesEntity, Long>, SeriesRepositoryCustom
{
    fun findAllBySeriesIdAndDate(seriesId: String, date: Int) : Iterable<SeriesEntity>

    fun findAllBySeriesId(seriesId: String) : Iterable<SeriesEntity>

    fun findAllByViews(views: Int) : Iterable<SeriesEntity>

    fun findAllByScreen(screen: String) : Iterable<SeriesEntity>
}
//--------------------------------------------------------
@Transactional
interface SeriesRepositoryCustom
{
    fun createSeries(seriesId: String, date: Int, screen: String, views: Int) : Long

    fun getAllSeriesId() : List<String>

    fun getAllViews() : List<Int>

    fun getAllOrderByDate() : List<SeriesEntity>

    fun getAllSeriesAndViewsOrderedByDate() : List<Pair<String, Int>>

    fun getAllSeriesIdSeenOnTv() : List<SeriesEntity>

    fun getSeriesIdWithMostViews() : List<SeriesEntity>
}
//--------------------------------------------------------
@Repository
@Transactional
class SeriesRepositoryImpl : SeriesRepositoryCustom
{
    @Autowired
    private lateinit var em: EntityManager

    override fun createSeries(seriesId: String, date: Int, screen: String, views: Int) : Long
    {
        val entity = SeriesEntity(seriesId, date, screen, views)
        em.persist(entity)

        return entity.id!!
    }

    override fun getAllSeriesId(): List<String>
    {
        val query: TypedQuery<String> = em.createQuery("select s.seriesId from SeriesEntity s", String::class.java)
        return query.resultList
    }

    override fun getAllViews(): List<Int>
    {
        val query = em.createQuery("select s.views from SeriesEntity s", java.lang.Integer::class.java)
        return query.resultList as List<Int>
    }

    override fun getAllOrderByDate(): List<SeriesEntity>
    {
        val query = em.createQuery("select s from SeriesEntity s order by s.date", SeriesEntity::class.java)
        return query.resultList
    }

    override fun getAllSeriesAndViewsOrderedByDate(): List<Pair<String, Int>>
    {
        val query = em.createQuery("select s from SeriesEntity s order by s.date", SeriesEntity::class.java)
        val list = query.resultList
        val newList = mutableListOf<Pair<String, Int>>()

        for ((index, value) in list.withIndex())
        {
            newList[index] = Pair(value.seriesId, value.views)
        }

        return newList
    }

    override fun getAllSeriesIdSeenOnTv(): List<SeriesEntity>
    {
        val query = em.createQuery("select s from SeriesEntity s where s.screen = ?1", SeriesEntity::class.java)
        query.setParameter(1, "tv")
        return query.resultList
    }

    override fun getSeriesIdWithMostViews(): List<SeriesEntity>
    {
        val query = em.createQuery("select s from SeriesEntity s order by s.views desc", SeriesEntity::class.java)
        query.maxResults = 1
        return query.resultList
    }
}














