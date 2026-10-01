/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public class PhysicalBooth extends LayananBooth {
    private String ukuranBooth;
    private boolean cetakFisik;
    
    public PhysicalBooth(String nama, int durasiJam, String ukuranBooth, boolean cetakFisik){
        super(nama, durasiJam, 350000.0);
        this.ukuranBooth = ukuranBooth;
        this.cetakFisik = cetakFisik;
    }
    
    public String getUkuranBooth(){
        return this.ukuranBooth;
    }
    
    public boolean isCetakFisik(){
        return this.cetakFisik;
    }
    
    @Override
    public double hitungTotalBiaya(){
        double biayaDasar = super.hitungTotalBiaya();
        if (cetakFisik){
            biayaDasar += 100000.0;  
        }
        return biayaDasar;
    }
    
    @Override
    public void tampilkanRincianSewa(){
        System.out.printf("[Physical Booth] Penyewa: %-12s | Durasi: %d Jam | Ukuran: %-7s | Cetak Fisik: %-5s | Total: Rp%,.0f%n", getNama(), getDurasiJam(), this.ukuranBooth, (this.cetakFisik ? "Ya" : "Tidak"), hitungTotalBiaya());
    }
    
    @Override
    public void cetakPetunjukLayanan(){
        System.out.println("Petunjuk: Booth fisik dan kru operasional akan hadir 1 jam sebelum acara dimulai.");
    }
}

