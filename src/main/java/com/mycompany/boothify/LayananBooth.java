/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public abstract class LayananBooth {
    private String nama;
    private int durasiJam;
    private double hargaPerJam;
    
    public static int totalTransaksiBerhasil = 0;
    
    public LayananBooth(String nama, int durasiJam, double hargaPerJam){
        this.nama = nama;
        setDurasiJam(durasiJam);
        this.hargaPerJam = hargaPerJam;
        totalTransaksiBerhasil++;
    }
    
    public String getNama(){
        return this.nama;
    }
    
    public void setNama(String namaPenyewa){
        this.nama = nama;
    }
    
    public int getDurasiJam(){
        return this.durasiJam;
    }
    
    public void setDurasiJam(int durasiJam){
        if(durasiJam > 0){
            this.durasiJam = durasiJam;
        }else{
            this.durasiJam = 1;
        }
    }
    
    public double getHargaPerJam(){
        return this.hargaPerJam;
    }
    
    public double hitungTotalBiaya(){
        return this.durasiJam * this.hargaPerJam;
    }
    
    public void tampilkanRincianSewa(){
        System.out.printf("Penyewa: %-15s | Durasi: %2d Jam | Total: Rp%,.2f" ,this.nama, this.durasiJam, hitungTotalBiaya());
    }
    
    public void cetakPetunjukLayanan(){
        System.out.println("-- Layanan photo booth Boothify --");
    }
    
    public abstract void hitungDendaKeterlambatan();
}
