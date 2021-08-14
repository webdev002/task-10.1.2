package pdp.uz.task10.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.task10.entity.Room;

import java.util.List;
import java.util.Optional;

public class HotelRepository implements JpaRepository<Room, Integer>{
    @Override
    public List<Room> findAll() {
        return null;
    }

    @Override
    public List<Room> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Room> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Room> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Room room) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Room> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Room> S save(S s) {
        return null;
    }

    @Override
    public <S extends Room> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Room> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Room> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Room> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Room> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Room getOne(Integer integer) {
        return null;
    }

    @Override
    public Room getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Room> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Room> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Room> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Room> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Room> boolean exists(Example<S> example) {
        return false;
    }
}

