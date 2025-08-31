package com.st11.salesnote.data.local

//


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.st11.salesnote.model.SingleProductEntity
import com.st11.salesnote.model.SingleSaleEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface SingleSaleProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSoldProduct(singleProductEntity: SingleProductEntity)

    @Update
    suspend fun updateSoldProduct(singleProductEntity: SingleProductEntity)


    @Query("SELECT * FROM single_product WHERE date = :saleDate")
    fun getSingleSalesByDate(saleDate: String): Flow<SingleProductEntity?>

//    @Query("SELECT * FROM watchlist ORDER BY timestamp DESC")
//    fun getAllWatchList(): Flow<List<WatchListEntity>>
//
//
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