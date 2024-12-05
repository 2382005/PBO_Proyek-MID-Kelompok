package absensiAsrama.entities;

public class AbsensiAsrama {
    private Integer id;
    private String namaSiswa;
    private String tanggal;
    private Boolean hadir;

    public AbsensiAsrama() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Boolean getHadir() {
        return hadir;
    }

    public void setHadir(Boolean hadir) {
        this.hadir = hadir;
    }
}