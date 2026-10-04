/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public class ExpressBooth extends LayananBooth {
    private String jenisKamera;
    private int jumlahProperty;
    
    public ExpressBooth(String nama, int durasiJam, String jenisKamera, int jumlahProperty){
      super(nama, durasiJam, 150000.0);
      this.jenisKamera = jenisKamera;
      this.jumlahProperty = jumlahProperty;
    }
    
    public String getJenisKamera(){
        return this.jenisKamera;
    }
    
    public int getJumlahProperty(){
        return this.jumlahProperty;
    }
    
    @Override
    public void tampilkanRincianSewa(){
        System.out.printf("[Express Booth ] Penyewa: %-12s | Durasi: %d Jam | Kamera: %-9s | Props: %-5d | Total: Rp%,.0f%n", getNama(), getDurasiJam(), this.jenisKamera, this.jumlahProperty, hitungTotalBiaya());
    }
    
    @Override
    public void cetakPetunjukLayanan(){
    System.out.println("Petunjuk: Kit photo booth self-service mandiri disiapkan dan diantar 30 menit sebelum acara.");
    }
}
