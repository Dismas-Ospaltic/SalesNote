package com.st11.salesnote.data.local

//


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.st11.salesnote.model.DailySalesReport
import com.st11.salesnote.model.SingleSaleEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface SingleSaleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingleSale(singleSaleEntity: SingleSaleEntity)

    @Update
    suspend fun updateSingleSale(singleSaleEntity: SingleSaleEntity)

    @Query("SELECT * FROM single_sale ORDER BY timestamp DESC")
    fun getAllSingleSale(): Flow<List<SingleSaleEntity>>

    @Query("SELECT * FROM single_sale WHERE date = :saleDate")
    fun getSalesByDate(saleDate: String): Flow<List<SingleSaleEntity>>



    @Query("""
    SELECT 
        date,
        SUM(CASE WHEN saleType = 'Cash' THEN totalPaid ELSE 0 END) AS cash,
        SUM(CASE WHEN saleType = 'Bank' THEN totalPaid ELSE 0 END) AS bank,
        SUM(CASE WHEN saleType = 'M-pesa' THEN totalPaid ELSE 0 END) AS mpesa,
        SUM(CASE WHEN saleType NOT IN ('Cash','Bank','M-pesa') THEN totalPaid ELSE 0 END) AS other,
        SUM(totalPaid) AS total
    FROM single_sale
    GROUP BY date
    ORDER BY date ASC
""")
    fun getDailySalesReports(): Flow<List<DailySalesReport>>



//    @Query("SELECT * FROM watchlist WHERE watchlistId = :watchlistId")
//    fun getWatchListById(watchlistId: String): Flow<WatchListEntity?>
//
//    @Query("DELETE FROM watchlist WHERE watchlistId = :watchlistId")
//    suspend fun deleteWatchListById(watchlistId: String)
//
//    @Query("UPDATE watchlist SET watchListTitle = :watchListTile  , expectedCompleteDate = :expectedCompleteDate , link = :link , type = :type , notes = :notes , category = :category , noEpisodesPage = :noEpisodesPage WHERE watchlistId = :watchlistId")
//    suspend fun updateWatchlistById(watchlistId: String, watchListTile: String,expectedCompleteDate : String, link: String?, type: String, notes: String?, category: String, noEpisodesPage: Int): Int?
//
//    @Query("UPDATE watchlist SET watchlistStatus = :watchlistStatus  WHERE watchlistId = :watchlistId")
//    suspend fun updateWatchlistStatusById(watchlistId: String, watchlistStatus: String): Int?
//
//
//    @Query("UPDATE watchlist SET status = :status  WHERE watchlistId = :watchlistId")
//    suspend fun updateStatusById(watchlistId: String, status: String): Int?
//
//    @Query("UPDATE watchlist SET seenPageEpisode = :seenPageEpisode  WHERE watchlistId = :watchlistId")
//    suspend fun updateSeenPageEpisodeById(watchlistId: String, seenPageEpisode: Int): Int?


}