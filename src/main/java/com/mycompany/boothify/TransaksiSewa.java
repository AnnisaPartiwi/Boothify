/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */

import java.io.FileWriter;
import java.io.IOException;

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
        
        double totalBiaya = booth.hitungTotalBiaya();
        System.out.printf("TOTAL BIAYA  : Rp%,.0f%n", totalBiaya);
        System.out.println("Status Berhasil dicatat di sistem.");
        System.out.println("--------------------------------------------------\n");
        
        try {
            FileWriter pipaTulis = new FileWriter("log_transaksi.txt", true);
            String log = String.format("Pelanggan: %s (%s) | Layanan: %s | Total: Rp%,.0f%n",
                    pelanggan.getNama(), pelanggan.getIdPelanggan(), booth.getNama(), totalBiaya);
            pipaTulis.write(log);
            pipaTulis.close();
            System.out.println("-> [LOG] Riwayat transaksi berhasil dicatat ke log_transaksi.txt");
        } catch (IOException e) {
            System.out.println("Peringatan: Gagal menulis log transaksi: " + e.getMessage());
        }
    }
}
