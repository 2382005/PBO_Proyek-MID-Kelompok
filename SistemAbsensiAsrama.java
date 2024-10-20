import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Acara {
    String namaAcara;
    String tanggal;
    String jamMulai;
    String jamSelesai;
    String pembuatAcara;

    public Acara(String namaAcara, String tanggal, String jamMulai, String jamSelesai, String pembuatAcara) {
        this.namaAcara = namaAcara;
        this.tanggal = tanggal;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.pembuatAcara = pembuatAcara;
    }

    public String getNamaAcara() {
        return namaAcara;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    @Override
    public String toString() {
        return "Nama Acara: " + namaAcara + ", Tanggal: " + tanggal + ", Jam Mulai: " + jamMulai + ", Jam Selesai: " + jamSelesai + ", Pembuat Acara: " + pembuatAcara;
    }
}

public class SistemAbsensiAsrama {

    // Data username dan password untuk para monitor dan monitris
    private static HashMap<String, String> monitorAccounts = new HashMap<>();
    // Data pemberitahuan harian yang dibuat oleh monitris/monitor
    private static String dailyNotification = "";
    private static ArrayList<Acara> daftarAcara = new ArrayList<>(); // Daftar acara untuk penyimpanan acara
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inisialisasi akun para monitor dan monitris
        initializeMonitorAccounts();

        // Inisialisasi poin penghuni asrama
        initializePenghuniPoints();

        // Menampilkan judul aplikasi
        System.out.println("========================================");
        System.out.println(" Inovasi Sistem Absensi untuk Penghuni Asrama");
        System.out.println("========================================");

        // Sistem login
        System.out.print("Masukkan Username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan Password: ");
        String password = scanner.nextLine();

        // Jika username dan password cocok dengan akun monitor/monitris
        if (isMonitorAccount(username, password)) {
            System.out.println(username + " Area");
            showMonitorMenu(username);
        } else {
            // Jika username dan password tidak cocok, anggap sebagai penghuni biasa
            System.out.println("Selamat datang, Penghuni Asrama!");
            showResidentMenu(password);
        }
    }

    // Method untuk memasukkan acara
    public static void inputAcara() {
        System.out.print("Masukkan Nama Acara: ");
        String namaAcara = scanner.nextLine();
        System.out.print("Masukkan Tanggal (dd/MM/yyyy): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan Jam Mulai (HH:mm): ");
        String jamMulai = scanner.nextLine();
        System.out.print("Masukkan Jam Selesai (HH:mm): ");
        String jamSelesai = scanner.nextLine();
        System.out.print("Masukkan Nama Pembuat Acara: ");
        String pembuatAcara = scanner.nextLine();

        Acara acaraBaru = new Acara(namaAcara, tanggal, jamMulai, jamSelesai, pembuatAcara);
        daftarAcara.add(acaraBaru);
        System.out.println("Acara berhasil ditambahkan!\n");
    }

    // Method untuk mengedit acara yang sudah ada
    public static void editAcara() {
        System.out.print("Masukkan Nama Acara yang ingin diedit: ");
        String namaAcara = scanner.nextLine();

        for (Acara acara : daftarAcara) {
            if (acara.getNamaAcara().equalsIgnoreCase(namaAcara)) {
                System.out.print("Masukkan Tanggal Baru (dd/MM/yyyy): ");
                acara.setTanggal(scanner.nextLine());
                System.out.print("Masukkan Jam Mulai Baru (HH:mm): ");
                acara.setJamMulai(scanner.nextLine());
                System.out.print("Masukkan Jam Selesai Baru (HH:mm): ");
                acara.setJamSelesai(scanner.nextLine());
                System.out.println("Acara berhasil diedit!\n");
                return;
            }
        }
        System.out.println("Acara tidak ditemukan!\n");
    }

    // Method untuk menghapus acara
    public static void hapusAcara() {
        System.out.print("Masukkan Nama Acara yang ingin dihapus: ");
        String namaAcara = scanner.nextLine();

        for (int i = 0; i < daftarAcara.size(); i++) {
            if (daftarAcara.get(i).getNamaAcara().equalsIgnoreCase(namaAcara)) {
                daftarAcara.remove(i);
                System.out.println("Acara berhasil dihapus!\n");
                return;
            }
        }
        System.out.println("Acara tidak ditemukan!\n");
    }

    // Method untuk menampilkan dan menyortir acara
    public static void tampilkanAcara() {
        if (daftarAcara.isEmpty()) {
            System.out.println("Tidak ada acara yang terdaftar.");
        } else {
            Collections.sort(daftarAcara, new Comparator<Acara>() {
                @Override
                public int compare(Acara a1, Acara a2) {
                    return a1.getTanggal().compareTo(a2.getTanggal());
                }
            });

            for (Acara acara : daftarAcara) {
                System.out.println(acara);
            }
        }
    }

    // Memeriksa apakah login untuk monitris/monitor
    private static boolean isMonitorAccount(String username, String password) {
        return monitorAccounts.containsKey(username) && monitorAccounts.get(username).equals(password);
    }

    private static HashMap<String, Integer> penghuniPoints = new HashMap<>();

    private static void initializePenghuniPoints() {
        penghuniPoints.put("Penghuni1", 100);
        penghuniPoints.put("Penghuni2", 90);
        penghuniPoints.put("Penghuni3", 85);
        // Tambahkan penghuni lainnya sesuai kebutuhan
    }

    // Menampilkan poin penghuni
    public static void tampilkanPoinPenghuni(String username) {
        if (penghuniPoints.containsKey(username)) {
            System.out.println("Poin Anda: " + penghuniPoints.get(username));
        } else {
            System.out.println("Penghuni tidak ditemukan.");
        }
    }

    // Method untuk menampilkan rangkuman poin seluruh penghuni (untuk monitor)
    public static void tampilkanRangkumanPoin() {
        System.out.println("Rangkuman Poin Seluruh Penghuni:");
        for (String penghuni : penghuniPoints.keySet()) {
            System.out.println(penghuni + ": " + penghuniPoints.get(penghuni) + " poin");
        }
    }

    // Menampilkan menu untuk monitris/monitor
    private static void showMonitorMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==== Dashboard Monitris/Monitor ====");
            System.out.println("1. Buat Pemberitahuan Harian");
            System.out.println("2. Lihat Catatan Monitris/Monitor");
            System.out.println("3. Buat Acara Baru");
            System.out.println("4. Edit Acara");
            System.out.println("5. Hapus Acara");
            System.out.println("6. Lihat Acara");
            System.out.println("7. Lihat Rangkuman Poin Penghuni");
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
                case 3:
                    inputAcara();
                    break;
                case 4:
                    editAcara();
                    break;
                case 5:
                    hapusAcara();
                    break;
                case 6:
                    tampilkanAcara();
                    break;
                case 7:
                    tampilkanRangkumanPoin();
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
    private static void showResidentMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==== Dashboard Penghuni Asrama ====");
            System.out.println("1. Lihat Catatan Monitris/Monitor");
            System.out.println("2. Lihat Poin Kehadiran");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // membersihkan buffer

            switch (choice) {
                case 1:
                    System.out.println("Catatan Monitris: ");
                    System.out.println(dailyNotification.isEmpty() ? "Belum ada catatan." : dailyNotification);
                    break;
                case 2:
                    tampilkanPoinPenghuni(username);
                    break;
                case 3:
                    System.out.println("Logout berhasil.");
                    choice = 0; // keluar dari sistem
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
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
