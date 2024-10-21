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

    private static HashMap<String, String> monitorAccounts = new HashMap<>();
    private static String dailyNotification = "";
    private static ArrayList<Acara> daftarAcara = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, ArrayList<String>> daftarHadir = new HashMap<>();
    private static HashMap<String, ArrayList<String>> daftarTidakHadir = new HashMap<>();
    private static ArrayList<IzinTidakHadir> daftarIzinTidakHadir = new ArrayList<>();
    private static HashMap<String, Integer> penghuniPoints = new HashMap<>();
    private static ArrayList<Feedback> feedbackList = new ArrayList<>();


    public static void main(String[] args) {
        // Inisialisasi akun para monitor
        initializeMonitorAccounts();
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

        if (isMonitorAccount(username, password)) {
            System.out.println(username + " Area");
            showMonitorMenu(username);
        } else {
            System.out.println("Selamat datang, Penghuni Asrama!");
            showResidentMenu(username);
        }
    }

    private static void inputAcara() {
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

    private static void editAcara() {
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

    private static void hapusAcara() {
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

    private static void tampilkanAcara() {
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

    private static void submitFeedback(String username) {
        System.out.print("Masukkan feedback Anda: ");
        String content = scanner.nextLine();
        Feedback feedback = new Feedback(username, content);
        feedbackList.add(feedback);
        System.out.println("Feedback Anda telah dikirim.\n");
    }

    private static void displayFeedback() {
        System.out.println("Daftar Feedback:");
        if (feedbackList.isEmpty()) {
            System.out.println("Belum ada feedback yang diberikan.");
        } else {
            for (Feedback feedback : feedbackList) {
                System.out.println(feedback);
            }
        }
    }


    private static boolean isMonitorAccount(String username, String password) {
        return monitorAccounts.containsKey(username) && monitorAccounts.get(username).equals(password);
    }

    private static void initializePenghuniPoints() {
        penghuniPoints.put("Penghuni1", 100);
        penghuniPoints.put("Penghuni2", 90);
        penghuniPoints.put("Penghuni3", 85);
    }

    private static void tampilkanPoinPenghuni(String username) {
        System.out.println("Ketentuan Poin:");
        System.out.println("Ibadah Chapel: -100 Point");
        System.out.println("Ibadah NDG: -70 Point");
        System.out.println("Acara Girls Club: -50 Point");
        System.out.println("Acara PA: -70 Point");
        System.out.println("Kegiatan lainnya tergantung keputusan kepala asrama.\n");

        if (penghuniPoints.containsKey(username)) {
            System.out.println("Poin Anda: " + penghuniPoints.get(username));
        } else {
            System.out.println("Penghuni tidak ditemukan.");
        }
    }

    private static void tampilkanRangkumanPoin() {
        System.out.println("Rangkuman Poin Seluruh Penghuni:");
        for (String penghuni : penghuniPoints.keySet()) {
            System.out.println(penghuni + ": " + penghuniPoints.get(penghuni) + " poin");
        }
    }

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
            System.out.println("8. Absensi");
            System.out.println("9. Lihat Daftar Hadir");
            System.out.println("10. Lihat Daftar Tidak Hadir");
            System.out.println("11. Izin Tidak Hadir");
            System.out.println("12. Lihat Izin Tidak Hadir");
            System.out.println("13. Lihat Feedback");
            System.out.println("14. Kirim Feedback");
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
                    break;
                case 8:
                    absensi(username);
                    break;
                case 9:
                    tampilkanDaftarHadir();
                    break;
                case 10:
                    tampilkanDaftarTidakHadir();
                    break;
                case 11:
                    izinTidakHadir();
                    break;
                case 12:
                    tampilkanIzinTidakHadir();
                    break;
                case 13:
                    displayFeedback();
                    break;
                case 14:
                    submitFeedback(username);
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

    private static void showResidentMenu(String username) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n==== Dashboard Penghuni Asrama ====");
            System.out.println("1. Lihat Catatan Monitris/Monitor");
            System.out.println("2. Lihat Acara");
            System.out.println("3. Kirim Feedback"); // Added feedback option
            System.out.println("4. Ketentuan Poin");
            System.out.println("5. Izin Tidak Hadir");
            System.out.println("6. Buat Pemberitahuan Harian");
            System.out.println("7. Buat Acara Baru");
            System.out.println("8. Edit Acara");
            System.out.println("9. Hapus Acara");
            System.out.println("10. Lihat Rangkuman Poin Penghuni");
            System.out.println("11. Absensi");
            System.out.println("12. Lihat Daftar Hadir");
            System.out.println("13. Lihat Daftar Tidak Hadir");
            System.out.println("14. Lihat Izin Tidak Hadir");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // membersihkan buffer

            switch (choice) {
                case 1:
                    System.out.println("Catatan Monitris: ");
                    System.out.println(dailyNotification.isEmpty() ? "Belum ada catatan." : dailyNotification);
                    break;
                case 2:
                    tampilkanAcara();
                    break;
                case 3: // Handle feedback submission
                    submitFeedback(username);
                    break;
                case 4:
                    tampilkanPoinPenghuni(username);
                    break;
                case 5:
                    izinTidakHadir();
                    break;
                // Menangani fitur yang tidak diizinkan
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                    System.out.println("Maaf, anda bukan Monitris/Monitor. Anda tidak memiliki akses ke fitur ini.");
                    break;
                case 13:
                    tampilkanIzinTidakHadir();
                    break;
                case 0:
                    System.out.println("Logout berhasil.");
                    choice = 0; // keluar dari sistem
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }



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

    public static void absensi(String username) {
        System.out.println("Masukkan daftar hadir (pisahkan dengan koma):");
        String[] hadir = scanner.nextLine().split(",");
        if (!daftarHadir.containsKey(username)) {
            daftarHadir.put(username, new ArrayList<>());
        }
        for (String nama : hadir) {
            daftarHadir.get(username).add(nama.trim());
        }

        System.out.println("Masukkan daftar tidak hadir (pisahkan dengan koma):");
        String[] tidakHadir = scanner.nextLine().split(",");
        if (!daftarTidakHadir.containsKey(username)) {
            daftarTidakHadir.put(username, new ArrayList<>());
        }
        for (String nama : tidakHadir) {
            daftarTidakHadir.get(username).add(nama.trim());
        }

        System.out.println("Absensi telah dicatat.\n");
    }

    public static void tampilkanDaftarHadir() {
        System.out.print("Masukkan nama asrama untuk melihat daftar hadir: ");
        String asrama = scanner.nextLine();
        System.out.println("Daftar Hadir untuk Asrama " + asrama + ":");
        ArrayList<String> hadir = daftarHadir.get(asrama);
        if (hadir != null && !hadir.isEmpty()) {
            for (String nama : hadir) {
                System.out.println(nama);
            }
        } else {
            System.out.println("Tidak ada nama yang hadir.");
        }
    }

    public static void tampilkanDaftarTidakHadir() {
        System.out.print("Masukkan nama asrama untuk melihat daftar tidak hadir: ");
        String asrama = scanner.nextLine();
        System.out.println("Daftar Tidak Hadir untuk Asrama " + asrama + ":");
        ArrayList<String> tidakHadir = daftarTidakHadir.get(asrama);
        if (tidakHadir != null && !tidakHadir.isEmpty()) {
            for (String nama : tidakHadir) {
                System.out.println(nama);
            }
        } else {
            System.out.println("Tidak ada nama yang tidak hadir.");
        }
    }

    static class Feedback {
        String username;
        String content;

        public Feedback(String username, String content) {
            this.username = username;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Penghuni: " + username + ", Feedback: " + content;
        }
    }


    static class IzinTidakHadir {
        String nama;
        String namaAsrama;
        String tanggal;
        String namaAcara;
        String alasan;

        public IzinTidakHadir(String nama, String namaAsrama, String tanggal, String namaAcara, String alasan) {
            this.nama = nama;
            this.namaAsrama = namaAsrama;
            this.tanggal = tanggal;
            this.namaAcara = namaAcara;
            this.alasan = alasan;
        }

        @Override
        public String toString() {
            return "Nama: " + nama + ", Asrama: " + namaAsrama + ", Tanggal: " + tanggal + ", Acara: " + namaAcara + ", Alasan: " + alasan;
        }
    }

    public static void izinTidakHadir() {
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Nama Asrama: ");
        String namaAsrama = scanner.nextLine();
        System.out.print("Masukkan Tanggal (dd/MM/yyyy): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan Nama Acara: ");
        String namaAcara = scanner.nextLine();
        System.out.print("Masukkan Alasan: ");
        String alasan = scanner.nextLine();

        IzinTidakHadir izin = new IzinTidakHadir(nama, namaAsrama, tanggal, namaAcara, alasan);
        daftarIzinTidakHadir.add(izin);
        System.out.println("Izin tidak hadir telah dicatat.\n");
    }

    public static void tampilkanIzinTidakHadir() {
        System.out.println("Daftar Izin Tidak Hadir:");
        for (IzinTidakHadir izin : daftarIzinTidakHadir) {
            System.out.println(izin);
        }

        // Menampilkan alasan untuk yang tidak hadir
        System.out.println("\nLihat Alasan:");
        for (IzinTidakHadir izin : daftarIzinTidakHadir) {
            System.out.println(izin.nama + " tidak hadir, alasan: " + izin.alasan);
        }
    }
}
