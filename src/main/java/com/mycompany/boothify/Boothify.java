/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Boothify {

    public static LayananBooth inputDataSewa(Scanner scanner, int nomor) throws Exception {
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
        
        ManajemenBoothify sistemBoothify = new ManajemenBoothify("Sistem Operasional Boothify");
        sistemBoothify.bukaSistem();
        
        boolean isRunning = true;
       
        System.out.println("==================================================");
        System.out.println("      SELAMAT DATANG DI BOOTHIFY PHOTO BOOTH     ");
        System.out.println("==================================================");
       
        while (isRunning) {
            System.out.println("\n==================================================");
            System.out.println("                  MENU UTAMA BOOTHIFY:              ");
            System.out.println("1. Tambah Pesanan Sewa Booth Baru");
            System.out.println("2. Tampilkan Seluruh Pesanan Sewa");
            System.out.println("3. Cari Pesanan Sewa");
            System.out.println("4. Manajemen Pelanggan");
            System.out.println("5. Transaksi Sewa Booth");
            System.out.println("6. Katalog Paket Property");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi (1-7): ");
            System.out.println("\n==================================================");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\n-- Tambah Pesanan Sewa Booth Baru --");
                            System.out.print("Pilih Tipe Booth (1. Physical / 2. Virtual / 3. Express): ");
                            int jenis = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Masukkan Nama Penyewa: ");
                            String nama = scanner.nextLine();

                            System.out.print("Masukkan Durasi Sewa (Jam): ");
                            int durasi = scanner.nextInt();
                            scanner.nextLine();

                            if (jenis == 1) {
                                System.out.print("Masukkan Ukuran Booth (Compact/Deluxe/Grand): ");
                                String uk = scanner.nextLine();
                                System.out.print("Apakah Cetak Foto Fisik? (true/false): ");
                                boolean cetak = scanner.nextBoolean();
                                scanner.nextLine();
                                sistemBoothify.tambahSewa(new PhysicalBooth(nama, durasi, uk, cetak));
                            } else if (jenis == 2) {
                                System.out.print("Masukkan Nama Frame Digital: ");
                                String frame = scanner.nextLine();
                                System.out.print("Masukkan Max Kapasitas User: ");
                                int cap = scanner.nextInt();
                                scanner.nextLine();
                                sistemBoothify.tambahSewa(new VirtualBooth(nama, durasi, frame, cap));
                            } else if (jenis == 3) {
                                System.out.print("Masukkan Jenis Kamera (Polaroid/DSLR): ");
                                String kamera = scanner.nextLine();
                                System.out.print("Masukkan Jumlah Property bawaan: ");
                                int prop = scanner.nextInt();
                                scanner.nextLine();
                                sistemBoothify.tambahSewa(new ExpressBooth(nama, durasi, kamera, prop));
                            } else {
                                System.out.println("Tipe booth tidak valid!");
                            }
                    }

                    case 2 -> {
                        sistemBoothify.lihatSemuaSewa();
                    }

                    case 3 -> {
                        System.out.println("\n-- Fitur Cari Pesanan --");
                        System.out.print("Masukkan Nama Penyewa yang Dicari: ");
                        String kataKunci = scanner.nextLine();

                        try {
                            sistemBoothify.cariSewa(kataKunci);
                        } catch (PesananTidakDitemukan e) {
                            System.out.println("Peringatan: " + e.getMessage());
                        }
                    }

                    case 4 -> {
                        System.out.println("\n-- Kelola Data Pelanggan --");
                        System.out.println("1. Tambah Pelanggan Baru");
                        System.out.println("2. Cari Pelanggan via ID");
                        System.out.println("3. Tampilkan Semua Pelanggan");
                        System.out.print("Pilih Submenu (1-3): ");
                        int menuPelanggan = scanner.nextInt();
                        scanner.nextLine();

                        if (menuPelanggan == 1) {
                            System.out.print("Masukkan ID Pelanggan (misal PLG01): ");
                            String id = scanner.nextLine();
                            System.out.print("Masukkan Nama Lengkap: ");
                            String nama = scanner.nextLine();
                            sistemBoothify.tambahPelanggan(new Pelanggan(id, nama));
                        } else if (menuPelanggan == 2) {
                            System.out.print("Masukkan ID Pelanggan yang dicari: ");
                            String idCari = scanner.nextLine();
                            sistemBoothify.cariPelanggan(idCari);
                        } else if (menuPelanggan == 3) {
                            sistemBoothify.lihatSemuaPelanggan();
                        } else {
                            System.out.println("Pilihan submenu pelanggan tidak valid!");
                        }
                    }

                    case 5 -> {
                        System.out.println("\n-- Transaksi Sewa Booth --");
                        System.out.print("Masukkan ID Pelanggan Peminjam: ");
                        String inputId = scanner.nextLine();
                        Pelanggan peminjam = sistemBoothify.getPelanggan(inputId);

                        if (peminjam == null) {
                            System.out.println("Gagal: ID Pelanggan '" + inputId + "' belum terdaftar");
                        } else if (sistemBoothify.getJumlahSewa() == 0) {
                            System.out.println("Gagal: Belum ada data pesanan sewa booth yang tersedia.");
                        } else {
                            boolean pilihLagi = true;
                            
                            while (pilihLagi){
                                sistemBoothify.lihatSemuaSewa();
                                System.out.print("Pilih Nomor Pesanan Sewa yang Ingin Diproses (1-" + sistemBoothify.getJumlahSewa() + "): ");
                                int noSewa = scanner.nextInt() - 1;
                                scanner.nextLine();

                                if (noSewa >= 0 && noSewa < sistemBoothify.getJumlahSewa()) {
                                    LayananBooth itemPilihan = sistemBoothify.getSewaInt(noSewa);
                                    TransaksiSewa.prosesTransaksi(peminjam, itemPilihan);
                                } else {
                                    System.out.println("Nomor pilihan pesanan tidak valid");
                                }
                                
                                System.out.print("Apakah ingin memproses pesanan lain untuk pelanggan ini? (y/n): ");
                                String jawab = scanner.nextLine().trim();
                                if (!jawab.equalsIgnoreCase("y")) {
                                    pilihLagi = false;
                                }
                            }
                        }
                    }

                    case 6 -> {
                        sistemBoothify.lihatKatalogProperty();
                    }
                    
                    case 7 -> {
                        System.out.println("\nTerima kasih telah menggunakan Sistem Operasional Boothify!");
                        isRunning = false;
                    }
                    
                    default -> {
                        System.out.println("Pilihan menu tidak valid, silakan coba lagi.");
                    }
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
  