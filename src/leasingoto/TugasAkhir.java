package leasingoto;
import java.io.*;
import java.util.*;

public class TugasAkhir {
    static ArrayList<String> NIKPelanggan = new ArrayList<>();
    static ArrayList<String> namaPelanggan = new ArrayList<>();
    static ArrayList<String> alamatPelanggan = new ArrayList<>();
    static ArrayList<String> tipeMotor = new ArrayList<>();
    static ArrayList<Double> hargaMotor = new ArrayList<>();
    static ArrayList<Double> uangMuka = new ArrayList<>();
    static ArrayList<Double> bungaList = new ArrayList<>();
    static ArrayList<Double> cicilanPerBulan = new ArrayList<>();
    static ArrayList<Integer> tenor = new ArrayList<>();
    static boolean isRunning = true;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String[] daftarMotor = {"Honda Vario125", "Yamaha Nmax155", "Honda Beat Street","Honda ADV160","Honda Scoopy","Honda Sonic150R","Honda CB150R Streetfire"};
    static double[] hargaMotorList = {24450000, 32000000, 18450000,33000000,22500000,26000000,31500000};

    public static void main(String[] args) throws IOException {
        while (isRunning) {
            showMenu();
        }
    }

    static void showMenu() throws IOException {
        System.out.println("===========================================");
        System.out.println("            PROGRAM LEASING MOTOR          ");
        System.out.println("              NIM : 24.240.0060            ");
        System.out.println("           NAMA : BAGUS ADI WIDAGDO        ");
        System.out.println("===========================================");
        System.out.println("=========== MENU LEASING MOTOR ============");
        System.out.println("1. Lihat Data Leasing");
        System.out.println("2. Tambah Data Leasing");
        System.out.println("3. Edit Data Leasing");
        System.out.println("4. Hapus Data Leasing");
        System.out.println("5. Keluar");
        System.out.println("===========================================");
        System.out.print("Pilih menu: ");

        int selectedMenu = Integer.parseInt(br.readLine());

        switch (selectedMenu) {
            case 1:
                showAllLeasing();
                break;
            case 2:
                insertLeasing();
                break;
            case 3:
                editLeasing();
                break;
            case 4:
                deleteLeasing();
                break;
            case 5:
                isRunning = false;
                System.out.println("Program dihentikan. Terima kasih!");
                break;
            default:
                System.out.println("Menu tidak valid!");
        }
    }

    static void showAllLeasing() {
    Scanner baca = new Scanner(System.in);
    int page = 1;
    int hal = 3; 
    int totalpages = (int) Math.ceil((double) NIKPelanggan.size() / hal);

    if (NIKPelanggan.isEmpty()) {
        System.out.println("BELUM ADA DATA YANG TERINPUT!");
        return; 
    }

        while (true) {
            System.out.println("\n===== Laporan Data Pelanggan Leasing Motor =====");
            System.out.println("Per 8 Januari 2025");
            System.out.println("Halaman " + page + " dari " + totalpages + "\n");

            System.out.println("========= DATA PELANGGAN ==========");
            System.out.println("=======================================================================================================================================================");
            System.out.printf("| %-5s | %-12s | %-15s | %-30s | %-15s | %-10s | %-15s | %-10s | %-10s |\n",
                    "No", "NIK", "Nama", "Alamat", "Motor", "DP", "LamaKredit", "bunga", "Cicilan");
            System.out.println("=======================================================================================================================================================");

            int start = (page - 1) * hal;
            int end = Math.min(start + hal, NIKPelanggan.size());
            double grandTotal = 0;

            for (int i = start; i < end; i++) {
                String nik = NIKPelanggan.get(i);
                String nama = namaPelanggan.get(i);
                String alamat = alamatPelanggan.get(i);
                String motor = tipeMotor.get(i);
                double uangawal = uangMuka.get(i);
                double harga = hargaMotor.get(i);
                int lamakredit = tenor.get(i);
                double cicilan = cicilanPerBulan.get(i);
                double bunga = bungaList.get(i);
                System.out.printf("| %-5d | %-12s | %-15s | %-30s | %-15s | %-10.2f | %-15d | %-10.2f | %-10.2f |\n",
                        i + 1, nik, nama, alamat, motor, uangawal, lamakredit, bunga, cicilan);
                grandTotal += cicilan;
            }

            System.out.println("=======================================================================================================================================================");
            System.out.printf("Subtotal Cicilan: Rp. %.2f%n", grandTotal);
            System.out.println("=======================================================================================================================================================");

            if (page < totalpages) {
                System.out.print("\nTekan 'n' untuk halaman berikutnya, 'p' untuk sebelumnya, atau 'q' untuk keluar: ");
            } else if (page > 1) {
                System.out.print("\nTekan 'p' untuk halaman sebelumnya atau 'q' untuk keluar: ");
            } else {
                System.out.print("\nTekan 'q' untuk keluar: ");
            }

            String input = baca.nextLine();

            if (input.equalsIgnoreCase("n") && page < totalpages) {
                page++;
            } else if (input.equalsIgnoreCase("p") && page > 1) {
                page--;
            } else if (input.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("Input tidak valid. Silakan coba lagi.");
            }
        }
    }

    static void insertLeasing() throws IOException {

    System.out.println("\n======== Tambah Data Leasing ========");

    System.out.println("\nDaftar Motor yang Tersedia:");
    for (int i = 0; i < daftarMotor.length; i++) {
        System.out.println((i + 1) + ". " + daftarMotor[i] + " - Harga: Rp " + String.format("%,.0f", hargaMotorList[i]));
    }

    System.out.print("Pilih motor (1-" + daftarMotor.length + "): ");
    int motorChoice = Integer.parseInt(br.readLine()) - 1;

    if (motorChoice >= 0 && motorChoice < daftarMotor.length) {
        String motorTerpilih = daftarMotor[motorChoice];
        double harga = hargaMotorList[motorChoice];

        System.out.print("Masukkan NIK Pelanggan: ");
        NIKPelanggan.add(br.readLine());

        System.out.print("Masukkan Nama Pelanggan: ");
        namaPelanggan.add(br.readLine());

        System.out.print("Masukkan Alamat: ");
        alamatPelanggan.add(br.readLine());

        tipeMotor.add(motorTerpilih);
        hargaMotor.add(harga);

        System.out.println("Pilih uang muka (10% - 60% dari harga motor):");
        for (int i = 10; i <= 60; i += 10) {
            System.out.println(i + "% - Rp " + String.format("%,.0f", harga * i / 100));
        }
        System.out.print("Masukkan persentase uang muka (10, 20, ..., 60): ");
        int persenUangMuka = Integer.parseInt(br.readLine());

        if (persenUangMuka >= 10 && persenUangMuka <= 60 && persenUangMuka % 10 == 0) {
            double uangMukaVal = harga * persenUangMuka / 100;
            uangMuka.add(uangMukaVal);

            System.out.println("\nPilih Masa Kredit (tahun):");
            System.out.println("1. 1 Tahun\n2. 2 Tahun\n3. 3 Tahun\n4. 4 Tahun\n5. 5 Tahun\n6. 6 Tahun\n7. 7 Tahun");
            System.out.print("Masukkan pilihan masa kredit (1-7): ");
            int masaKreditTahun = Integer.parseInt(br.readLine());
            int tenorVal = masaKreditTahun * 12; 
            tenor.add(tenorVal);

            System.out.println("\nPilih bunga (6% - 36%):");
            System.out.print("Masukkan bunga (dalam persen): ");
            double bunga = Double.parseDouble(br.readLine());
            bungaList.add(bunga);

            if (bunga >= 6 && bunga <= 36) {
                double sisaHarga = harga - uangMukaVal;
                double totalBunga = sisaHarga * (bunga / 100);
                double totalHarga = sisaHarga + totalBunga;
                double cicilan = totalHarga / tenorVal;

                cicilanPerBulan.add(cicilan);

                System.out.println("Data berhasil ditambahkan!");
                System.out.printf("Cicilan per bulan: Rp %,d (dengan bunga %.1f%%)%n", Math.round(cicilan), bunga);
            } else {
                System.out.println("Persentase bunga tidak valid!");
            }
        } else {
            System.out.println("Persentase uang muka tidak valid!");
        }
    } else {
        System.out.println("Pilihan motor tidak valid!");
    }
}
        static void editLeasing() throws IOException {
            System.out.println("\n======== Edit Data Leasing ========");
            showAllLeasing();
            System.out.print("Pilih nomor data yang ingin diubah: ");
            int index = Integer.parseInt(br.readLine()) - 1;

            if (index >= 0 && index < NIKPelanggan.size()) {
                System.out.println("\nDaftar Motor yang Tersedia:");
                for (int i = 0; i < daftarMotor.length; i++) {
                    System.out.println((i + 1) + ". " + daftarMotor[i] + " - Harga: Rp " + String.format("%,.0f", hargaMotorList[i]));
                }
                System.out.print("Pilih motor (1-" + daftarMotor.length + "): ");
                int motorChoice = Integer.parseInt(br.readLine()) - 1;

                if (motorChoice >= 0 && motorChoice < daftarMotor.length) {
                    String motorTerpilih = daftarMotor[motorChoice];
                    double harga = hargaMotorList[motorChoice];

                    System.out.print("Masukkan NIK Pelanggan baru: ");
                    NIKPelanggan.set(index, br.readLine());

                    System.out.print("Masukkan Nama Pelanggan baru: ");
                    namaPelanggan.set(index, br.readLine());

                    System.out.print("Masukkan Alamat baru: ");
                    alamatPelanggan.set(index, br.readLine());

                    tipeMotor.set(index, motorTerpilih);
                    hargaMotor.set(index, harga);

                    System.out.println("Pilih uang muka (10% - 60% dari harga motor):");
                    for (int i = 10; i <= 60; i += 10) {
                        System.out.println(i + "% - Rp " + String.format("%,.0f", harga * i / 100));
                    }
                    System.out.print("Masukkan persentase uang muka (10, 20, ..., 60): ");
                    int persenUangMuka = Integer.parseInt(br.readLine());

                    if (persenUangMuka >= 10 && persenUangMuka <= 60 && persenUangMuka % 10 == 0) {
                        double uangMukaVal = harga * persenUangMuka / 100;
                        uangMuka.set(index, uangMukaVal);

                        System.out.println("\nPilih Masa Kredit (tahun):");
                        System.out.println("1. 1 Tahun\n2. 2 Tahun\n3. 3 Tahun\n4. 4 Tahun\n5. 5 Tahun\n6. 6 Tahun\n7. 7 Tahun");
                        System.out.print("Masukkan pilihan masa kredit (1-7): ");
                        int masaKreditTahun = Integer.parseInt(br.readLine());
                        int tenorVal = masaKreditTahun * 12; 
                        tenor.set(index, tenorVal);

                        System.out.println("\nPilih bunga (6% - 36%):");
                        System.out.print("Masukkan bunga (dalam persen): ");
                        double bunga = Double.parseDouble(br.readLine());
                        bungaList.set(index, bunga);

                        if (bunga >= 6 && bunga <= 36) {
                            double sisaHarga = harga - uangMukaVal;
                            double totalBunga = sisaHarga * (bunga / 100);
                            double totalHarga = sisaHarga + totalBunga;
                            double cicilan = totalHarga / tenorVal;

                            cicilanPerBulan.set(index, cicilan);

                            System.out.println("Data berhasil diubah!");
                            System.out.printf("Cicilan per bulan: Rp %,d (dengan bunga %.1f%%)%n", Math.round(cicilan), bunga);
                        } else {
                            System.out.println("Persentase bunga tidak valid!");
                        }
                    } else {
                        System.out.println("Persentase uang muka tidak valid!");
                    }
                } else {
                    System.out.println("Pilihan motor tidak valid!");
                }
            } else {
                System.out.println("Data tidak ditemukan!");
            }
        }

    static void deleteLeasing() throws IOException {
        System.out.println("\n======== Hapus Data Leasing ========");
        showAllLeasing();
        System.out.print("Pilih nomor data yang ingin dihapus: ");
        int index = Integer.parseInt(br.readLine()) - 1;

        if (index >= 0 && index < NIKPelanggan.size()) {
            NIKPelanggan.remove(index);
            namaPelanggan.remove(index);
            alamatPelanggan.remove(index);
            tipeMotor.remove(index);
            hargaMotor.remove(index);
            uangMuka.remove(index);
            cicilanPerBulan.remove(index);
            tenor.remove(index);

            System.out.println("Data berhasil dihapus!");
        } else {
            System.out.println("Data tidak ditemukan!");
        }
    }
}
