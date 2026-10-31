/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Boothify {
    public static void cariSewa(String nama, ArrayList<LayananBooth> daftarSewa) throws PesananTidakDitemukan {
       System.out.println("\nHasil Pencarian Berdasarkan Nama: " + nama );
        boolean ditemukan = false;
        for (LayananBooth booth : daftarSewa) {
            if (booth.getNama().equalsIgnoreCase(nama)) {
                booth.tampilkanRincianSewa();
                booth.cetakPetunjukLayanan();
                booth.hitungDendaKeterlambatan();
                ditemukan = true;
            }
        } 
        if (!ditemukan) {
            throw new PesananTidakDitemukan("Gagal: Pesanan dengan nama '" + nama + "' tidak ditemukan!");
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
    
    
    public static void cetakNotaSewaNama(String nama, ArrayList<LayananBooth> daftarSewa) throws PesananTidakDitemukan {
        boolean ditemukan = false;
        double totalHargaGabungan = 0;
        int count = 0;
           
        System.out.println("\n--------------------------------------------------");
        System.out.println("           BOOTHIFY PHOTO BOOTH NOTA              ");
        System.out.println(" Pelanggan: " + nama);
        System.out.println("--------------------------------------------------");
        
        for(LayananBooth booth : daftarSewa){
            if (booth.getNama().equalsIgnoreCase(nama)){
                ditemukan = true;
                count++;
                System.out.println("- Item Pesanan Ke-" + count + " -");
                totalHargaGabungan += cetakNotaSewa(booth);
            }
        }
        
        if (ditemukan){ 
            System.out.printf(" TOTAL SELURUH PEMBAYARAN: Rp%,.0f%n", totalHargaGabungan);
            System.out.println("--------------------------------------------------");
            System.out.println(" Terima kasih telah menggunakan layanan Boothify!");
            System.out.println("--------------------------------------------------\n");
        }else{
            throw new PesananTidakDitemukan("Gagal Cetak Nota: Data pesanan atas nama " + nama + " tidak ditemukan");
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
        ArrayList<LayananBooth> daftarSewa = new ArrayList<>();
        HashMap<String, String> dataProperty = new HashMap<>();
        
        dataProperty.put("PROP01", "Paket Fun (5 Kacamata Oversize + 3 Papan Tulisan Lucu)");
        dataProperty.put("PROP02", "Paket Party (Topi Ulang Tahun + Bando Karakter + Party Popper)");
        dataProperty.put("PROP03", "Paket Elegant (Bando Mutiara + Topi Fedora + Kacamata Hitam Deluxe)");
        
        boolean isRunning = true;
       
        System.out.println("==================================================");
        System.out.println("      SELAMAT DATANG DI BOOTHIFY PHOTO BOOTH     ");
        System.out.println("==================================================");
        
        
        System.out.print("\nMasukkan jumlah data pesanan sewa awal yang ingin diinputkan: ");
        try{
            int jumlahAwal = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < jumlahAwal; i++) {
                daftarSewa.add(inputDataSewa(scanner, i + 1));
            }
            System.out.println("\nSuccess: Berhasil menginputkan " + jumlahAwal + " data pesanan awal\n");
        }catch (Exception e){
            System.out.println("\nGagal menginput data awal: " + e.getMessage());
            scanner.nextLine();
        }
        
        while (isRunning) {
            System.out.println("\n==================================================");
            System.out.println("MENU UTAMA BOOTHIFY:");
            System.out.println("1. Tambah Pesanan Sewa Booth Baru");
            System.out.println("2. Tampilkan Seluruh Pesanan Sewa");
            System.out.println("3. Cari Pesanan Sewa");
            System.out.println("4. Cetak Nota Pembayaran Pesanan");
            System.out.println("5. Katalog Paket Property Booth");
            System.out.println("6. Keluar");
            System.out.print("Pilih opsi (1-6): ");
            System.out.println("\n==================================================");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

            switch (pilihan) {
                case 1 -> {
                    daftarSewa.add(inputDataSewa(scanner, daftarSewa.size() + 1));
                        System.out.println("Berhasil ditambahkan! Total data: " + daftarSewa.size());
                }
                
                case 2 -> {
                    System.out.println("\n=== DAFTAR PESANAN BOOTHIFY ===");
                    if (daftarSewa.isEmpty()) {
                        System.out.println("Belum ada data.");
                    }else{
                        for (int i = 0; i < daftarSewa.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                daftarSewa.get(i).tampilkanRincianSewa();
                                daftarSewa.get(i).cetakPetunjukLayanan();
                                daftarSewa.get(i).hitungDendaKeterlambatan();

                                if (daftarSewa.get(i) instanceof DapatDibatalkan) {
                                    DapatDibatalkan itemBatal = (DapatDibatalkan) daftarSewa.get(i);
                                    itemBatal.batalkanPesanan();
                                }
                                if (daftarSewa.get(i) instanceof DapatDidiskon) {
                                    DapatDidiskon itemDiskon = (DapatDidiskon) daftarSewa.get(i);
                                    itemDiskon.terapkanDiskon(10.0);
                                }
                            System.out.println("------------------------------------------------");
                        }
                        System.out.println("\nTotal Transaksi: " + LayananBooth.totalTransaksiBerhasil);
                    }
                }
                
                case 3 -> {
                    System.out.println("\n--- Fitur Cari Pesanan ---");
                    if (daftarSewa.isEmpty()) {
                        System.out.println("Belum ada data pesanan.");
                        break;
                    }
                    System.out.print("Masukkan Nama Penyewa yang Dicari: ");
                    String namaCari = scanner.nextLine();
        
                    try {
                        cariSewa(namaCari, daftarSewa);
                    } catch (PesananTidakDitemukan e) {
                        System.out.println("Peringatan: " + e.getMessage());
                    } finally {
                        System.out.println("Sesi pencarian pesanan selesai dieksekusi.");
                    }
                }
                
                case 4 -> {
                    System.out.println("\n---- Cetak Nota Pembayaran ----");
                    if (daftarSewa.isEmpty()) {
                        System.out.println("Belum ada data pesanan untuk dicetak nota.");
                        break;
                    }
                    System.out.print("Masukkan Nama Penyewa: ");
                    String namaCetak = scanner.nextLine();
        
                    try {
                        cetakNotaSewaNama(namaCetak, daftarSewa);
                    } catch (PesananTidakDitemukan e) {
                        System.out.println("Peringatan: " + e.getMessage());
                    } finally {
                        System.out.println("Sesi cetak nota selesai dieksekusi.");
                    }
                }
                
                case 5 -> {
                    System.out.println("\n-- KATALOG PAKET PROPERTY BOOTH --");
                    System.out.println("1. Lihat Daftar Paket Property Tersedia");
                    System.out.println("2. Pilih/Cek Detail Paket via Kode");
                    System.out.println("3. Tambah Paket Custom Baru");
                    System.out.print("Pilih (1-3): ");
                    int menuProp = scanner.nextInt();
                    scanner.nextLine();
                    
                    if (menuProp == 1){
                        System.out.println("\n--- Daftar Paket Property Tersedia ---");
                        for (String kode : dataProperty.keySet()) {
                            System.out.println("- Kode: " + kode + " | " + dataProperty.get(kode));
                        }
                    } else if (menuProp == 2){
                        System.out.print("\nMasukkan Kode Paket yang Ingin Dipilih (misal: PROP01): ");
                        String kodePilih = scanner.nextLine().toUpperCase();

                        if (dataProperty.containsKey(kodePilih)) {
                            System.out.println("\nAnda Memilih: " + dataProperty.get(kodePilih));
                            System.out.println("Status: Paket siap ditambahkan ke pesanan Boothify!");
                        } else {
                            System.out.println("\nKode paket '" + kodePilih + "' tidak ditemukan dalam sistem.");
                        }
                    } else if (menuProp == 3){
                        System.out.print("Masukkan Kode Paket Baru: ");
                        String kodeBaru = scanner.nextLine().toUpperCase();
                        System.out.print("Masukkan Deskripsi Isi Paket: ");
                        String isiBaru = scanner.nextLine();

                        dataProperty.put(kodeBaru, isiBaru);
                        System.out.println("Success: Paket baru berhasil ditambahkan ke katalog sistem!");
                    } else {
                        System.out.println("Pilihan menu tidak valid!");
                    }
                }
                
                case 6 -> {
                        System.out.println("Terima kasih!");
                        isRunning = false;
                    }
                    default -> System.out.println("Opsi menu tidak valid!");
                }
            }catch (InputMismatchException e) {
                System.out.println("\n!ERROR SYSTEM: Input harus berupa angka!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\n! ERROR SYSTEM: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
  