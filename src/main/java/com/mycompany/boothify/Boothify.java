/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */

import java.util.Scanner;

public class Boothify {
    public static void cariSewa(String nama, LayananBooth[] daftarSewa, int totalData){
       System.out.println("\nHasil Pencarian Berdasarkan Nama: " + nama );
        boolean ditemukan = false;
        for (int i = 0; i < totalData; i++) {
            if (daftarSewa[i].getNama().equalsIgnoreCase(nama)) {
                daftarSewa[i].tampilkanRincianSewa();
                daftarSewa[i].cetakPetunjukLayanan();
                ditemukan = true;
            }
        } 
        if(!ditemukan){
            System.out.println("Data penyewa dengan nama " + nama + " tidak ditemukan.");
        }
    }
    
    public static void cariSewa(int durasi, LayananBooth[] daftarSewa, int totalData){
        System.out.println("\nHasil Pencarian Berdasarkan Durasi: " + durasi + " Jam");
        boolean ditemukan = false;
        for (int i = 0; i < totalData; i++) {
            if (daftarSewa[i].getDurasiJam() == durasi) {
                daftarSewa[i].tampilkanRincianSewa();
                daftarSewa[i].cetakPetunjukLayanan();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Data sewa dengan durasi " + durasi + " jam tidak ditemukan.");
        }
    }
    
    public static double cetakNotaSewa(LayananBooth layanan){
        layanan.tampilkanRincianSewa();
        layanan.cetakPetunjukLayanan();
        layanan.hitungDendaKeterlambatan();
        
        if (layanan instanceof DapatDidiskon){
            DapatDidiskon itemDiskon = (DapatDidiskon) layanan;
            itemDiskon.terapkanDiskon(10.0);
        }
        if (layanan instanceof DapatDibatalkan){
            DapatDibatalkan itemBatal = (DapatDibatalkan) layanan;
            itemBatal.batalkanPesanan();
        }
        
        System.out.println("------------------------------------------------");
        return layanan.hitungTotalBiaya();
    }
    
    
    public static void cetakNotaSewaNama(String nama, LayananBooth[] daftarSewa, int totalData){
        boolean ditemukan = false;
        double totalHargaGabungan = 0;
        int count = 0;
                
        System.out.println("\n--------------------------------------------------");
        System.out.println("           BOOTHIFY PHOTO BOOTH NOTA              ");
        System.out.println(" Pelanggan: " + nama);
        System.out.println("--------------------------------------------------");
        
        for(int i = 0; i < totalData; i++){
            if (daftarSewa[i].getNama().equalsIgnoreCase(nama)){
                ditemukan = true;
                count++;
                System.out.println("- Item Pesanan Ke-" + count + " -");
                
                cetakNotaSewa(daftarSewa[i]);
                totalHargaGabungan += daftarSewa[i].hitungTotalBiaya();
            }
        }
        
        if (ditemukan){ 
            System.out.printf(" TOTAL SELURUH PEMBAYARAN: Rp%,.0f%n", totalHargaGabungan);
            System.out.println("--------------------------------------------------");
            System.out.println(" Terima kasih telah menggunakan layanan Boothify!");
            System.out.println("--------------------------------------------------\n");
        }else{
            System.out.println("\n Data penyewa dengan nama" + nama + "tidak ditemukan.");
            System.out.println("--------------------------------------------------\n");
        }
    }
    
    public static LayananBooth inputDataSewa(Scanner scanner, int nomor) {
        System.out.print("\nData ke-" + nomor + " | Tipe Booth (1. Physical / 2. Virtual / 3. Express): ");
        int tipe = scanner.nextInt(); 
        scanner.nextLine();
        System.out.print("Nama Penyewa: "); 
        String nama = scanner.nextLine();
        System.out.print("Durasi (Jam): "); 
        int durasi = scanner.nextInt();
        scanner.nextLine();

        switch (tipe){
            case 1 -> {
                System.out.print("Ukuran (Compact/Deluxe/Grand): "); 
                String uk = scanner.nextLine();
                System.out.print("Cetak Fisik? (true/false): "); 
                boolean cetak = scanner.nextBoolean(); 
                scanner.nextLine();
                return new PhysicalBooth(nama, durasi, uk, cetak);
            }
            case 2 -> {
                System.out.print("Nama Frame: "); 
                String frame = scanner.nextLine();
                System.out.print("Max User: "); 
                int cap = scanner.nextInt(); 
                scanner.nextLine();
                return new VirtualBooth(nama, durasi, frame, cap);
            }
            case 3 -> {
                System.out.print("Jenis Kamera (Polaroid/DSLR): "); 
                String kamera = scanner.nextLine();
                System.out.print("Jumlah Property: "); 
                int property = scanner.nextInt(); 
                scanner.nextLine();
                return new ExpressBooth(nama, durasi, kamera, property);    
            }
            default -> {
                System.out.println("Tipe tidak valid! Menggunakan Express Booth secara default.");
                return new ExpressBooth(nama, durasi, "Standard", 5);
            }
        }
    }
    
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        LayananBooth[] daftarSewa = new LayananBooth[50];
        int totalSewa = 0;
        boolean isRunning = true;
        
        System.out.println("==================================================");
        System.out.println("      SELAMAT DATANG DI BOOTHIFY PHOTO BOOTH     ");
        System.out.println("==================================================");
        
        System.out.print("\nMasukkan jumlah data pesanan sewa awal yang ingin diinputkan: ");
        int jumlahAwal = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jumlahAwal; i++) {
            daftarSewa[totalSewa] = inputDataSewa(scanner, i + 1);
            totalSewa++;
        }

        System.out.println("\n-> Success: Berhasil menginputkan " + jumlahAwal + " data pesanan awal!\n");
        
        while (isRunning) {
            System.out.println("\n==================================================");
            System.out.println("MENU UTAMA BOOTHIFY:");
            System.out.println("1. Tambah Pesanan Sewa Booth Baru");
            System.out.println("2. Tampilkan Seluruh Pesanan Sewa");
            System.out.println("3. Cari Pesanan Sewa");
            System.out.println("4. Cetak Nota Pembayaran Pesanan");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi (1-5): ");

            int pilihan = scanner.nextInt();
            scanner.nextLine();
                    
            switch (pilihan) {
                case 1 -> {
                    if (totalSewa < daftarSewa.length) {
                        daftarSewa[totalSewa] = inputDataSewa(scanner, totalSewa + 1);
                        totalSewa++;
                        System.out.println("-> berhasil ditambahkan!");
                    } else {
                        System.out.println("kapasitas sudah penuh.");
                    }
                }
                
                case 2 -> {
                    System.out.println("\n=== DAFTAR PESANAN BOOTHIFY ===");
                    if (totalSewa == 0) {
                        System.out.println("Belum ada data.");
                    }
                    for (int i = 0; i < totalSewa; i++) {
                        System.out.print((i + 1) + ". ");
                        daftarSewa[i].tampilkanRincianSewa();
                        daftarSewa[i].cetakPetunjukLayanan();
                        
                        daftarSewa[i].hitungDendaKeterlambatan();
                        
                        if (daftarSewa[i] instanceof DapatDibatalkan) {
                            DapatDibatalkan itemBatal = (DapatDibatalkan) daftarSewa[i];
                            itemBatal.batalkanPesanan();
                        }
                        if (daftarSewa[i] instanceof DapatDidiskon) {
                            DapatDidiskon itemDiskon = (DapatDidiskon) daftarSewa[i];
                            itemDiskon.terapkanDiskon(10.0);
                        }
                        System.out.println("------------------------------------------------");
                    }
                    System.out.println("\nTotal Transaksi: " + LayananBooth.totalTransaksiBerhasil);
                }
                
                case 3 -> {
                    System.out.print("Cari berdasarkan (1. Nama / 2. Durasi): ");
                    int mode = scanner.nextInt();
                    scanner.nextLine();
                    if (mode == 1) {
                        System.out.print("Nama Penyewa: ");
                        cariSewa(scanner.nextLine(), daftarSewa, totalSewa);
                    } else if (mode == 2) {
                        System.out.print("Durasi Jam: ");
                        int durasiCari = scanner.nextInt();
                        scanner.nextLine();
                        cariSewa(durasiCari, daftarSewa, totalSewa);
                    } else {
                        System.out.println("Mode pencarian tidak valid!");
                    }
                }
                
                case 4 -> {
                    System.out.println("\n---- Cetak Nota Pembayaran ----");
                    if (totalSewa == 0){
                        System.out.println("Belum ada data pesanan untuk dicetak nota.");
                        break;
                    }
                    System.out.print("Masukkan Nama Penyewa: ");
                    String namaCetak = scanner.nextLine();
                    
                    cetakNotaSewaNama(namaCetak, daftarSewa, totalSewa);
                }
                case 5 -> {
                    System.out.println("Terima kasih!");
                    isRunning = false;
                }
                default -> System.out.println("Opsi tidak valid!");
            }
        }
        scanner.close();
    }
}
  