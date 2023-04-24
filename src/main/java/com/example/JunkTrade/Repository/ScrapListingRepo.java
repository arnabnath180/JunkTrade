package com.example.JunkTrade.Repository;

import com.example.JunkTrade.models.ScrapItemListings;
import com.example.JunkTrade.models.ScrapListings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapListingRepo extends JpaRepository<ScrapListings,Long> {
}
