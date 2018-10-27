package org.filereader.series.model

import org.filereader.series.Initializer
import org.filereader.series.model.repository.SeriesRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@DataJpaTest
@Transactional(propagation = Propagation.NEVER)
class SeriesEntityTest
{
    @Autowired
    private lateinit var crud: SeriesRepository

    @Before
    fun cleanDatabase()
    {
        crud.deleteAll()
    }

    @Test
    fun testInit()
    {
        assertNotNull(crud)
    }

    @Test
    fun testCreateSeries()
    {
        assertEquals(0, crud.count())

        val seriesEntity = crud.createSeries("name", 55, "screen", 6)

        assertEquals(1, crud.count())
        assertEquals(seriesEntity, crud.findById(seriesEntity).get().id)
    }

    @Test
    fun testPopulateDb()
    {
        assertEquals(0, crud.count())
        Initializer().initialize(crud)
        assertEquals(6259, crud.count())
    }

    @Test
    fun testGetAllSeriesId()
    {
        Initializer().initialize(crud)

        val list = crud.getAllSeriesId()

        assertTrue(list.size > 1)
    }

    @Test
    fun testGetAllViews()
    {
        Initializer().initialize(crud)

        val list = crud.getAllViews()

        assertTrue(list.size > 1)
    }

    @Test
    fun testOrderByDate()
    {
        Initializer().initialize(crud)

        val list = crud.getAllOrderByDate()

        assertTrue(list[0].date < list[list.size-1].date)
    }

    @Test
    fun testGetAllSeriesSeenOnTv()
    {
        Initializer().initialize(crud)

        val list = crud.getAllSeriesIdSeenOnTv()

        list.forEach { assertEquals("tv", it.screen) }
    }

    @Test
    fun testGetSeriesWithMostViews()
    {
        Initializer().initialize(crud)

        val entity = crud.findAll().maxBy { it.views }
        val list = crud.getSeriesIdWithMostViews()

        assertEquals(entity!!.views, list[0].views)
    }

    @Test
    fun testGetBySeriesAndDate()
    {
        Initializer().initialize(crud)

        val list = crud.findAllBySeriesIdAndDate("martin-og-mikkelsen", 20181016)
        val list2 = crud.findAllBySeriesIdAndDate("heimebane", 20181016)

        assertEquals(6, list.count())
        assertEquals(6, list2.count())
    }
}