import java.util.HashMap;
import java.util.Scanner;

public class SistemAbsensiAsrama {

    // Data username dan password untuk para monitor dan monitris
    private static HashMap<String, String> monitorAccounts = new HashMap<>();
    // Data pemberitahuan harian yang dibuat oleh monitris/monitor
    private static String dailyNotification = "";

    public static void main(String[] args) {
        // Inisialisasi akun para monitor dan monitris
        initializeMonitorAccounts();

        // Menampilkan judul aplikasi
        System.out.println("========================================");
        System.out.println(" Inovasi Sistem Absensi untuk Penghuni Asrama");
        System.out.println("========================================");

        // Sistem login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        // Jika username dan password cocok dengan akun monitor/monitris
        if (isMonitorAccount(username, password)) {
            System.out.println(username + " Area");
            showMonitorMenu(scanner, username);
        } else {
            // Jika username dan password tidak cocok, anggap sebagai penghuni biasa
            System.out.println("Selamat datang, Penghuni Asrama!");
            showResidentMenu();
        }
    }

    // Memeriksa apakah login untuk monitris/monitor
    private static boolean isMonitorAccount(String username, String password) {
        return monitorAccounts.containsKey(username) && monitorAccounts.get(username).equals(password);
    }

    // Menampilkan menu untuk monitris/monitor
    private static void showMonitorMenu(Scanner scanner, String username) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==== Dashboard Monitris/Monitor ====");
            System.out.println("1. Buat Pemberitahuan Harian");
            System.out.println("2. Lihat Catatan Monitris/Monitor");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // membersihkan buffer

            switch (choice) {
                case 1:
                    System.out.println("Masukkan catatan pemberitahuan harian: ");
                    dailyNotification = scanner.nextLine();
                    System.out.println("Catatan tersimpan.");
                    break;
                case 2:
                    System.out.println("Catatan Monitris/Monitor: ");
                    System.out.println(dailyNotification.isEmpty() ? "Belum ada catatan." : dailyNotification);
                    break;
                case 0:
                    System.out.println("Keluar dari sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Menampilkan menu untuk penghuni asrama biasa
    private static void showResidentMenu() {
        System.out.println("\n==== Dashboard Penghuni Asrama ====");
        System.out.println("Fitur yang bisa diakses oleh penghuni asrama: ");
        System.out.println("1. Lihat Catatan Monitris/Monitor");
        System.out.println("2. Logout");
        System.out.println("Fitur lainnya akan dikembangkan lebih lanjut.");
    }

    // Inisialisasi akun monitor dan monitris
    private static void initializeMonitorAccounts() {
        monitorAccounts.put("Ester Hall", "ehWELL");
        monitorAccounts.put("Ester Extension Hall", "EEHWARR");
        monitorAccounts.put("Ruth Hall", "RHCALM");
        monitorAccounts.put("Ruth Extension Hall", "REhElit");
        monitorAccounts.put("Naomi Hall", "HallKkPrwt");
        monitorAccounts.put("Daniel", "PecintaUnai");
        monitorAccounts.put("Joseph", "BrokoliMbaFin");
        monitorAccounts.put("Samuel", "BarudakWELL");
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Kelas non-publik Acara
class Acara {
    private String tanggal;
    private String nama;
    private String deskripsi;

    // Konstruktor untuk menginisialisasi objek Acara
    public Acara(String tanggal, String nama, String deskripsi) {
        this.tanggal = tanggal;
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    // Metode getter untuk mendapatkan tanggal acara
    public String getTanggal() {
        return tanggal;
    }

    // Metode getter untuk mendapatkan nama acara
    public String getNama() {
        return nama;
    }

    // Metode getter untuk mendapatkan deskripsi acara
    public String getDeskripsi() {
        return deskripsi;
    }
}

// Kelas publik DaftarAcara
public class DaftarAcara {
    private List<Acara> acaraList;
    private Scanner scanner;

    // Konstruktor
    public DaftarAcara() {
        acaraList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Metode untuk memasukkan acara
    public void tambahAcara() {
        System.out.println("=== Tambah Acara Baru ===");

        String tanggal;
        while (true) {
            System.out.print("Masukkan Tanggal (YYYY-MM-DD): ");
            tanggal = scanner.nextLine().trim();
            if (isValidDate(tanggal)) {
                break;
            } else {
                System.out.println("Format tanggal salah. Silakan coba lagi.");
            }
        }

        String nama;
        while (true) {
            System.out.print("Masukkan Nama Acara: ");
            nama = scanner.nextLine().trim();
            if (!nama.isEmpty()) {
                break;
            } else {
                System.out.println("Nama acara tidak boleh kosong. Silakan coba lagi.");
            }
        }

        String deskripsi;
        while (true) {
            System.out.print("Masukkan Deskripsi Acara: ");
            deskripsi = scanner.nextLine().trim();
            if (!deskripsi.isEmpty()) {
                break;
            } else {
                System.out.println("Deskripsi acara tidak boleh kosong. Silakan coba lagi.");
            }
        }

        Acara acara = new Acara(tanggal, nama, deskripsi);
        acaraList.add(acara);

        System.out.println("Acara berhasil ditambahkan!\n");
    }

    // Metode untuk menampilkan semua acara dalam bentuk tabel
    public void tampilkanAcara() {
        if (acaraList.isEmpty()) {
            System.out.println("Tidak ada acara yang tersedia.\n");
            return;
        }

        System.out.printf("%-15s %-30s %-50s%n", "Tanggal", "Nama Acara", "Deskripsi");
        System.out.println("-----------------------------------------------------------------------------------------------");

        for (Acara acara : acaraList) {
            System.out.printf("%-15s %-30s %-50s%n", acara.getTanggal(), acara.getNama(), acara.getDeskripsi());
        }
        System.out.println();
    }

    // Metode untuk memeriksa validitas format tanggal (YYYY-MM-DD)
    public boolean isValidDate(String tanggal) {
        return tanggal.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Metode utama untuk menjalankan program
    public void run() {
        System.out.println("=== Program Daftar Acara ===\n");

        boolean exit = false;
        while (!exit) {
            System.out.println("Pilih opsi:");
            System.out.println("1. Tambah Acara");
            System.out.println("2. Tampilkan Daftar Acara");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan (1-3): ");

            String pilihan = scanner.nextLine().trim();
            System.out.println();

            switch (pilihan) {
                case "1":
                    tambahAcara();
                    break;
                case "2":
                    tampilkanAcara();
                    break;
                case "3":
                    exit = true;
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.\n");
            }
        }

        scanner.close();
    }

    // Metode main untuk menjalankan program
    public static void main(String[] args) {
        DaftarAcara daftarAcara = new DaftarAcara();
        daftarAcara.run();
    }
}

