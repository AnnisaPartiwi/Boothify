/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
        
public class ManajemenBoothify {
    private String namaSistem;
    private GudangPeralatan gudangUtama;
    private ArrayList<LayananBooth> daftarSewa;
    private HashMap<String, Pelanggan> dataPelanggan;
    private HashMap<String, String> dataProperty;
    
    public ManajemenBoothify(String namaSistem) {
        this.namaSistem = namaSistem;
        this.gudangUtama = new GudangPeralatan("Gudang Pusat Boothify", 30);
        this.daftarSewa = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
        
        this.dataProperty = new HashMap<>();
        this.dataProperty.put("PROP01", "Paket Fun (5 Kacamata Oversize & 3 Papan Tulisan)");
        this.dataProperty.put("PROP02", "Paket Party (Topi Ulang Tahun & Bando Karakter)");
        this.dataProperty.put("PROP03", "Paket Elegant (Bando Mutiara & Topi Fedora)");
    }
    
    public void lihatKatalogProperty() {
        System.out.println("\n--- Katalog Paket Property Tersedia ---");
        for (String kode : dataProperty.keySet()) {
            System.out.println("- Kode: " + kode + " | " + dataProperty.get(kode));
        }
    }
    
    public void bukaSistem(){
        System.out.println("\nMenginisialisasi sistem " + this.namaSistem );
        gudangUtama.cekKetersediaan();
    }
    
    public void tambahSewa(LayananBooth booth) {
        daftarSewa.add(booth);
        System.out.println("Sukses! Pesanan sewa ditambahkan. (Total Pesanan: " + daftarSewa.size() + ")");
    }
    
    public void lihatSemuaSewa() {
        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada data sewa yang tersimpan.");
            return;
        }
        System.out.println("\n--- Daftar Pesanan Sewa di Boothify ---");
        for (int i = 0; i < daftarSewa.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarSewa.get(i).tampilkanRincianSewa();
            daftarSewa.get(i).cetakPetunjukLayanan();
            daftarSewa.get(i).hitungDendaKeterlambatan();
            System.out.println("------------------------------------------------");
        }
    }
    
    public void cariSewa(String nama) throws PesananTidakDitemukan {
        System.out.println("Mencari Pesanan dengan Nama: " + nama);
        boolean ditemukan = false;
        for (LayananBooth booth : daftarSewa) {
            if (booth.getNama().equalsIgnoreCase(nama)) {
                booth.tampilkanRincianSewa();
                booth.cetakPetunjukLayanan();
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            throw new PesananTidakDitemukan("Gagal! Pesanan dengan nama '" + nama + "' tidak terdaftar.");
        }
    }
    
    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getIdPelanggan(), p);
        System.out.println("Berhasil menambahkan pelanggan!");
    }
    
    public void cariPelanggan(String id) {
        if (dataPelanggan.containsKey(id)) {
            System.out.println("Ditemukan! Nama: " + dataPelanggan.get(id).getNama());
        } else {
            System.out.println("Pelanggan dengan ID tersebut tidak terdaftar.");
        }
    }
    
    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- ID: " + p.getIdPelanggan() + " | Nama: " + p.getNama());
        }
    }
    
    public Pelanggan getPelanggan(String id) {
        return dataPelanggan.get(id);
    }

    public LayananBooth getSewaInt(int index) {
        return daftarSewa.get(index);
    }

    public int getJumlahSewa() {
        return daftarSewa.size();
    }
    
    public void simpanDataKeFile() {
        try {
            FileOutputStream fileKeluar = new FileOutputStream("data_boothify.dat");
            ObjectOutputStream mesinPembeku = new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(this.daftarSewa);
            mesinPembeku.writeObject(this.dataPelanggan);

            mesinPembeku.close();
            System.out.println("-> [SYSTEM LOG] Seluruh data berhasil disimpan (Serialization) ke data_boothify.dat!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal menyimpan data: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public void muatDataDariFile() {
        try {
            File cekFile = new File("data_boothify.dat");
            if (!cekFile.exists()) {
                return;
            }
            
            FileInputStream fileMasuk = new FileInputStream(cekFile);
            ObjectInputStream mesinPembangkit = new ObjectInputStream(fileMasuk);
            this.daftarSewa = (ArrayList<LayananBooth>) mesinPembangkit.readObject();
            this.dataPelanggan = (HashMap<String, Pelanggan>) mesinPembangkit.readObject();
            mesinPembangkit.close();
            
            System.out.println("-> [SYSTEM LOG] Data sistem berhasil dimuat ulang (Deserialization) dari penyimpanan!");
        } catch (Exception e) {
            System.out.println("-> [SYSTEM ERROR] Gagal memuat data: " + e.getMessage());
        }
    }
    
    public void bacaLogTransaksi() {
        System.out.println("\n--- Riwayat Log Transaksi Boothify ---");
        try {
            File fileLog = new File("log_transaksi.txt");
            if (!fileLog.exists()) {
                System.out.println("Belum ada catatan log transaksi.");
                return;
            }

            Scanner mesinBaca = new Scanner(fileLog);
            while (mesinBaca.hasNextLine()) {
                System.out.println(mesinBaca.nextLine());
            }
            mesinBaca.close();
        } catch (FileNotFoundException e) {
            System.out.println("Gagal membuka file log: " + e.getMessage());
        }
    }
}
