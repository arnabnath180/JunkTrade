package com.example.JunkTrade.seller.controller;

import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.Seller;
import com.example.JunkTrade.seller.dtos.*;
import com.example.JunkTrade.seller.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
/*
    @PatchMapping("/add_image/{scrapId}")
    public ResponseEntity<?> uploadMedicalRecord(@RequestParam("image") MultipartFile request,
                                                 @PathVariable Long scrapId) throws IOException {
        sellerService.uploadScrapRecord(request, scrapId);
        return ResponseEntity.ok().build();
    }*/

    @GetMapping("/my_orders")
    public List<MyOrdersDTO> myOrders(@AuthenticationPrincipal Seller seller){
        return sellerService.myOrders(seller);
    }

    @GetMapping("/getScrapItemList/{scrapId}")
    public List<SellerItemResponseDTO> getScrapItemList(@PathVariable Long scrapId){
        return sellerService.getScrapItemList(scrapId);
    }

    @DeleteMapping("/deleteScrapList/{scrapId}")
    public String deleteScrapFromList(@PathVariable Long scrapId){
        return sellerService.deleteScrapFromList(scrapId);
    }


}
