package com.example.stor.db.offline

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stor.db.offline.relation.ProductAndTarshaCross
import com.example.stor.db.offline.relation.TareshaWithSolidger
import com.example.stor.db.offline.tables.Gards
import com.example.stor.db.offline.tables.Product
import com.example.stor.db.offline.tables.Solidger
import com.example.stor.db.offline.tables.Taresha
import java.util.*

@Dao
interface StorDao  {

    //insert new gard
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGard(gards: Gards)

    //update product tabel
    @Update
    suspend fun updateProduct(product: Product)

    //update soldger tabel
    @Query("UPDATE Solidger SET currentmoneyTaresha = :currentmoneyTaresha WHERE ID_Solidger = :id")
    suspend fun updateSoldgerMony(id: Int,  currentmoneyTaresha: Double)


    //add new soldger
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSoildger(soildger: Solidger)

    //add new product
    @Insert
    suspend fun insertProduct(product: Product)

    //check product
    @Query("select Count() from Product where  name =:name")
   suspend fun checkProduct(name: String?): Int


    //add new taresha
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaresha(taresha: Taresha):Long

    //add cross
    @Insert
    suspend fun insertproductTarshaCross(crossRef: ProductAndTarshaCross)




    @Transaction
    @Query("SElECT * FROM Taresha WHERE ID_Solidger = :ID_Solidger ")
    suspend fun getSolidgerHaveTaresha(ID_Solidger: Int):List<TareshaWithSolidger>


    @Transaction
    @Query("Select * From Solidger ORDER BY solidgerName ASC ")
     fun getAllSolidgerHaveTaresha():LiveData<List<Solidger>>


    @Query("SELECT * FROM Product ")
     fun  getAllProudects():LiveData< List<Product>>

     //get all taresha


    @Transaction
    @Query("SElECT * FROM Gards WHERE Date_Sell = :Date_Sell ")
     fun getgardDay(Date_Sell: Date):LiveData<List<Gards>>

    @Transaction
    @Query("SElECT * FROM Gards ")
    suspend  fun getAllgardDay():List<Gards>


    @Transaction
    @Query("SElECT name FROM Product WHERE ID_Product = :id ")
    suspend  fun getNameById(id: Int):String

    @Transaction
    @Query("SElECT * FROM ProductAndTarshaCross ")
    fun getAllCross():LiveData<List<ProductAndTarshaCross>>

    @Transaction
    @Query("SElECT SUM( Total_mony ) FROM Gards where Date_Sell = :Date_Sell")
    fun getgardDayTotalSell(Date_Sell: Date):LiveData<Double>




    @Transaction
    @Query("SElECT SUM( money ) FROM Taresha where date = :date")
    fun gettareshaDay(date: Date):LiveData<Double>
    @Transaction
    @Query("SElECT * FROM Taresha")
    fun getAllTaresha():LiveData<List<Taresha>>



    @Transaction
    @Query("SElECT SUM( Total_mony ) - ( SElECT SUM( money ) FROM Taresha where date = :Date_Sell) FROM Gards WHERE Date_Sell = :Date_Sell ")
    fun getgardDayTotalCash(Date_Sell: Date):LiveData<Double>






    //get what product that i get
    //get prodcut with tarsha
    //get taresha with product





/*
    @Transaction
    @Query("SELECT * FROM school WHERE schoolName = :schoolName")
    suspend fun getSchoolWithStudents(schoolName: String): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM subject WHERE subjectName = :subjectName")
    suspend fun getStudentsOfSubject(subjectName: String): List<SubjectWithStudents>

    @Transaction
    @Query("SELECT * FROM student WHERE studentName = :studentName")
    suspend fun getSubjectsOfStudent(studentName: String): List<StudentWithSubjects>
    */

}