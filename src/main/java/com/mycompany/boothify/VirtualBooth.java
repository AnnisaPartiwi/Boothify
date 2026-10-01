/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public class VirtualBooth extends LayananBooth{
    private String domainFrame;
    private int kapasitasUser;
    
    public VirtualBooth(String nama, int durasiJam, String domainFrame, int kapasitasUser){
        super(nama, durasiJam, 200000.0);
        this.domainFrame = domainFrame;
        this.kapasitasUser = kapasitasUser;
    }
    
    public String getDomainFrame(){
        return this.domainFrame;
    }
    
    public int getKapasitasUser(){
        return this.kapasitasUser;
    }
    
    @Override
    public void tampilkanRincianSewa(){
        System.out.printf("[Virtual Booth] Penyewa: %-12s | Durasi: %d Jam | Frame: %-10s | Max User: %-4d | Total: Rp%,.0f%n" ,getNama(), getDurasiJam(), this.domainFrame, this.kapasitasUser, hitungTotalBiaya());
    }
    
    @Override
    public void cetakPetunjukLayanan(){
        System.out.println("Petunjuk: Tautan booth virtual dan link QR code akses siap digunakan 15 menit sebelum acara.");
    }
}
