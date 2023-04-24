package com.example.JunkTrade.admin.controller;

import com.example.JunkTrade.admin.dtos.AdminItemResponseDTO;
import com.example.JunkTrade.admin.dtos.AdminResponseDTO;
import com.example.JunkTrade.admin.dtos.LoginAdminDTO;
import com.example.JunkTrade.admin.dtos.LoginResponseDTO;
import com.example.JunkTrade.admin.services.AdminService;
import com.example.JunkTrade.models.ScrapItemListings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> verifyPatient(
            @RequestBody LoginAdminDTO request)
    {
        LoginResponseDTO verifiedAdmin = adminService.verifyAdmin(request);
        return ResponseEntity.ok().body(verifiedAdmin);
    }

    @DeleteMapping("/deleteScrapList/{scrapId}")
    public String deleteScrapFromList(@PathVariable Long scrapId){
        return adminService.deleteScrapFromList(scrapId);
    }

    @GetMapping("/getScrapList")
    public List<AdminResponseDTO> getScrapList(){
        return adminService.getScrapList();
    }

    @GetMapping("/getScrapItemList/{scrapId}")
    public List<AdminItemResponseDTO> getScrapItemList(@PathVariable Long scrapId){
        return adminService.getScrapItemList(scrapId);
    }

}
