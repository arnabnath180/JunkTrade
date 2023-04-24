package com.example.JunkTrade.seller.controller;

import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.LoginSellerDTO;
import com.example.JunkTrade.seller.dtos.OrderRequestDTO;
import com.example.JunkTrade.seller.dtos.SellerLoginResponseDTO;
import com.example.JunkTrade.seller.dtos.SellerRegistrationDTO;
import com.example.JunkTrade.seller.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @PostMapping("/login")
    public ResponseEntity<SellerLoginResponseDTO> verifyPatient(
            @RequestBody LoginSellerDTO request)
    {
        SellerLoginResponseDTO verifiedSeller = sellerService.verifySeller(request);
        return ResponseEntity.ok().body(verifiedSeller);
    }

    @PostMapping("/register_seller")
    public void addSeller(@RequestBody SellerRegistrationDTO sellerRegistrationDTO){
        sellerService.addSeller(sellerRegistrationDTO);
    }
    @PostMapping("/add_orders")
    public Long addOrders(@RequestBody OrderRequestDTO orderRequestDTO,@AuthenticationPrincipal Seller seller){
        return sellerService.addOrders(orderRequestDTO,seller);
    }

    @PatchMapping("/add_image/{scrapId}")
    public ResponseEntity<?> uploadMedicalRecord(@RequestParam("image") MultipartFile request,
                                                 @PathVariable Long scrapId) throws IOException {
        sellerService.uploadScrapRecord(request, scrapId);
        return ResponseEntity.ok().build();
    }

}
