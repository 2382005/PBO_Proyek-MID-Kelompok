package absensiAsrama.repositories;

import entities.AbsensiAsrama;

public interface AbsensiAsramaRepository {
    AbsensiAsrama[] getAll();

    void add(AbsensiAsrama absensiAsrama);

    Boolean remove(Integer id);

    Boolean edit(AbsensiAsrama absensiAsrama);
}