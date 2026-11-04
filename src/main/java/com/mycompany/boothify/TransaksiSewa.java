/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public class TransaksiSewa {
    public static void prosesTransaksi(Pelanggan pelanggan, LayananBooth booth) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("          STRUK TRANSAKSI SEWA BOOTHIFY           ");
        System.out.println("--------------------------------------------------");
        System.out.println("ID Pelanggan : " + pelanggan.getIdPelanggan());
        System.out.println("Nama Penyewa : " + pelanggan.getNama());
        System.out.println("--------------------------------------------------");
        
        booth.tampilkanRincianSewa();
        booth.cetakPetunjukLayanan();
        
        if (booth instanceof DapatDidiskon) {
            ((DapatDidiskon) booth).terapkanDiskon(10.0);
        }
        
        System.out.printf("TOTAL BIAYA  : Rp%,.0f%n", booth.hitungTotalBiaya());
        System.out.println("Status Berhasil dicatat di sistem.");
        System.out.println("--------------------------------------------------\n");
    }
}
