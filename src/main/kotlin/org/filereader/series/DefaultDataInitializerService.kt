package org.filereader.series

import org.filereader.series.model.repository.SeriesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader
import javax.annotation.PostConstruct

@Service
class DefaultDataInitializerService
{
    @Autowired
    private lateinit var crud: SeriesRepository

    @PostConstruct
    fun initialize()
    {
        var fileReader: BufferedReader? = null
        try
        {
            var line: String?

            fileReader = BufferedReader(FileReader("visningsdata.csv"))
            fileReader.readLine()
            line = fileReader.readLine()

            while (line != null)
            {
                val tokens = line.split(",")
                if (tokens.isNotEmpty())
                {
                    crud.createSeries(tokens[0], Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]))
                }
                line = fileReader.readLine()
            }
        }
        catch (e: Exception)
        {
            println("Error reading csv file")
            e.printStackTrace()
        }
        finally
        {
            try
            {
                fileReader!!.close()
            }
            catch (e: Exception)
            {
                println("Error closing file reader")
                e.printStackTrace()
            }
        }
    }
}