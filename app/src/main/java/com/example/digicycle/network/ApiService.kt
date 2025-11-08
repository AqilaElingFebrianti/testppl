package com.example.digicycle.network

import com.example.digicycle.models.GetCurrentPricesResponse
import com.example.digicycle.models.GetPriceHistoryResponse
import com.example.digicycle.models.LoginRequest
import com.example.digicycle.models.LoginResponse
import com.example.digicycle.models.RegisterRequest
import com.example.digicycle.models.RegisterResponse
import com.example.digicycle.models.WastePriceRequest
import com.example.digicycle.models.WastePriceResponse
import com.example.digicycle.models.AdminDashboardStatsResponse
import com.example.digicycle.models.ReportResponse
import okhttp3.ResponseBody

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

interface ApiService {

    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("api/v1/auth/register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>


    @GET("api/v1/waste-prices")
    suspend fun getCurrentPrices(
        @Query("cacheBuster") cacheBuster: Long? = null
    ): Response<GetCurrentPricesResponse>

    @GET("api/v1/waste-prices/history/{category_id}")
    suspend fun getPriceHistory(
        @Path("category_id") categoryId: Int
    ): Response<GetPriceHistoryResponse>

    @POST("api/v1/waste-prices")
    suspend fun createWastePrice(
        @Header("Authorization") token: String,
        @Body request: WastePriceRequest
    ): Response<WastePriceResponse>

    @GET("api/v1/admin/dashboard/stats")
    suspend fun getAdminDashboardStats(
        @Header("Authorization") token: String,
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null
    ): Response<AdminDashboardStatsResponse>

    @GET("/api/v1/admin/reports/transactions")
    suspend fun getTransactionReport(
        @Query("transaction_type") type: String? = null,
        @Query("user_id") userId: Int? = null,
        @Query("status") status: String? = null,
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("sort_order") sortOrder: String? = null,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): Response<ReportResponse>

    @GET("/api/v1/admin/reports/export")
    @Streaming
    suspend fun exportTransactionsToCsv(
        @Query("transaction_type") type: String? = null,
        @Query("user_id") userId: Int? = null,
        @Query("status") status: String? = null,
        @Query("date_from") dateFrom: String? = null,
        @Query("date_to") dateTo: String? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("sort_order") sortOrder: String? = null
    ): Response<ResponseBody>
}