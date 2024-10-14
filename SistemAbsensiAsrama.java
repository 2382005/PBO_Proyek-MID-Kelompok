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
