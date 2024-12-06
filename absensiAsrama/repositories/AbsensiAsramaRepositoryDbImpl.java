package absensiAsrama.repositories;

import config.Database;
import entities.AbsensiAsrama;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class AbsensiAsramaRepositoryDbImpl implements AbsensiAsramaRepository {
    private final Database database;

    public AbsensiAsramaRepositoryDbImpl(final Database database) {
        this.database = database;
    }

    @Override
    public AbsensiAsrama[] getAll() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM absensi_asrama";
        List<AbsensiAsrama> absensiList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = prepared Statement.executeQuery();
            while (resultSet.next()) {
                AbsensiAsrama absensi = new AbsensiAsrama();
                absensi.setNamaAcara(resultSet.getString("nama_acara"));
                absensi.setTanggal(resultSet.getString("tanggal"));
                absensi.setJamMulai(resultSet.getString("jam_mulai"));
                absensi.setJamSelesai(resultSet.getString("jam_selesai"));
                absensi.setPembuatAcara(resultSet.getString("pembuat_acara"));
                absensiList.add(absensi);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return absensiList.toArray(new AbsensiAsrama[0]);
    }

    @Override
    public void add(final AbsensiAsrama absensiAsrama) {
        String sqlStatement = "INSERT INTO absensi_asrama(nama_acara, tanggal, jam_mulai, jam_selesai, pembuat_acara) VALUES(?, ?, ?, ?, ?)";
        Connection conn = database.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, absensiAsrama.getNamaAcara());
            preparedStatement.setString(2, absensiAsrama.getTanggal());
            preparedStatement.setString(3, absensiAsrama.getJamMulai());
            preparedStatement.setString(4, absensiAsrama.getJamSelesai());
            preparedStatement.setString(5, absensiAsrama.getPembuatAcara());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert successful!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Boolean remove(final Integer id) {
        String sqlStatement = "DELETE FROM absensi_asrama WHERE id = ?";
        Connection conn = database.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean edit(final AbsensiAsrama absensiAsrama) {
        String sqlStatement = "UPDATE absensi_asrama SET nama_acara = ?, tanggal = ?, jam_mulai = ?, jam_selesai = ?, pembuat_acara = ? WHERE id = ?";
        Connection conn = database.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);
            preparedStatement.setString(1, absensiAsrama.getNamaAcara());
            preparedStatement.setString(2, absensiAsrama.getTanggal());
            preparedStatement.setString(3, absensiAsrama.getJamMulai());
            preparedStatement.setString(4, absensiAsrama.getJamSelesai());
            preparedStatement.setString(5, absensiAsrama.getPembuatAcara());
            preparedStatement.setInt(6, absensiAsrama.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
