package com.example.storeRental.repo

import com.example.storeRental.domain.StoreImageModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreImageRepo: JpaRepository<StoreImageModel, Long> {
}