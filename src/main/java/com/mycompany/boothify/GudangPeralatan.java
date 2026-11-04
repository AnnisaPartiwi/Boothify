/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.boothify;

/**
 *
 * @author Annisa
 */
public class GudangPeralatan {
    private String namaGudang;
    private int kapasitasPerangkat;
    
    public GudangPeralatan(String namaGudang, int kapasitasPerangkat) {
        this.namaGudang = namaGudang;
        this.kapasitasPerangkat = kapasitasPerangkat;
    }
    
    public void cekKetersediaan(){
        System.out.println("-" + namaGudang + " aktif. Kapasitas: " + kapasitasPerangkat + " unit booth.");
    }
}
